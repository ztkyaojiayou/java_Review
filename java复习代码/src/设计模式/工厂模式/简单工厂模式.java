package 设计模式.工厂模式;

/**
 * （1）简单⼯⼚模式：⼀个抽象的接⼝，多个抽象接⼝的实现类，⼀个⼯⼚类，⽤来实例化抽象的接⼝
 *
 * 案例示范：这里我们写一个“用户只需要传入车的品牌就可以生产出该车的一个对象”的工厂
 */

// 1.车接口（⼀个抽象的接⼝）
interface Car {
     void run();//车启动
     void stop();//车停车
}
// 2.车的具体实现类，这里写了2个：即一个Benz车，一个Ford车（多个抽象接⼝的实现类）
//2.1Benz车
class Benz implements Car {
    public void run() {
        System.out.println("Benz开始启动了。。。。。");
    }
    public void stop() {
        System.out.println("Benz停⻋了。。。。。");
    }
}
//2.2Ford车
class Ford implements Car {
    public void run() {
        System.out.println("Ford开始启动了。。。");
    }
    public void stop() {
        System.out.println("Ford停⻋了。。。。");
    }
}

// 3.生产汽车的⼯⼚类（⼀个⼯⼚类，重点）
// 现在就是要在这个工厂类里面实现“只需要传入车名/类名即可得到该车的一个对象”，
// 也即把“new对象”的操作放在了这个工厂里面实现（这就是工厂模式的精髓）
class CarFactory {
    public static Car getCarInstance(String type) {//传入要生产的车的名字（即对应的类名）
        Car c = null;
        if ("Benz".equals(type)) {//3.1若传入的值为“Benz”，则我们就在这里new一个Benz类的对象并返回给用户即可
            c = new Benz();
        }
        if ("Ford".equals(type)) {//3.2若传入的值为“Ford”，则我们就在这里new一个Ford类的对象并返回给用户即可
            c = new Ford();
        }
        return c;//3.3返回创建好的对象给用户即可
    }
}

// 测试
class Test工厂01 {
    public static void main(String[] args) {
        //使用工厂模式中的方法创建Benz类的一个具体对象
        Car c = CarFactory.getCarInstance("Benz");//关于多态，编译时看左边（父类），运行时看右边（子类）
        if (c != null) {
            //此时是调用子类的方法（多态的特性）
            c.run();
            c.stop();
        } else {
            System.out.println("造不了这种汽⻋。。。");
        }
    }
}