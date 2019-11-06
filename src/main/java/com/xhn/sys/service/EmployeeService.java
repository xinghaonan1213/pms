package com.xhn.sys.service;

import com.xhn.sys.bean.Employee;

import java.util.List;

public interface EmployeeService {
    List<Employee> getEmpList();

    Employee login(Employee employee);

    List<Employee> getReceiver(Integer eid);

    int saveInfo(Employee employee);
}
