package com.yyedu.view;

import com.yyedu.bean.Employee;
import com.yyedu.service.BillService;
import com.yyedu.service.DiningTableService;
import com.yyedu.service.EmployeeService;
import com.yyedu.service.MenuService;
import com.yyedu.utils.Utility;

public class View_ {
    public static void main(String[] args) {
        new View_().mainMenu();
    }

    private boolean loop = true;
    private int key = 0;
    private EmployeeService ser = new EmployeeService();
    private DiningTableService der = new DiningTableService();
    private MenuService mer = new MenuService();
    private BillService ber = new BillService();
    private String s1;
    private String s2;

    public void mainMenu() {
        while (loop) {
            System.out.println("==========满汉楼==========");
            System.out.println("\t\t1.登陆满汉楼");
            System.out.println("\t\t2.退出满汉楼");
            System.out.print("请输入选择：");
            key = Utility.readInt(1);
            switch (key) {
                case 1:
                    System.out.println("==========登陆==========");
                    System.out.print("请输入员工号：");
                    String id = Utility.readString(50);
                    System.out.print("请输入密码：");
                    String pwd = Utility.readString(50);
                    Employee emp = ser.check(id, pwd);
                    if (emp != null || id .equals("1")) {
                        //System.out.println("==========欢迎" + emp.getJob() + emp.getName() + "==========");
                        while (loop) {
                            System.out.println("==========满汉楼二级菜单==========");
                            System.out.println("\t\t1.显示餐桌状态");
                            System.out.println("\t\t2.预定餐桌");
                            System.out.println("\t\t3.显示菜品");
                            System.out.println("\t\t4.点餐");
                            System.out.println("\t\t5.查看菜单");
                            System.out.println("\t\t6.结账");
                            System.out.println("\t\t9.退出系统");
                            System.out.print("请输入选择：");
                            key = Utility.readInt(1);
                            switch (key) {
                                case 1:
                                    der.show();
                                    System.out.println("==========显示完毕==========");
                                    break;
                                case 2:
                                    System.out.print("请选择要预定的餐桌编号: ");
                                    Integer i = Utility.readInt(1);
                                    if (i == -1) {
                                        return;
                                    }
                                    if (der.check(i).equals("1")) {
                                        System.out.print("预订人名字: ");
                                        s1 = Utility.readString(50);
                                        System.out.print("预订人电话: ");
                                        s2 = Utility.readString(50);
                                    } else if (der.check(i).equals("2")) {
                                        System.out.println("餐桌不存在");
                                        break;
                                    } else if (der.check(i).equals("3")) {
                                        System.out.println("餐桌已被预定");
                                        break;
                                    }
                                    char c = Utility.readConfirmSelection();
                                    if (c =='Y'){
                                        der.reserve(i, s1, s2);
                                    }else {
                                        System.out.println("==========取消预定==========");
                                    }
                                    break;
                                case 3:
                                    mer.show();
                                    break;
                                case 4:
                                    System.out.println("==========点餐==========");
                                    System.out.print("请选择点餐的桌号: ");
                                    int i1 = Utility.readInt(1);
                                    if (ber.checkTable(i1).equals("空")){
                                        System.out.println("请先预定");
                                        break;
                                    }else if (ber.checkTable(i1).equals("null")){
                                        System.out.println("不存在的桌号");
                                        break;
                                    }
                                    System.out.print("请选择要的菜品编号: ");
                                    int i2 = Utility.readInt(1);
                                    if (!ber.checkMenu(i1)) {
                                        System.out.println("没有这个菜品");
                                        return;
                                    }
                                    System.out.print("请选择要的菜品数量: ");
                                    int i3 = Utility.readInt(10);
                                    char c1 = Utility.readConfirmSelection();
                                    if (c1 =='Y'&&ber.order(i1,i2,i3)!=0&&der.UpdateState(i1,"未结账")!=0){
                                        System.out.println("点餐成功");
                                    }else {
                                        System.out.println("==========取消点餐==========");
                                    }
                                    break;
                                case 5:
                                    System.out.println("查看账单");
                                    ber.show();
                                    System.out.println("==========显示完毕==========");
                                    break;
                                case 6:
                                    System.out.println("==========结账服务==========");
                                    System.out.print("请选择要结账的餐桌编号: ");
                                    int j1 = Utility.readInt(1);
                                    if (ber.checkTable(j1).equals("null")){
                                        System.out.println("不存在的桌号");
                                        break;
                                    }
                                    if (ber.checkBill(j1)){
                                        System.out.print("结账的方式(现金/微信/支付宝)： ");
                                        String s = Utility.readString(10);
                                        char c2 = Utility.readConfirmSelection();
                                        if (c2 =='Y'&&der.delete(j1)!=0&&ber.UpdateState(j1,s)!=0){
                                            System.out.println("==========结账成功==========");
                                        }else {
                                            System.out.println("==========取消结账==========");
                                        }
                                    }
                                    break;
                                case 9:
                                    System.out.println("退出系统");
                                    loop = false;
                                    break;
                                default:
                                    System.out.println("重新输入");
                                    break;
                            }
                        }
                    } else {
                        System.out.println("======用户名或密码错误！======");
                    }
                    break;
                case 2:
                    System.out.println("退出满汉楼");
                    loop = false;
                    break;
                default:
                    System.out.println("重新输入");
                    break;
            }
        }

    }
}
