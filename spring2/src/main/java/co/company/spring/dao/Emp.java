package co.company.spring.dao;

import java.sql.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class Emp {
	@JsonProperty(value="empId")
    String employeeId;   
	String firstName;
	String lastName;
	String email;
	// @JsonFormat(pattern="yyyy/MM/dd HH:mm:ss")
	// @JsonFormat(shape=Shape.STRING) // json 포맷을 따라 바꾸는거
	@DateTimeFormat(pattern="yyyy-MM-dd") // 넘어오는 파라미터를 변환시킬때
	Date hireDate;	
	String jobId;	
	String departmentId;
	@JsonIgnore Integer salary;
	@JsonIgnore String msg;
}
