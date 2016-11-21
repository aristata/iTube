package com.itube.controller;

import java.text.SimpleDateFormat;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.itube.domain.ReplyVO;
import com.itube.domain.UserVO;
import com.itube.persistence.PostDAOImpl;
import com.itube.persistence.ReplyDAOImpl;
import com.itube.service.PostServiceImpl;
import com.itube.service.ReplyServiceImpl;
import com.itube.view.MovieContentView;

@Controller
@RequestMapping("/view")
public class ViewController {

	private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");

	@Inject
	private PostServiceImpl service;
	@Inject
	private ReplyServiceImpl rService;
	@Inject
	private PostDAOImpl postDAO;
	@Inject
	private ReplyDAOImpl replyDAO;

	@RequestMapping(value = "/", method = { RequestMethod.GET, RequestMethod.POST })
	public String view(@RequestParam("pid") Integer pid, Model model) throws Exception {
		System.out.println("ViewController - pid 파라미터 테스트 : " + pid);
		model.addAttribute("pid", pid);
		model.addAttribute("postVO", service.readPost(pid));
		model.addAttribute("rList", rService.listReply(pid));
		return "view/view";
	}

	@RequestMapping(value = "/addReply", method = RequestMethod.POST, produces = "text/html; charset=utf-8")
	public ResponseEntity<String> addReply(HttpSession session, @RequestParam("pid") Integer pid,
			@RequestParam("content") String content, @RequestParam("nickname") String nickname) throws Exception {

		UserVO login = (UserVO) session.getAttribute("login");

		ReplyVO vo = new ReplyVO();
		vo.setContent(content);
		vo.setNickname(nickname);
		vo.setPid(pid);
		rService.addReply(vo);
		System.out.println("댓글 추가");
		System.out.println(replyHTML(pid, login));
				
		ResponseEntity<String> result = new ResponseEntity<>(replyHTML(pid, login), HttpStatus.OK);

		return result;
	}

	@RequestMapping(value = "deleteReply", method = RequestMethod.POST, produces = "text/html; charset=utf-8")
	public ResponseEntity<String> deleteReply(HttpSession session, @RequestParam("pid") Integer pid,
			@RequestParam("rid") Integer rid) throws Exception {

		UserVO login = (UserVO) session.getAttribute("login");

		rService.removeReply(rid);
		System.out.println("댓글 삭제");

		ResponseEntity<String> result = new ResponseEntity<>(replyHTML(pid, login), HttpStatus.OK);

		return result;
	}

	@RequestMapping
	public ResponseEntity<String> updateReply(HttpSession session, @RequestParam("pid") Integer pid,
			@RequestParam("rid") Integer rid) throws Exception {
		UserVO login = (UserVO) session.getAttribute("login");

		rService.modifyReply(null);

		ResponseEntity<String> result = new ResponseEntity<>(replyHTML(pid, login), HttpStatus.OK);

		return result;
	}

