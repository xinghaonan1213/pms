package com.xhn.cust.controller;

import com.xhn.cust.bean.Customer;
import com.xhn.cust.service.CustomerService;
import com.xhn.utils.ExcelUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@RequestMapping("/cust")
public class CustomerController {

    @Autowired
    private  CustomerService customerService;

    //实现Excel的文件上传
    @RequestMapping(value = "/importExcel",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> importExcel(MultipartFile excel){
        Map<String,Object> map=new HashMap<String, Object>();
        List<Customer> customers=new ArrayList<Customer>();
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        try {
            //获取Excel
            Workbook wb = WorkbookFactory.create(excel.getInputStream());

            for (int k=0;k<wb.getNumberOfSheets();k++){
                //获取单元格
                Sheet sheet = wb.getSheetAt(k);
                if (sheet == null){
                    break;
                }
                for (int i = sheet.getFirstRowNum()+1; i <sheet.getLastRowNum() ; i++) {
                    //获取行号
                    Row row = sheet.getRow(i);
                    Customer customer=new Customer();
                    //获取每行的数据对应的列
                    if (row != null){
                        double id = row.getCell(0).getNumericCellValue();
                        System.out.println(id);
                        String linkMan = row.getCell(1).getStringCellValue();
                        customer.setCompanyperson(linkMan);
                        String companyName = row.getCell(2).getStringCellValue();
                        customer.setComname(companyName);

                        Date dateCellValue = row.getCell(3).getDateCellValue();
                        String format = sdf.format(dateCellValue);
                        Date date = sdf.parse(format);
                        customer.setAddtime(date);

                        double value = row.getCell(4).getNumericCellValue();
                        BigDecimal decimal=new BigDecimal(String.valueOf(value));
                        customer.setComphone(decimal.toPlainString());
                       /* //遍历每一行下的每一列数据
                        for (int j = row.getFirstCellNum(); j < row.getLastCellNum(); j++) {
                            //获取行对应的列
                            Cell cell = row.getCell(j);
                            String value= ExcelUtils.parseExcelValueToString(cell);
                            if (i>0&&j==0){
                                value=value.substring(0,value.indexOf("."));
                            }
                            System.out.print(value+"  ");
                        }*/
                        System.out.println();
                    }
                    customers.add(customer);
                }
            }
            int row=customerService.batchInsert(customers);
            if (row >0){
                map.put("statusCode",200);
                map.put("message","上传成功");
            }
        } catch (Exception e) {
            map.put("statusCode",500);
            map.put("message","上传失败");
            e.printStackTrace();
        }
        return map;
    }

    //导出Excel表格
    @RequestMapping(value = "/exportExcel",method = RequestMethod.GET)
    @ResponseBody
    public Map<String,Object> exportExcel(){
        //先创建对应的Excel表格
        Workbook wb = new HSSFWorkbook();
        //创建对应的单元格
        Sheet sheet = wb.createSheet("customers");
        //先获取到集合下的所有数据
        List<Customer> customerList = customerService.getCustomerList();
        //创建单元格下的第一行  设置标题
        Row row = sheet.createRow(0);
        //第一行下的对应的每一列
        Cell[] cells=new HSSFCell[5];
        for (int i = 0; i < 5 ; i++) {
            cells[i]=row.createCell(i);
        }
        cells[0].setCellValue("ID");
        cells[1].setCellValue("联系人");
        cells[2].setCellValue("公司名称");
        cells[3].setCellValue("添加时间");
        cells[4].setCellValue("联系电话");

        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        //开始设置数据
        for (int i = 0; i < customerList.size(); i++) {
            //第一条记录 一行
            Customer customer = customerList.get(i);
            Row row1 = sheet.createRow(i + 1);
            //将对应的数据5条添加到Excel中
            Cell[] cell=new HSSFCell[5];
            for (int j = 0; j < 5 ; j++) {
                cell[j]=row1.createCell(j);
            }
            cell[0].setCellValue(customer.getId());
            cell[1].setCellValue(customer.getCompanyperson());
            cell[2].setCellValue(customer.getComname());

            String format = sdf.format(customer.getAddtime());
            cell[3].setCellValue(format);
            cell[4].setCellValue(customer.getComphone());
        }
        FileOutputStream fos=null;
        try  {
            fos = new FileOutputStream("D:\\Desktop\\workbook.xls");
            wb.write(fos);
        }catch (IOException ex){
            ex.getMessage();
        }finally {
            if (fos != null){
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        Map<String,Object> map=new HashMap<String, Object>();
        map.put("code",200);
        map.put("message","下载成功");
        return map;
    }

    //发送异步请求获取查询的数据
    @RequestMapping(value = "/jsonList",method = RequestMethod.GET)
    @ResponseBody
    //显示主界面
    public List<Customer> getJsonCustomerList(){
        List<Customer> list=customerService.getCustomerList();
        return  list;
    }
    //查询某条数据
    @RequestMapping(value = "/info/{id}",method = RequestMethod.GET)
    @ResponseBody
    public Customer info(@PathVariable("id") Integer id){
        Customer customer=customerService.detail(id);
        return customer;
    }

    @RequestMapping(value = "/search",method = RequestMethod.GET)
    //搜索显示对应的数据
    public ModelAndView search(Integer cid,String keyword,Integer orderby){
        List<Customer> list=customerService.search(cid,keyword,orderby);
        ModelAndView mv=new ModelAndView("customer");
        mv.addObject("customerList",list);
        return mv;
    }

    //批量删除
    @RequestMapping(value = "/batchDel",method = RequestMethod.DELETE)
    @ResponseBody
    public Map<String,Object> batchDel( @RequestParam("ids[]") Integer [] ids){
        boolean isSuccess=customerService.batchDel(ids);
        Map<String,Object> map=new HashMap<String, Object>();
        if (isSuccess){
            map.put("statusCode",200);
            map.put("message","删除成功");
        }else{
            map.put("statusCode",500);
            map.put("message","删除失败");
        }

            return map;
    }


    //修改（编辑）某条数据 第一步先查询实现数据的回填
    @RequestMapping(value = "/edit/{id}",method = RequestMethod.GET)
    public String edit(@PathVariable("id" ) Integer id,Map<String,Object> map){
        Customer customer=customerService.edit(id);
        map.put("customer",customer);
        return "customer-edit";
    }

    //修改（编辑）第二步实现修改操作
    @RequestMapping(value = "/update",method = RequestMethod.PUT)
    public String update(Customer customer){
        customerService.update(customer);
        return "redirect:/cust/list";
    }
    //查询某条数据
    @RequestMapping(value = "/detail/{id}",method = RequestMethod.GET)
    public String detail(@PathVariable("id") Integer id, Map<String,Object> map){
        Customer customer=customerService.detail(id);
        map.put("customer",customer);
        return "customer-look";
    }

    @RequestMapping(value = "/list",method = RequestMethod.GET)
    //显示主界面
    public ModelAndView getCustomerList(){
        List<Customer> list=customerService.getCustomerList();
        ModelAndView mv=new ModelAndView("customer");
        mv.addObject("customerList",list);
        return mv;
    }
    //添加用户
    @RequestMapping(value = "/insert",method = RequestMethod.POST)
    public String insertCust(Customer customer){
        customerService.insertCust(customer);
        return "redirect:/cust/list";
    }
}
