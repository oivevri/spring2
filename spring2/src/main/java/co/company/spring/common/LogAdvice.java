package co.company.spring.common;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Component // 빈<bean> 등록
@Aspect // <aop:aspect>
public class LogAdvice { // 어드바이스 클래스 생성
	@Pointcut("execution(* co.company.spring..*Impl.*(..))")
	public void allpointcut() {
		
	}
	
	@Before("allpointcut()") // 이게 <aop:before>
	public void printLog(JoinPoint jp) {
		String method = jp.getSignature().getName();
		Object[] param = jp.getArgs();
		System.out.println("[공통로그] before "
				+ method
				+ "\n"
				+ ((param != null && param.length>0 )? param[0] : "null" ));
	}
}
