package co.company.spring.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
@Service
@Component
public class EmpDAO {
	@Autowired DataSource ds;
	Connection conn;
	PreparedStatement pstmt;
	final String SELECT ="select * from employees";
	public ArrayList<Emp> getList(){
		ArrayList<Emp> list = new ArrayList<Emp>();
		try {
			conn=ds.getConnection();
			pstmt = conn.prepareStatement(SELECT);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				Emp emp = new Emp();
				emp.setFirstName(rs.getString("first_name"));
				emp.setLastName(rs.getString("last_name"));
				emp.setEmployeeId(rs.getString("employee_id"));
				list.add(emp);
				}
			}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			}catch (Exception e) {
				// TODO: handle exception
			}
		}
		return list;
	}
	
	
//	final String INSERT ="insert into employees(EMPLOYEE_ID,LAST_NAME,HIRE_DATE,EMAIL,JOB_ID) VALUES(?,?,?,SYSDATE,'IT_PROG')";
//	public int empInsert(Emp emp){
//		ArrayList<Emp> list = new ArrayList<Emp>();
//		try {
//			conn=ds.getConnection();
//			pstmt = conn.prepareStatement(INSERT);
//			ResultSet rs = pstmt.executeQuery();
//			while(rs.next()) {
//				Emp emp = new Emp();
//				emp.setFirstName(rs.getString("first_name"));
//				emp.setLastName(rs.getString("last_name"));
//				emp.setEmployeeId(rs.getString("employee_id"));
//				list.add(emp);
//				}
//			}catch (Exception e) {
//			// TODO: handle exception
//			e.printStackTrace();
//		} finally {
//			try {
//				conn.close();
//			}catch (Exception e) {
//				// TODO: handle exception
//			}
//		}
//		return list;
//	}
}
