package 设计模式.工厂模式;

/**
 * （2）⼯⼚单一⽅法模式：有四个⾓⾊，抽象产品接口，具体产品接口，抽象⼯⼚类，具体⼯⼚类，。
 *      不再是由⼀个⼯⼚类去实例化具体的产品，⽽是由抽象⼯⼚的⼦类（即子工厂去继承该抽象工厂）去实例化产品
 */
// 1.车接口（抽象）
interface Car02 {
    void run();
}
// 2.具体的车类（这里也写了2个）
// 2.1Benz车
class Benz02 implements Car02 {
    @Override
    public void run() {
        System.out.println("Benz车已经创建，启动啦....");
    }
}
// 2.2Ford车
class Ford02 implements Car02 {
    @Override
    public void run() {
        System.out.println("Ford车已经创建，启动啦.....");
    }
}

/**
 * 重点来啦，即先创建一个抽象工厂，
 * 然后让具体的工厂类去继承（而不是实现）这个抽象工厂，
 * 且每个具体的工厂类都只生产一种对象，易知，这样一来，分工更明确
 */
// 3.抽象⼯⼚类（父类），只有一个创建对象的方法，
// 接着，通过创建不同的具体工厂类来继承该抽象工厂，
// 并重写父类中那个唯一的抽象方法，实现对象的创建，
abstract class CarFactory02 {
    abstract Car02 create();
}
// 4.具体⼯⼚类（一个一个的子类，用于单一生产）
// 易知，此时都不需要传入车名了，只需直接创建一个该车的工厂，
// 再调用其方法即可，因为分工已经非常明确，每一个工厂只生产一种车
// 4.1（只）生产Benz车的工厂（但不生产Ford车）
class BenzFactory extends CarFactory02 {
    public Car02 create() {
        return new Benz02();//在这里真正new该类的对象
    }
}
// 4.2（只）生产Ford车的工厂（同样地，也不生产Benz车）
class FordFactory extends CarFactory02 {
    public Car02 create() {
        return new Ford02();
    }
}

// 测试，生产一辆Ford车
class Test工厂02 {
    public static void main(String[] args) {
        //生产一辆Ford车，直接创建一个该车的工厂，再调用其方法即可
        CarFactory02 factory = new FordFactory();
        Car02 m = factory.create();
        m.run();//该车的对象已经被创建，开始启动~
    }
}