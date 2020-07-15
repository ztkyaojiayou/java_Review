package java基础.设计模式.工厂模式;

/**
 * （抽象）⼯⼚多个方法模式：与⼯⼚单一⽅法模式不同的是，⼯⼚单一⽅法模式中的⼯⼚只⽣产单⼀的产品，
 * ⽽⼯⼚多个方法模式中的⼯⼚可以⽣产多个产品
 */
//接口
interface Vehicle{
    void run();
}
interface Weapon{
    void shoot();
}
interface Food{
    void printName();
}

//具体实现类
class Apple implements Food{
    @Override
    public void printName() {
        System.out.println("苹果来啦-----------");
    }
}
class Car03 implements Vehicle{
    @Override
    public void run() {
        System.out.println("汽车启动啦-----------");
    }
}
class AK47 implements Weapon{
    @Override
    public void shoot() {
        System.out.println("AK47射击啦-----------");
    }
}

/**
 * 重点来啦
 */
//抽象⼯⼚类，里面就是包含了创建不同对象的若干方法（而不是一个）
abstract class AbstractFactory {
    abstract Vehicle createVehicle();
    abstract Weapon createWeapon();
    abstract Food createFood();
}
//具体⼯⼚类，继承父类工厂，重写其方法，实现不同对象的创建
//易知，这个工厂可以生产多个产品/对象（这就是与工厂方法不同的地方）
class Multi_Factory extends AbstractFactory{
    @Override
    public Food createFood() {//生产具体食物Apple的具体实现
        return new Apple();
    }
    @Override
    public Vehicle createVehicle() {//生产具体交通工具Car03的具体实现
        return new Car03();
    }
    @Override
    public Weapon createWeapon() {//生产某一具体武器AK47的具体实现
        return new AK47();
    }
}

//测试类
class Test工厂03 {
    public static void main(String[] args) {
        AbstractFactory f = new Multi_Factory();//新建一个工厂对象，通过该工厂对象就可以生产所有的产品啦~
        Vehicle v = f.createVehicle();//生产Car03
        v.run();
        Weapon w = f.createWeapon();//生产ak47
        w.shoot();
        Food a = f.createFood();//生产Apple
        a.printName();
    }
}
