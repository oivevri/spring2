package co.company.spring.dao;

import lombok.Data;

@Data
public class EmpSearch {
	Integer minSalary;
	Integer maxSalary;
	Integer first;
	Integer last;
	String[] list;
	
}
