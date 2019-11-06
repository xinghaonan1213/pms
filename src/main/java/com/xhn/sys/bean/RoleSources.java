package com.xhn.sys.bean;

public class RoleSources {

    private Integer rid;
    private Integer sid;

    public Integer getRid() {
        return rid;
    }

    public void setRid(Integer rid) {
        this.rid = rid;
    }

    public Integer getSid() {
        return sid;
    }

    public void setSid(Integer sid) {
        this.sid = sid;
    }

    public RoleSources(Integer rid, Integer sid) {
        this.rid = rid;
        this.sid = sid;
    }

    public RoleSources() {
        super();
    }

    @Override
    public String toString() {
        return "RoleSources{" +
                "rid=" + rid +
                ", sid=" + sid +
                '}';
    }
}
