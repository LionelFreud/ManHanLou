package com.yyedu.service;

import com.yyedu.bean.Bill;
import com.yyedu.bean.Menu;
import com.yyedu.dao.BillDAO;
import com.yyedu.dao.DiningTableDAO;
import com.yyedu.dao.MenuDAO;

import java.util.List;
import java.util.UUID;

public class BillService {
    private BillDAO b = new BillDAO();
    private DiningTableDAO d = new DiningTableDAO();
    private MenuDAO m = new MenuDAO();
    public String checkTable(int i){
        long o1 =(Long) m.queryScalar("select count(*) from diningtable");
        if (i > o1){
            return "null";
        }
        return (String)d.queryScalar("select state from diningtable where id =?", i);
    }
    public boolean checkMenu(int s){
        long o1 =(Long) m.queryScalar("select count(*) from menu");
        if (s > o1){
            return false;
        }
        return true;
    }
    public int order(int a1,int a2,int a3){
        String ID = UUID.randomUUID().toString();
        double a4 = 0;
        a4 = (Double)m.queryScalar("select price from menu where id =?", a2) * a3;
       return b.update("insert into bill values (null,?,?,?,?,?,CURRENT_TIME,'未结账')",ID,a2,a3,a4,a1);
    }
    public void show() {
        System.out.println("编号\t\t菜品号\t\t菜品量\t\t金额\t\t桌号\t\t日期\t\t\t\t\t\t\t状态");
        List<Bill> bills = b.queryMulti("select * from bill", Bill.class);
        for (Bill o : bills) {
            System.out.println(o.getId() + "\t\t"+ o.getMenuid() + "\t\t\t" + o.getNums()
            +"\t\t\t" + o.getMoney()+"\t" + o.getDiningtableid()+"\t\t" + o.getBilldate()+"\t\t" + o.getState());
        }
    }
    public boolean checkBill(int s){
        Bill bill = b.querySingle("select * from bill where diningtableid =? and state ='未结账'",Bill.class, s);
        if (bill == null){
            System.out.println("请先点菜");
            return false;
        }
        return true;
    }
    public int UpdateState(int s,String state){
        return b.update("update  bill  set state =? where diningtableid =?",state,s);
    }
}
