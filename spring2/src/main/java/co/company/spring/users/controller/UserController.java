package co.company.spring.users.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import co.company.spring.users.service.UserService;
import co.company.spring.users.service.UserVO;

@Controller
public class UserController {

	@Autowired UserService service;
	
	// 등록페이지로 이동
	@GetMapping("/userInsert")
	public String userInsertForm() {
		return "user/insert";
	}
	
	
	// 등록 처리
	@PostMapping("/userInsert")  // 매퍼에서 쓴 id랑 같아야함
	public String userInsert(HttpServletRequest request, UserVO user)
			throws IllegalStateException, IOException {
		// request multipart 로 캐스팅
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest)request;
		
		//이미지파일(첨부파일 읽어내기)
		MultipartFile multipartFile = multipartRequest.getFile("uploadFile");
		// insert.jsp에서 input type="file"의 name 속성이 "uploadFile"이었는데,
		// 그걸 multipartRequest.getFile에 적어줌
		
		if(! multipartFile.isEmpty() && multipartFile.getSize()>0) {
			
			// images 폴더 밑을 패스로 설정해서
			String path = request.getSession().getServletContext().getRealPath("/images");
			System.out.println(">>> path : " + path); // 패스확인용		
			multipartFile.transferTo(
							new File(path, // 그 패스path 에 저장하도록 설정 ??
							multipartFile.getOriginalFilename())); // 오리지널이름 : 업로드된 후의 이름
		user.setProfile(multipartFile.getOriginalFilename());
		}
		service.insertUser(user);
		return "user/insert";
	}

// 파일다운
	@RequestMapping("/filedown")
	public void filedown(HttpServletResponse response, 
			HttpServletRequest request,
			@RequestParam String uFile ) // 파라미터로 파일이름(uFile) 받기. 값이 여러개라면 Map으로 묶으면 됨
					throws IOException { 
		response.setContentType("application/octet-stream;charset=UTF-8");
		// 파일 이름 uFile에 인코딩해주기(너무길어서 따로 뺌)
		String fn = URLEncoder.encode(uFile, "utf-8");
		response.setHeader("Content-Disposition",
				"attachment;filename=\"" + fn + "\"");

		// ContentType이 jsp에서는 그냥 text/html 였는데, 응답으로 보낼때는 다운로드라고 해줘야함
		
		BufferedInputStream in = null;
		BufferedOutputStream out = null;
		try {
			String path = request.getSession().getServletContext().getRealPath("/images"); 
			in = new BufferedInputStream(new FileInputStream(path + "/" + uFile));
			// 이때 path는 파일 업로드한 경로라서, 다운받을 파일이랑 +로 같이써줘야함
			
			out = new BufferedOutputStream(response.getOutputStream());
			// response로 아웃풋 스트림보내면 그게 파일다운받기임
			FileCopyUtils.copy(in, out);
			out.flush();
		} catch (IOException ex) {
		} finally {
			in.close();
			response.getOutputStream().flush();
			response.getOutputStream().close();
		}
	}
	
// 단건조회
	@RequestMapping("/getUser")
	public String getUser(UserVO user, Model model) {
		model.addAttribute("user", service.getUser(user));
		return "user/user";
	}
}
