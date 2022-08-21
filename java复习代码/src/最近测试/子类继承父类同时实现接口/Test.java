//package 最近测试.子类继承父类同时实现接口;
//
///**
// * @author :zoutongkun
// * @date :2022/8/16 1:32 下午
// * @description :
// * @modyified By:
// */
//
//import java.util.Scanner;
//
//public class Test {
//    public static void main(String[] args){
//        Extends_implements ei = new Extends_implements();
//        System.out.println("这是一家之中");
//        System.out.println("输入1查看人\n输入2查看猫\n输入3查看狗");
//        //导入扫描器，供用户输入选择
//        Scanner input = new Scanner(System.in);
//        int a = input.nextInt();
//        //调用switch——case选择结构
//        switch(a){
//            case 1:
//                ei.setname("人");
//                ei.setcolor("黄色");
//                ei.setage(15);
//                ei.setsex('男');
//                ei.setspeak("好好的");
//                ei.shuchu();
//                ei.money();
//                ei.love();
//                break;
//            case 2:
//                ei.setname("小猫");
//                ei.setcolor("白色");
//                ei.setage(2);
//                ei.setsex('母');
//                ei.setspeak("喵喵喵");
//                ei.shuchu();
//                ei.zhua();
//                break;
//            case 3:
//                ei.setname("小狗");
//                ei.setcolor("黑色");
//                ei.setage(3);
//                ei.setsex('公');
//                ei.setspeak("汪汪汪");
//                ei.shuchu();
//                ei.lookhome();
//                break;
//            default :
//                System.out.println("请按照提示正确输入");
//        }
//    }
//}
