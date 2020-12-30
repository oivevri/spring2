package co.company.spring.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

@Component
public class EmpDAOJdbcTemplate {
   // 쿼리는 필요하니까 가져오기
   final String SELECT = "SELECT * FROM EMPLOYEES";
   final String INSERT = "INSERT INTO EMPLOYEES(" + "EMPLOYEE_ID, LAST_NAME, EMAIL, HIRE_DATE, JOB_ID) "
         + "VALUES(?,?,?,SYSDATE,'IT_PROG')";

   @Autowired
   private JdbcTemplate jdbcTemplate;

   public List<Map<String, Object>> getListMap() {
      return jdbcTemplate.queryForList(SELECT);
   }

   public void insert(Emp emp) {
      // jdbcTemplate.update(INSERT, emp.getEmployeeId(), emp.getLastName(),
      // emp.getEmail()); // 이렇게 3개 넣어줘도 되고
      // 매개변수들 넣은 배열 오브젝트 생성해도 됨
      Object[] param = new Object[] { emp.getEmployeeId(), emp.getLastName(), emp.getEmail() };
      jdbcTemplate.update(INSERT, param);
   }

   // p.192
   public List<Emp> getList() {
      return jdbcTemplate.query(SELECT, new RowMapper<Emp>() {

         @Override
         public Emp mapRow(ResultSet rs, int rowNum) throws SQLException {
            Emp emp = new Emp();
            emp.setFirstName(rs.getString("first_name"));
            emp.setLastName(rs.getString("last_name"));
            emp.setEmployeeId(rs.getString("employee_id"));
            return emp;
         }
      });
   }
}