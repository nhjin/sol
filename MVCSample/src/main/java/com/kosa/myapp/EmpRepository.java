package com.kosa.myapp;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

//@Repository
public class EmpRepository implements IEmpRepository {

	@Autowired
	JdbcTemplate jdbcTemplate;

	//DB에 있는 어떤 열을 Vo에 어떤 변수에 mapping해야할지 알려줘야 함 (만약 이게 없다면?, 이부분이랑 )
	//JdbcTemplate API 좀 더 조사해보기
	private class EmpMapper implements RowMapper<EmpVO>{

		@Override
		public EmpVO mapRow(ResultSet rs, int count) throws SQLException {
			EmpVO emp = new EmpVO();
			emp.setEmployeeId(rs.getInt("employee_id" ));
			emp.setFirstName(rs.getString("first_name"));
			emp.setLastName(rs.getString("last_name"));
			emp.setEmail(rs.getString("email"));
			emp.setPhoneNumber(rs.getString("phone_number"));
			emp.setHireDate(rs.getDate("hire_date"));

			emp.setJobId(rs.getString("job_id"));
			emp.setSalary(rs.getDouble("salary"));
			emp.setCommissionPct(rs.getDouble("commission_pct"));
			emp.setManagerId(rs.getInt("manager_id"));
			emp.setDepartmentId(rs.getInt("department_id"));
			return emp;
		}
	}

	//모든 사원의 수 조회
	@Override
	public int getEmpCount() {
		String sql = "select count(*) from employees";
		return jdbcTemplate.queryForObject(sql,Integer.class);
	}

	//지정한 부서의 사원 수 조회 //query메소드는 ?에 맵핑된 걸 쭉 나열하면 됨 
	@Override
	public int getEmpCount(int deptid) {
		String sql ="select count(*) from employees where department_id=?";
		return jdbcTemplate.queryForObject(sql,Integer.class, deptid);
	}

	// 모든 사원의 정보 조회
	@Override
	public List<EmpVO> getEmpList() {

		String sql = "select * from employees";
		return jdbcTemplate.query(sql, new EmpMapper());
	}

	// 사원의 아이디로 사원의 모든 정보 조회
	@Override
	public EmpVO getEmpInfo(int empid) {
		String sql = "select * from employees where employee_id = ?";
		return jdbcTemplate.queryForObject(sql, new EmpMapper(), empid);
	}

	// 지정한사원의 정보 수정 야 꼭 뭐가 잘못됐는지 꼭 찾아라 꼮 깨빢친다 
	//	@Override
	//	public void updateEmp(EmpVO emp) {
	//		String sql = "update employees," +
	//				"set first_name=?, last_name=?,	email=?," +
	//				"phone_number=?,"
	//				+ " hire_date=?, "
	//				+ "job_id=?," +
	//				"salary=?, commission_pct=?, manager_id=?," +
	//				"department_id=?	where	employee_id=?";
	//		jdbcTemplate.update(sql, emp.getFirstName(), emp.getLastName(),emp.getEmail(), emp.getPhoneNumber()
	//				,emp.getHireDate()
	//				,emp.getJobId(),emp.getSalary(),emp.getCommissionPct(),emp.getManagerId(),
	//				emp.getDepartmentId(),emp.getEmployeeId());
	//	}

	@Override
	public	void	updateEmp(EmpVO	emp)	{
		String	sql	=	"update	employees	"
				+	"set	first_name=?,	last_name=?,	email=?,	"
				+	"phone_number=?,	hire_date=?,	job_id=?,	"
				+	"salary=?,	commission_pct=?,	manager_id=?,	"
				+	"department_id=?	where	employee_id=?";
		jdbcTemplate.update(sql,	emp.getFirstName(),
				emp.getLastName(),
				emp.getEmail(),
				emp.getPhoneNumber(),
				emp.getHireDate(),
				emp.getJobId(),
				emp.getSalary(),
				emp.getCommissionPct(),
				emp.getManagerId(),
				emp.getDepartmentId(),
				emp.getEmployeeId()	
				);
	}

	// 새로운 사원 정보 저장 , hiredate가 없음
	//	@Override
	//	public void insertEmp(EmpVO emp) {
	//		String sql = "insert into employees (employee_id, first_name,last_name," + 
	//				"email, phone_number, hire_date, job_id, salary, commission_pct, manager_id, department_id)" + 
	//				"values"
	//				+ "(?,?,?,?,?,sysdate,?,?,?,?,?)";
	//		jdbcTemplate.update(sql,  emp.getEmployeeId(),emp.getFirstName(), emp.getLastName(),emp.getEmail(), emp.getPhoneNumber()
	//				,emp.getHireDate(), emp.getJobId(),emp.getSalary(),emp.getCommissionPct(),emp.getManagerId(),
	//				emp.getDepartmentId());
	//	}

	@Override
	public	void	insertEmp(EmpVO	emp)	{
		String	sql	=	"insert	into	employees	(employee_id,	first_name,	"
				+	"last_name,	email,	phone_number,	hire_date,	job_id,	"
				+	"salary,	commission_pct,	manager_id,	department_id)	"
				+	"values	(?,?,?,?,?,sysdate,?,?,?,?,?)";
		jdbcTemplate.update(sql,	
				emp.getEmployeeId(),	
				emp.getFirstName(),	
				emp.getLastName(),
				emp.getEmail(),
				emp.getPhoneNumber(),
				//emp.getHireDate(), 에라잌ㅋㅋㅋㅋ
				emp.getJobId(),
				emp.getSalary(),
				emp.getCommissionPct(),
				emp.getManagerId(),
				emp.getDepartmentId()
				);
	}

	// 사원의 직무 이력 변경내용 삭제 (서비스에선 없음) //
	@Override
	public void deleteJobHistory(int empid) {
		String sql = "delete from job_history where employee_id=?";
		jdbcTemplate.update(sql,empid);

	}
	// 사원의 정보 삭제
	@Override
	public void deleteEmp(int empid, String email) {
		String sql ="delete from employees where employee_id=? and email=?";
		jdbcTemplate.update(sql,empid,email);

	}

	//모든 부서번호와 부서이름을 조회 //**********rowmappter는 사용 안되나요?*************
	@Override
	public List<Map<String, Object>> getAllDeptId() {
		String sql="select department_id, department_name from departments";
		return jdbcTemplate.queryForList(sql);
	}

	//모든 직무아이디와 직무타이틀을 조회
	@Override
	public List<Map<String, Object>> getAllJobId() {
		String sql ="select job_id, job_title from jobs";
		return jdbcTemplate.queryForList(sql);
	}

	//모든 매니저아이디와 매니저 이름을 조회
	@Override
	public List<Map<String, Object>> getAllManagerId() {
		//		String sqlz = "select"
		//				+ "d.manager_id as managerId, e.first_name as firstName"
		//				+ "from departments d join employees e"
		//				+ "on d.manager_id = e.employee_id"
		//				+ "order by d.manager_id";
		//		
		//		String sql = "select"
		//				 + "d.manager_id as managerId, e.first_name as firstName"
		//				 + "from departments d	join employees e"
		//				 + "on d.manager_id = e.employee_id"
		//				 + "order by d.manager_id";
		//				 return	jdbcTemplate.queryForList(sql);
		//		
		String	sql	=	"select	"
				+	" d.manager_id	as	managerId,	e.first_name	as	firstName	"
				+	"		from	departments	d	join	employees	e	"
				+	" on	d.manager_id	=	e.employee_id	"
				+	"		order	by	d.manager_id";
		return	jdbcTemplate.queryForList(sql);
	}

}
