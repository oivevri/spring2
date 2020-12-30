package co.company.spring.emp.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.company.spring.dao.Department;
import co.company.spring.dao.Emp;
import co.company.spring.dao.EmpMapper;
import co.company.spring.dao.EmpSearch;
import co.company.spring.dao.Job;
import co.company.spring.emp.service.EmpService;

@Service
public class EmpServiceImpl implements EmpService{

	@Autowired EmpMapper dao;
	
	@Override
	public List<Emp> getEmpList(EmpSearch emp) {
		return dao.getEmpList(emp);
	}

	@Override
	public Emp getEmp(Emp emp) {
		return dao.getEmp(emp);
	}

	@Override
	public int getCount(EmpSearch emp) {
		return dao.getCount(emp);
	}

	@Override
	public int insertEmp(Emp emp) {
		return dao.insertEmp(emp);
	}

	@Override
	public void insertEmpProc(Emp emp) {
		dao.insertEmpProc(emp);
	}

	@Override
	public int updateEmp(Emp emp) {
		return dao.updateEmp(emp);
	}

	@Override
	public int deleteEmp(Emp emp) {
		return dao.deleteEmp(emp);
	}

	@Override
	public int deleteMultiEmp(EmpSearch emp) {
		return dao.deleteMultiEmp(emp);
	}

	@Override
	public List<Job> jobSelect() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Department> departmentSelect() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Map<String, Object>> getStatDept() {
		return dao.getStatDept();
	}

	
}
