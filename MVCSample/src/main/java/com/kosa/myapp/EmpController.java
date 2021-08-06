package com.kosa.myapp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class EmpController {

	@Autowired
	IEmpService empService;

		//http://localhost:8080/myapp/hr/count가 요청되면 empCount() 메서드가 실
		@RequestMapping(value="hr/count")
		public String empCount(@RequestParam(value="deptid", 
		required=false, defaultValue="0") int deptid, Model model) {
			System.out.println("/hr/count 요청됨");
			if(deptid==0) {
				model.addAttribute("count", empService.getEmpCount());
			}else {			
				int empCount = empService.getEmpCount(deptid);
				model.addAttribute("count", empCount);
			}
			return "/hr/count"; // WEB-INF/views/hr/count.jsp 파일이 있어야 함
		}
		
		@RequestMapping(value= {"/hr", "/hr/list"})
		public String getAllEmps(Model model) {
			List<EmpVO> empList = empService.getEmpList();
			model.addAttribute("empList",empList);
			//System.out.println(empList);
			return "hr/list";
			
		}
		//사원번호로 사원정보 상세조회
		@RequestMapping(value="/hr/{employeeId}")
		public String getEmpInfo(@PathVariable int employeeId, Model model) {
			EmpVO emp = empService.getEmpInfo(employeeId);
			model.addAttribute("emp", emp);
			return "hr/view";
		}
		
		//사원정보 입력
		@RequestMapping(value="/hr/insert", method=RequestMethod.GET)
		public String insertEmp(Model model) {
			model.addAttribute("deptList", empService.getAllDeptId());
			model.addAttribute("jobList", empService.getAllJobId());
			model.addAttribute("managerList", empService.getAllManagerId());
			return "hr/insertform";
			
		}//emp아이디로 간게 그 아이디로 가는건가? 가 아니라 vo야 이놈아 별칭이랑 헷갈려가지구
		
		//사원정보 디비로 넘기기
		@RequestMapping(value="/hr/insert", method=RequestMethod.POST)
		public String insertEmp(EmpVO emp) {
			empService.insertEmp(emp); 
			return "redirect:/hr";
		}
		
		//사원정보 수정
		@RequestMapping(value="/hr/update", method=RequestMethod.GET)
		public String updateEmp(int empid, Model model) {
			model.addAttribute("emp", empService.getEmpInfo(empid));
			model.addAttribute("deptList", empService.getAllDeptId());
			model.addAttribute("jobList", empService.getAllJobId());
			model.addAttribute("managerList", empService.getAllManagerId());
			return "hr/updateform";
		}
		
		//사원정보 수정 디비로 넘기기
		@RequestMapping(value="/hr/update", method=RequestMethod.POST)
		public String updateEmp(EmpVO emp) {
			empService.updateEmp(emp);
			return "redirect:/hr/" + emp.getEmployeeId();
		}
		
		//삭제하기 폼으로 가기 
		@RequestMapping(value="/hr/delete", method=RequestMethod.GET)
		public String deleteEmp(int empid, Model model) {
			model.addAttribute("emp", empService.getEmpInfo(empid));
			return "hr/deleteform";
		}
		//삭제디비에서 긁
		@RequestMapping(value="/hr/delete", method=RequestMethod.POST)
		public String deleteEmp(int empid, String email) {
			empService.deleteEmp(empid, email);
			return "redirect:/hr";
		}
	}
