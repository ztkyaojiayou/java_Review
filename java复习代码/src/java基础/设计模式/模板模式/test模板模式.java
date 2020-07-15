package java基础.设计模式.模板模式;


public class test模板模式 {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        //制作红豆豆浆

        System.out.println("----制作红豆豆浆----");
        Milk redBeanMilk = new RedBeanMilk();
        redBeanMilk.make();

        System.out.println("----制作花生豆浆----");
        Milk peanutMilk = new PeanutMilk();
        peanutMilk.make();

        System.out.println("----制作纯豆浆----");
        Milk pureMilk = new PureMilk();
        pureMilk.make();
    }

}

