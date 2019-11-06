package com.xhn.sys.service;

import com.xhn.sys.bean.Employee;
import com.xhn.sys.bean.EmployeeExample;
import com.xhn.sys.mapper.EmployeeMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Resource
    private EmployeeMapper employeeMapper;

    public List<Employee> getEmpList() {
        EmployeeExample example = new EmployeeExample();
        EmployeeExample.Criteria criteria = example.createCriteria();
        criteria.andPFkEqualTo(4);
      return   employeeMapper.selectByExample(example);
    }

    public Employee login(Employee employee) {
        EmployeeExample example=new EmployeeExample();
        //example进行精确查询
        EmployeeExample.Criteria criteria = example.createCriteria();
        criteria.andUsernameEqualTo(employee.getUsername());
        criteria.andPasswordEqualTo(employee.getPassword());
        List<Employee> employees = employeeMapper.selectByExample(example);
        if (employees !=null && employees.size() > 0){
            employee = employees.get(0);
            return employee;
        }
        return null;
    }

    public List<Employee> getReceiver(Integer eid) {
        //需要使用多表联查，所以不能使用精确查找了
       return employeeMapper.getReceiver(eid);
    }

    public int saveInfo(Employee employee) {
        employeeMapper.insertEmp(employee);
        return employee.getEid();
    }
}
