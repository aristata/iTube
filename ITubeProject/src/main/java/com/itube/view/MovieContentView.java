package com.itube.view;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.io.RandomAccessFile;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.view.AbstractView;

public class MovieContentView extends AbstractView {

	private static final Logger logger = LoggerFactory.getLogger(MovieContentView.class);

	@Override
	protected void renderMergedOutputModel(Map<String, Object> arg0, HttpServletRequest arg1, HttpServletResponse arg2)
			throws Exception {
		String movieName = (String) arg0.get("movieName");
		String movieDir = (String) arg0.get("movieDir");
		File dirs = new File(movieDir);
		if(!dirs.exists())
			dirs.mkdirs();
		RandomAccessFile randomFile = null;
		try{
			randomFile = new RandomAccessFile(new File(movieDir, movieName), "r");
		}catch(FileNotFoundException e){
			logger.warn("파일이 없어요");
			return;
		}
		long fileLength = randomFile.length();

		long rangeStart = 0;
		long rangeEnd = 0;
		boolean isPart = false;

		try {
			String range = arg1.getHeader("range");
			logger.info(range);
			if (range != null) {
				if (range.endsWith("-"))
					range = range + (fileLength - 1);
				int idxm = range.trim().indexOf("-");
				rangeStart = Long.parseLong(range.substring(6, idxm));
				rangeEnd = Long.parseLong(range.substring(idxm + 1));
				if (rangeStart > 0)
					isPart = true;
			} else {
				rangeStart = 0;
				rangeEnd = fileLength - 1;
			}

			long partSize = rangeEnd - rangeStart + 1;

			arg2.reset();
			arg2.setStatus(isPart ? 206 : 200);
			arg2.setContentType("video/mp4");
			arg2.setHeader("Content-Range", "bytes " + rangeStart + "-" + rangeEnd + "/" + fileLength);
			arg2.setHeader("Accept-Ranges", "bytes");
			arg2.setHeader("Content-Length", "" + partSize);

			OutputStream out = arg2.getOutputStream();
			randomFile.seek(rangeStart);

			int bufferSize = 8 * 1024;
			byte[] buf = new byte[bufferSize];
			do {
				int block = partSize > bufferSize ? bufferSize : (int) partSize;
				int len = randomFile.read(buf, 0, block);
				out.write(buf, 0, len);
				partSize -= block;
			} while (partSize > 0);
			logger.debug("sent "+movieName+" "+rangeStart+"-"+rangeEnd);
		} catch (IOException e) {
			logger.debug("전송 취소");
		} finally {
			randomFile.close();
		}
	}
}
