package com.yyedu.service;

import com.yyedu.bean.Employee;
import com.yyedu.dao.EmployeeDAO;

public class EmployeeService {
    private EmployeeDAO e = new EmployeeDAO();
    public Employee check(String id, String pwd){
        Employee employee = e.querySingle("select * from employee where empid=? and pwd=md5(?)",Employee.class,id,pwd);
        return employee;
    }
}
