package co.company.spring.config;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import co.company.spring.common.AuthCheckInterceptor;

@Configuration
@ComponentScan(basePackages="co.company.spring")
@EnableWebMvc // 자바기반일때는 어노테이션만 해주면 되고 ___일때는 (annotation-driven) -> MessageConvert
@EnableAspectJAutoProxy(proxyTargetClass = true)
public class MvcConfiguration implements WebMvcConfigurer {
	// 책 p.323
	@Bean
	public MessageSource messageSource() {
		ResourceBundleMessageSource ms = new ResourceBundleMessageSource();
		ms.setBasenames("message.label"); // 경로 : 폴더명과 파일명
		ms.setDefaultEncoding("UTF-8");
		ms.setCacheSeconds(10); // 10초마다 메세지 새로고침
		return ms;
	}
	// 14장 인터셉터
	/**
	 * 언어 변경을 위한 인터셉터를 생성한다.
	 */
	@Bean
	public LocaleChangeInterceptor localeChangeInterceptor() {
	    LocaleChangeInterceptor interceptor = new LocaleChangeInterceptor();
	    interceptor.setParamName("lang");
	    return interceptor;
	}

	/**
	 * 변경된 언어 정보를 기억할 로케일 리졸퍼를 생성한다.
	 * 여기서는 세션에 저장하는 방식을 사용한다.
	 * @return
	 */
	@Bean
	public SessionLocaleResolver localeResolver() {
		return new SessionLocaleResolver();
	}

	/**
	 * 인터셉터를 등록한다. 몇개든 추가 등록 가능함
	 */
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
	    registry.addInterceptor(localeChangeInterceptor());
	    registry.addInterceptor(authCheckInterceptor()).addPathPatterns("/emp*");
	    // 이 패턴 patterns은 정규표현식을 써도 됨. 지금은 emp로 시작하는 모든 것을 인터셉터로 시작하겠다는말
	}
	
//책 47p  
//   @Bean//컨테이너에 객체를 등록해주는 어노테이션이 Bean임
//   public Greeter greeter() {
//      Greeter g = new Greeter();
//      g.setFormat("%s, 안녕하세요");
//      return g;
//   }

   @Override
   public void addResourceHandlers(ResourceHandlerRegistry registry) {
      registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
      registry.addResourceHandler("/images/**").addResourceLocations("/images/");
   }
   
   @Bean
   public AuthCheckInterceptor authCheckInterceptor() {
	   return new AuthCheckInterceptor();
   }
   
   @Bean
   public ViewResolver getViewResolver(){
      InternalResourceViewResolver resolver = new InternalResourceViewResolver();
      resolver.setPrefix("/WEB-INF/views/");
      resolver.setSuffix(".jsp");
      resolver.setOrder(3);
      return resolver;
   }
   
   @Bean CommonsMultipartResolver multipartResolver() {
	   CommonsMultipartResolver multi = new CommonsMultipartResolver();
	   multi.setMaxUploadSize(1024*10000); // byte 단위 -> 현재 10mb
	return multi;
   }
   
   @Override
   public void configureDefaultServletHandling(
         DefaultServletHandlerConfigurer configurer) {
      configurer.enable();
   }

//   @Override
//   public void configureViewResolvers(ViewResolverRegistry registry) { //우리는 이거 안쓰고 tilesview 쓸거임
//      registry.jsp("WEB-INF/views/",".jsp");
//   }
 
}