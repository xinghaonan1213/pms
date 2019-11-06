package com.xhn.cust.service;

import com.xhn.cust.bean.Customer;
import org.springframework.stereotype.Service;

import java.util.List;

public interface CustomerService {
    public void insertCust(Customer customer);

    public List<Customer> getCustomerList();

    Customer detail(Integer id);

    Customer edit(Integer id);

    void update(Customer customer);

    boolean batchDel(Integer [] ids);

    List<Customer> search(Integer cid, String keyword, Integer orderby);

    int batchInsert(List<Customer> customers);
}
