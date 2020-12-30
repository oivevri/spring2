package co.company.spring.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class EmpDaoMybatis {
@Autowired SqlSession sqlsession;

public List<Emp> getEmpList(){
	return sqlsession.selectList("EmpDAO.getEmpList");
}

}
