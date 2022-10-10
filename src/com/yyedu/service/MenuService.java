package com.yyedu.service;

import com.yyedu.bean.DiningTable;
import com.yyedu.bean.Menu;
import com.yyedu.dao.MenuDAO;

import java.util.List;

public class MenuService {
    private MenuDAO m = new MenuDAO();

    public void show() {
        System.out.println("菜品编号\t\t菜品名\t\t类别\t\t价格");
        List<Menu> menus = m.queryMulti("select * from menu", Menu.class);
        for (Menu o : menus) {
            System.out.println(o.getId() + "\t\t\t" + o.getName() + "\t\t" + o.getType() + "\t\t" + o.getPrice());
        }
    }
}
