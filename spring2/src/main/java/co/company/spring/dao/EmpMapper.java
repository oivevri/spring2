package co.company.spring.dao;

import java.util.List;
import java.util.Map;

import co.company.spring.dao.Emp;

public interface EmpMapper {
	public List<Emp> getEmpList(EmpSearch emp); // 전체조회
	public Emp getEmp(Emp emp); // 단건조회
	
	public int getCount(EmpSearch emp); // 갯수
	
	public int insertEmp(Emp emp); // 등록
	public void insertEmpProc(Emp emp);
	
	public int updateEmp(Emp emp); // 수정
	
	public int deleteEmp(Emp emp); // 삭제
	public int deleteMultiEmp(EmpSearch emp);

	public List<Job> jobSelect();
	public List<Department> departmentSelect();
	public List<Map<String,Object>> getStatDept();
}