	@RequestMapping("/{uuid}")
	public ModelAndView movieCall(@PathVariable("uuid") String uuid) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("movieName", uuid + ".mp4");
		mav.addObject("movieDir", "d:/movies/");
		mav.setView(new MovieContentView());
		return mav;
	}
	
	@RequestMapping(value = "upDownCheck", method=RequestMethod.POST)
	public ResponseEntity<String> upDownCheck(HttpSession session, HttpServletRequest request) throws Exception{
		ResponseEntity<String> result = null;
		
		int pid =  Integer.parseInt(request.getParameter("pid"));
		int rid = Integer.parseInt(request.getParameter("rid")==null?"0":request.getParameter("rid"));
		int type = Integer.parseInt(request.getParameter("type"));
		String msg = request.getParameter("msg");
		System.out.println("upDownCheck 메소드의 "+pid+" : "+rid+" : "+type);
		
		if(type == 1){
			String i = postDAO.upDownTypeGet(pid, session.getId());
			System.out.println(i);
			if(i.equalsIgnoreCase("no")){
				result = new ResponseEntity<>(HttpStatus.OK);
				postDAO.insertUpDown(session.getId(), pid, msg);
			}else{
				result = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			}
		}else if(type == 2){
			String i = replyDAO.upDownTypeGet(rid, session.getId());
			System.out.println(i);
			if(i.equalsIgnoreCase("no")){
				result = new ResponseEntity<>(HttpStatus.OK);
				replyDAO.insertUpDown(session.getId(), rid, msg);
			}else{
				result = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			}
		}
		return result;
	}

	// 좋아요
	@RequestMapping(value = "clickLike", method = RequestMethod.POST)
	public ResponseEntity<String> clickLike(@RequestParam("pid") Integer pid) throws Exception {
		service.likeUp(pid);
		ResponseEntity<String> result = new ResponseEntity<>(HttpStatus.OK);
		return result;
	}

	@RequestMapping(value = "likeReply", method = RequestMethod.POST)
	public ResponseEntity<String> likeReply(@RequestParam("pid") Integer pid, @RequestParam("rid") Integer rid)
			throws Exception {
		rService.upReply(rid);

		ResponseEntity<String> result = new ResponseEntity<>(HttpStatus.OK);
		return result;
	}

	// 싫어요
	@RequestMapping(value = "clickDislike", method = RequestMethod.POST)
	public ResponseEntity<String> clickDislike(@RequestParam("pid") Integer pid) throws Exception {
		service.dislikeUp(pid);
		ResponseEntity<String> result = new ResponseEntity<>(HttpStatus.OK);
		return result;
	}

	@RequestMapping(value = "disLikeReply", method = RequestMethod.POST)
	public ResponseEntity<String> disLikeReply(@RequestParam("pid") Integer pid, @RequestParam("rid") Integer rid)
			throws Exception {
		rService.downReply(rid);

		ResponseEntity<String> result = new ResponseEntity<>(HttpStatus.OK);
		return result;
	}

	// 댓글 갱신
	private String replyHTML(int pid, UserVO login) throws Exception {
		List<ReplyVO> list = rService.listReply(pid);
		String msg = "";
		for (ReplyVO replyVO : list) {
			msg += "<tr>";
			msg += "<td><b style='font-family: '돋움체'; color: navy; position: relative; padding-left: 10px'>"
					+ replyVO.getNickname() + "</b></td>";
			msg += "<td><p style='color: gray;'>" + sdf.format(replyVO.getRegdate()) + "</p></td>";

			msg += "<td>"
					+ (login.getNickname().equalsIgnoreCase(replyVO.getNickname()) == true
							? "<div class='dropdown' style='float: right'>"
									+ "<button class='dropdown-toggle' data-toggle='dropdown' style='float: right; background-color: white; border: none;'>"
									+ "<i class='fa fa-ellipsis-v ' aria-hidden='true' style='float: right; padding-top: 3px'></i>"
									+ "</button>" + "<ul class='dropdown-menu'>" + "<li><a onclick='updateReply("
									+ replyVO.getRid() + "," + pid + ")'>수정</a></li>" + "<li><a onclick='deleteReply("
									+ replyVO.getRid() + "," + pid + ")'>삭제</a></li>" + "</ul>" + "</div>"
							: login.getGrade() == 99 ? "<div class='dropdown' style='float: right'>"
									+ "<button class='dropdown-toggle' data-toggle='dropdown' style='float: right; background-color: white; border: none;'>"
									+ "<i class='fa fa-ellipsis-v ' aria-hidden='true' style='float: right; padding-top: 3px'></i>"
									+ "</button>" + "<ul class='dropdown-menu'>" + "<li><a onclick='updateReply("
									+ replyVO.getRid() + "," + pid + ")'>수정</a></li>" + "<li><a onclick='deleteReply("
									+ replyVO.getRid() + "," + pid + ")'>삭제</a></li>" + "</ul>" + "</div>"
									: "<div class='dropdown' style='float: right'>"
											+ "<button class='dropdown-toggle' data-toggle='dropdown' style='float: right; background-color: white; border: none;'>"
											+ "<i class='fa fa-ellipsis-v ' aria-hidden='true' style='float: right; padding-top: 3px'></i>"
											+ "</button>" + "<ul class='dropdown-menu'>"
											+ "<li><a href=''>스팸신고</a></li>" + "</ul>" + "</div>")
					+ "</td>";
			msg += "<td><span class='badge' style='float: right; width: 40px;' id='dislikeCnt" + replyVO.getRid() + "'>"
					+ replyVO.getDown()
					+ "</span> <span class='glyphicon glyphicon-thumbs-down' data-toggle='tooltip' title='이 글이 싫어요!' style='width: 15px; float: right;' onclick='disLikeReply("
					+ replyVO.getRid() + "," + pid
					+ ")'></span> <span style='float: right;'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span> <span class='badge' style='float: right; width: 40px;' id='likeCnt"
					+ replyVO.getRid() + "'>" + replyVO.getUp()
					+ "</span> <span class='glyphicon glyphicon-thumbs-up' data-toggle='tooltip' title='이 글이 좋아요!' style='width: 15px; float: right;' onclick='likeReply("
					+ replyVO.getRid() + "," + pid + ")'></span></td>";
			msg += "<td style='border-top: none;'></td><td colspan='2' style='border-top: none; font-family: '맑은고딕';'>"
					+ replyVO.getContent() + "</td>";
			msg += "</tr>";
		}
		return msg;
	}

	@RequestMapping(value = "bookmark", method= RequestMethod.POST)
	public String bookmark(@RequestParam("pid")Integer pid, @RequestParam("uid")Integer uid, Model model) throws Exception{
		
		service.addBookmark(pid, uid);
		System.out.println("ViewController - Bookmark"+"pid="+pid+"uid="+uid);
		model.addAttribute("pid", pid);
		model.addAttribute("postVO", service.readPost(pid));
		model.addAttribute("rList", rService.listReply(pid));
		return "view/view";
	}
}