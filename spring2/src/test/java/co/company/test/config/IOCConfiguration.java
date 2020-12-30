package co.company.test.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
@ComponentScan(basePackages = {"co.company.test"})
public class IOCConfiguration {
//	// componentScan 걸어놨는데 왜 빈등록하냐?
//	// 우리가 등록한 클래스가 아니라, 메이븐에 의해 등록된 jar 파일안에 있는 클래스는 스캔할수가 없어서 수동으로 등록해줘야함
//	
//	@Bean(initMethod = "init", destroyMethod = "destroy") // 객체 초기화와 소멸
//	@Scope("prototype") // 프로토타입은 요청할때마다 객체가 새로 생성. 그걸 생략하면 기본은 singletone, request, session
//	public TV tv() {
//		return new TV();
//	}
}
