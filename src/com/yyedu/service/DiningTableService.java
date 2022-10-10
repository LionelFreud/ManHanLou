package com.yyedu.service;

import com.yyedu.bean.DiningTable;
import com.yyedu.dao.DiningTableDAO;

import java.util.List;

public class DiningTableService {
    private DiningTableDAO d = new DiningTableDAO();

    public void show() {
        System.out.println("餐桌编号   餐桌状态");
        List<DiningTable> diningTables = d.queryMulti("select id,state from diningtable", DiningTable.class);
        for (DiningTable o : diningTables) {
            System.out.println(o.getId() + "          " + o.getState());
        }
    }

    public String check(int s) {
        long o1 =(Long) d.queryScalar("select count(*) from diningtable");
        if (s > o1){
            return "2";
        }
        String o = (String) d.queryScalar("select state from diningtable where id=?", s);
        if (o.equals("空")) {
            return "1";
        } else if (o.equals("已预定") || o.equals("在就餐")) {
            return "3";
        }
        return null;
    }

    public void reserve(int s, String s1, String s2) {
        int row = d.update("update  diningtable  set state = '已预定',ordername =?,ordertel =? where id =?", s1, s2, s);
        if (row != 0) {
            System.out.println("预定成功");
        } else {
            System.out.println("预定失败");
        }
    }
    public  int UpdateState(int a,String state){
        return d.update("update  diningtable  set state =? where id =?",state,a);
    }
    public  int delete(int id){
        return d.update("update  diningtable  set state ='空',ordername = '',ordertel = '' where id =?",id);
    }
}
