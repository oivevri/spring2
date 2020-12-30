package co.company.spring;

import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import co.company.spring.config.DBConfiguration;
import co.company.spring.config.MybatisConfiguration;
import co.company.spring.dao.EmpMapper;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { DBConfiguration.class,MybatisConfiguration.class } )
public class EmpDaoMybatisClient2 {
@Autowired EmpMapper empDAO;

//@Test
//public void empdaotest() {
//	EmpSearch empVO = new EmpSearch();
//	empVO.setMinSalary(0);
//	empVO.setMaxSalary(10000);
//	//empVO.setDepartmentId("20");
//	List<Emp>list=empDAO.getEmpList(empVO);
//	
//	for(Emp emp : list) {
//		System.out.println(emp.getEmployeeId()+" : "+emp.getFirstName());
//	}
//}
//@Test
//public void empdaotest2() {
//	Emp empVO = new Emp();
//	empVO.setEmployeeId("100011");
//	empVO.setLastName("CHOI");
//	empVO.setFirstName("JAEYEONG");
//	empVO.setJobId("IT_PROG");
//	empVO.setEmail("email11");
//	empVO.setHireDate(new Date(System.currentTimeMillis()));
//	empDAO.insertEmp(empVO);
//	
//	empVO.setEmployeeId("100122");
//	empVO.setLastName("CHOI1");
//	empVO.setFirstName("JAEYEONG1");
//	empVO.setJobId("IT_PROG");
//	empVO.setEmail("email22");
//	empVO.setHireDate(new Date(System.currentTimeMillis()));
//	empDAO.insertEmp(empVO);
//	
//}

//@Test
//public void empdaotest3() {
//	EmpSearch empVO = new EmpSearch();
//	empVO.setList(new String[]{"100011","100122"});
//	empDAO.deleteMultiEmp(empVO);
//	
//}

//@Test
//public void empdaotest4() {
//	Emp empVO = new Emp();
//	empVO.setLastName("CHOI");
//	empVO.setJobId("IT_PROG");
//	empVO.setEmail("email1331DWDWD");
//	empDAO.insertEmpProc(empVO);
//	
//	System.out.println(empVO.getEmployeeId()+empVO.getMsg());
//	
//}

//@Test
//public void empdaotest5() {
//	
//	
//	System.out.println(empDAO.getCount());
//	
//}

@Test
public void empdaotest6() {
	
List<Map<String,Object>> list =
	empDAO.getStatDept();
	System.out.println(list.get(0));
	
	
}

}

