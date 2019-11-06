package com.xhn.sys.bean;

public class EmployeeRole {
    private  Integer eid;
    private Integer rid;

    public Integer getEid() {
        return eid;
    }

    public void setEid(Integer eid) {
        this.eid = eid;
    }

    public Integer getRid() {
        return rid;
    }

    public void setRid(Integer rid) {
        this.rid = rid;
    }

    @Override
    public String toString() {
        return "EmployeeRole{" +
                "eid=" + eid +
                ", rid=" + rid +
                '}';
    }

    public EmployeeRole(Integer eid, Integer rid) {
        this.eid = eid;
        this.rid = rid;
    }

    public EmployeeRole() {
        super();
    }
}
