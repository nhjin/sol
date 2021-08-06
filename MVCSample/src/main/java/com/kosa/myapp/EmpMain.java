package com.kosa.myapp;

import java.util.List;
import java.util.Map;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;
//컨트롤러대신 메인에다가 다 때려박음
public class EmpMain {
	public static void main(String[] args) {
		AbstractApplicationContext context = 
				new GenericXmlApplicationContext("application-config.xml");
		IEmpService service = context.getBean(IEmpService.class);

	//	int empCount = service.getEmpCount();
	//	System.out.println("전체 사원의 수 : " + empCount);
		System.out.println("50번부서 인원  : "+service.getEmpCount(50));
		System.out.println("==============================");

		System.out.println("전체사원정보조회");
		List<EmpVO>empList = service.getEmpList();
		for(EmpVO emp:empList) {
			System.out.println(emp);
		}
//		System.out.println(service.getEmpList());

		System.out.println("==============================");
		System.out.println("103번 사원의 정보를 출력");
		System.out.println(service.getEmpInfo(103));

		
//		System.out.println("==============================");
//		System.out.println("새로운 사원 정보를 추가");
//		EmpVO emp =	new	EmpVO();
//		emp.setEmployeeId(210);
//		emp.setFirstName("JinKyoung");
//		emp.setLastName("Heo");
//		emp.setEmail("www.w"); //이미 있던 정보가 들어가면서 
//		emp.setPhoneNumber("222-222");
////		java.sql.Date now = new java.sql.Date(2010,03,05); //이거 아냐...흑흑
////		emp.setHireDate(now);
//		emp.setJobId("IT_PROG");
//		emp.setSalary(8000);
//		emp.setCommissionPct(0.2);
//		emp.setManagerId(100);
//		emp.setDepartmentId(10);
//		try	{
//			System.out.println("정보가 들어가긴했닝?");
//			service.insertEmp(emp);
//			System.out.println("insert	ok");
//		}catch(Exception e)	{
//			System.out.println("오류메세지 : " + e.getMessage());
//		}
//		
		
//		System.out.println("==============================");
//		System.out.println("신규사원 정보를 조회");
//		EmpVO emp210 = service.getEmpInfo(210);
//		System.out.println(emp210);
//		System.out.println("==============================");
//		System.out.println("210번 사원의 급여 10퍼 상승");
//		emp210.setSalary(emp210.getSalary()*1.1);
//		System.out.println("==============================");
//		System.out.println("수정된 사원 정보 다시 출력");
//		System.out.println(emp210);
		System.out.println("==============================");
		System.out.println("210번 사원 삭제");
		try {
			service.deleteEmp(210, "www.w");
		}catch(RuntimeException e) {
			System.out.println(e.getMessage());
		}try {
			System.out.println(service.getEmpInfo(210));
		}catch(RuntimeException e){
			System.out.println("삭제할 사원정보가 없습니다.");
		}
		System.out.println("=============================");
		System.out.println("모든 부서번호와 부서이름을 출력 ");
		List<Map<String, Object>> empList2 = service.getAllDeptId();
		for(Map<String, Object> emp : empList2) {
			System.out.println(emp);
		}
		//System.out.println(service.getAllDeptId());
		System.out.println("==============================");
		System.out.println("모든 직무아이디와 직무타이틀을 출력 ");
		System.out.println(service.getAllJobId());
		System.out.println("==============================");
		System.out.println("모든 매니저번호와 매니저이름을 출력 ");
		System.out.println(service.getAllManagerId());
		context.close();
	}


}
