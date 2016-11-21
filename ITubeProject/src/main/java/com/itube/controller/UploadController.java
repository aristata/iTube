package com.itube.controller;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.itube.domain.PostVO;
import com.itube.service.PostServiceImpl;

@Controller
@RequestMapping("/upload")
public class UploadController {
	
	private static final String SAVE_PATH = "d:/movies/";
	
	@Autowired
	private PostServiceImpl postService;
	
	@RequestMapping("/")
	public String upload(){
		return "upload/upload";
	}
	
	@RequestMapping("/execute")
	public String execute(MultipartHttpServletRequest request) throws IOException{
		// 받아온 데이터
		String title = request.getParameter("title");
		String nickname = request.getParameter("nickname");
		String content = request.getParameter("content");
		MultipartFile file = request.getFile("file");
		MultipartFile thumbnail = request.getFile("thumbnail");
		String category = request.getParameter("category");
		
		
		// 파일
		String originName = file.getOriginalFilename();
		String fileType = originName.substring(originName.indexOf('.'));
		String saveFileName = UUID.randomUUID().toString();
		
		// DB 데이터 객체 작성
		PostVO postvo = new PostVO();
		postvo.setContent(content);
		postvo.setTitle(title);
		postvo.setUrl(saveFileName+fileType);
		postvo.setCategory(category);
		postvo.setNickname(nickname);
		postvo.setThumbnail(thumbnail.getInputStream());
				
		System.out.println(title);
		System.out.println(content);
		System.out.println(file.getOriginalFilename());
		System.out.println(category);
		
		// DB입력과 파일 저장
		try {
			postService.writePost(postvo);
			File saveDir = new File(SAVE_PATH);
			if(!saveDir.exists())
				saveDir.mkdirs();
			File savefile = new File(SAVE_PATH, saveFileName+fileType);
			
			if(!savefile.exists())
				savefile.createNewFile();
			file.transferTo(savefile);
			System.out.println("업로드 완료!!");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "redirect:../mypage/upload/";
	}

}
