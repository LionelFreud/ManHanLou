package com.yyedu.bean;

public class DiningTable {
    private Integer id;
    private String state;
    private String ordername;
    private String ordertel;

    public DiningTable() {
    }

    public DiningTable(Integer id, String state, String ordername, String ordertel) {
        this.id = id;
        this.state = state;
        this.ordername = ordername;
        this.ordertel = ordertel;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getOrdername() {
        return ordername;
    }

    public void setOrdername(String ordername) {
        this.ordername = ordername;
    }

    public String getOrdertel() {
        return ordertel;
    }

    public void setOrdertel(String ordertel) {
        this.ordertel = ordertel;
    }
}
