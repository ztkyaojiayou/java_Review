package java基础.内部类;

abstract class Person {
    //抽象类
    public abstract void eat();
//    //抽象类中可以写实体方法（jdk-8以后都支持）
//    public void drink(){
//        return;
//    }

}

public class TestAnnoInnerClass {
    public static void main(String[] args) {
//        //匿名内部类的使用
//        new Person() {
//            @Override
//            public void eat() {
//                System.out.println("吃吃吃！！！！");
//            }
//        }.eat();
//
//        //常规继承
//        Tom tom = new Tom();
//        tom.eat();
//    }

        //thread的使用
        new Thread() {
            @Override
            public void run() {
                System.out.println("11111");
            }
        }.run();
    }

}

class Tom extends Person {
    @Override
    public void eat() {
        System.out.println("Tom吃吃吃");
    }
}
