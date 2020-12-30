package co.company.spring;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import co.company.spring.config.DBConfiguration;
import co.company.spring.dao.Emp;
import co.company.spring.dao.EmpDAOJdbcTemplate;



@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { DBConfiguration.class } )
public class jdbcTemplateClient {
	@Autowired EmpDAOJdbcTemplate empDAOjdbcTemplate;
	@Test
	public void empdaotest() {
		System.out.println(empDAOjdbcTemplate.getListMap());
	}
	
	@Test
	public void empdaotest2() {
		Emp emp = new Emp();
		emp.setEmployeeId("1002");
		emp.setEmail("chooo0601@naver.com");
		emp.setLastName("jaeyoung");
		empDAOjdbcTemplate.insert(emp);
	}
}
