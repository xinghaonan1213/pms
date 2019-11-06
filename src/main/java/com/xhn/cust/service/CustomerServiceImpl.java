package com.xhn.cust.service;

import com.xhn.cust.bean.Customer;
import com.xhn.cust.bean.CustomerExample;
import com.xhn.cust.mapper.CustomerMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerMapper customerMapper;
    public void insertCust(Customer customer) {
        customer.setAddtime(new Date());
        customerMapper.insert(customer);
    }

    public List<Customer> getCustomerList() {
       return customerMapper.selectByExample(new CustomerExample());
    }

    public Customer detail(Integer id) {
        Customer customer = customerMapper.selectByPrimaryKey(id);
        return customer;
    }

    public Customer edit(Integer id) {
        Customer customer = customerMapper.selectByPrimaryKey(id);
        return customer;
    }

    public void update(Customer customer) {
        customerMapper.updateByPrimaryKeySelective(customer);
    }

    public boolean batchDel(Integer [] ids) {
        List<Integer> list = Arrays.asList(ids);
        CustomerExample customerExample=new CustomerExample();
        CustomerExample.Criteria criteria = customerExample.createCriteria();
        criteria.andIdIn(list);
       int i= customerMapper.deleteByExample(customerExample);
        return ids.length==i;
    }

    public List<Customer> search(Integer cid, String keyword, Integer orderby) {
        CustomerExample example=new CustomerExample();
        CustomerExample.Criteria criteria = example.createCriteria();
            if (cid==0){
                criteria.andComnameLike("%"+keyword+"%");
                CustomerExample.Criteria criteria1 = example.createCriteria();
                criteria1.andCompanypersonLike("%"+keyword+"%");
                example.or(criteria1);
            }else if (cid == 1){
                criteria.andComnameLike("%"+keyword+"%");
            }else {
                criteria.andCompanypersonLike("%"+keyword+"%");
            }
            if (orderby == 1){
                //该方法设置为某字段排序
                example.setOrderByClause("id desc");
            }

        List<Customer> list = customerMapper.selectByExample(example);
        return list;
    }

    public int batchInsert(List<Customer> customers) {
        return customerMapper.batchInsert(customers);
    }


}
