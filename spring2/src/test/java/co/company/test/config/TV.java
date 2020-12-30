package co.company.test.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component // 빈 등록 외에도 컨트롤러, 서비스, 레포지터리 등 해당클래스의 형태에 맞게 알아서 맞춰줌
public class TV {
	@Autowired
	@Qualifier("apple")
	// @Resource(name = "apple")
	Speaker speaker;
	
	public void volumeup() {
		speaker.volumeup();
	}
	
	// 생성자
	public TV() {
		System.out.println("TV 생성자");
	}
	public void init() {
		System.out.println("객체 생성됨");
	}
	public void destroy() {
		System.out.println("객체 소멸됨");
	}
}
