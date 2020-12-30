package co.company.spring.controller;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import co.company.spring.dao.Emp;

public class EmpValidator implements Validator {
	// 이메일 정규표현식
	public final static String emailRegExp = "^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$";
	
	// 자바유틸 패턴
	public Pattern pattern;
	
	// 패턴객체 생성 -> pattern 안에 email 정규표현식을 컴파일해줌
	public EmpValidator() {
		pattern = Pattern.compile(emailRegExp);
	}
	
	@Override
	public boolean supports(Class<?> clazz) {
		return Emp.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		Emp emp = (Emp)target;
		
		// 널체크 각각하기 -> 귀찮다..
		if(emp.getLastName() == null || emp.getLastName().trim().isEmpty()) {
			errors.rejectValue("lastName", "required", new Object[]{"lastName"},"");
		}
		
		// 널체크 제공 (에러, 검사대상, 에러코드, 메세지)
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "jobId", "required", new Object[]{"jobId"},"");
		
		// 이메일 형식이 맞는지 체크(@.) -> 패턴매치
		if(emp.getEmail() == null || emp.getEmail().trim().isEmpty()) {
			// {0}번 자리에 email 넣어준거.. 
			errors.rejectValue("email", "required", new Object[]{"email"},"");
		} else {
			Matcher matcher = pattern.matcher(emp.getEmail());
			if(! matcher.matches()) {
				errors.rejectValue("email", "bad");
			}
		}
	}

}
