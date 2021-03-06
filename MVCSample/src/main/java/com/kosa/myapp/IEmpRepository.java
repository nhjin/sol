package com.kosa.myapp;

import java.util.*;

import org.apache.ibatis.annotations.Param;

public interface IEmpRepository {
	
	int getEmpCount(); //모든 사원의 수 조회
	int getEmpCount(@Param("deptid") int deptid); //지정한 부서의 사원 수 조회
	List<EmpVO> getEmpList(); //모든 사원의 정보 조회
	EmpVO getEmpInfo(int empid);//지정한 사원의 모든 정보 수정
	void updateEmp(EmpVO emp); //지정한 사원의 정보
	void insertEmp(EmpVO emp); //새로운 사원의 정보 입력
	void deleteJobHistory(int empid);//사원의 직무이력 변경내용 삭제
	void deleteEmp(@Param("empid") int empid, @Param("email") String email);
	List<Map<String, Object>> getAllDeptId();
	List<Map<String, Object>> getAllJobId();
	List<Map<String, Object>> getAllManagerId();

}
