package co.company.spring.users.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LoginController {

	// uri는 똑같은 /login 이지만 get과 post에 따라 기능이 다름
	@GetMapping("/login") // get 요청은 로그인페이지로 가는것
	public String loginForm() {
		return "user/login";
	}

	@PostMapping("/login") // post 요청은 로그인 처리
	public String login(HttpSession session) {
		// 세션에 담기
		session.setAttribute("loginId", "user");
		// 여기서 name 에 들어갈 값은 AuthCheckInterceptor.java의 preHandle 메소드에서
		// String id = (String) session.getAttribute("loginId"); 라고 담아준 그 이름

		return "redirect:/empSelect"; // 사원목록으로 이동
	}

	// 로그아웃
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		// 세션 무효화
		session.invalidate(); // 로그아웃처리
		return "user/login";
	}
}
