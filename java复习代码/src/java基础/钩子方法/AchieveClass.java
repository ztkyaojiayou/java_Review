package java基础.钩子方法;

//抽象类A，里面有四个抽象方法，现在我只需要用isOpen方法（该方法就叫钩子方法）
abstract class A{
    public abstract void fun1();
    public abstract void fun2();
    public abstract void fun3();
    public abstract boolean isOpen();
}
abstract class B extends A{
//在一个抽象类中，假设我们只需要用到isOpen方法，而其他方法并不需要，则我们可以用另一个抽象类B去继承它，
//然后把该方法继续标记为抽象方法，而其他方法都进行空实现，则我们在继承这个抽象类时，就不需要实现其它不用的方法了

    //要使用的这个方法依旧保留为抽象方法
    public abstract boolean isOpen();

    //其他不用的方法全部进行空实现，则其他类再继承该类时就不需要再实现了
    public void fun1(){};
    public void fun2(){};
    public void fun3(){};

    public final void operating() {//测试方法
        if(isOpen()) {
            System.out.println("钩子方法开启");
        }else {
            System.out.println("钩子方法关闭");
        }
    }
}

public class AchieveClass extends B {

    //钩子方法能挂载到operating上，能干预到operating业务逻辑
    //实现抽象类B中的抽象方法，此时我们只需要实现我们需要的方法isOpen即可
    @Override
    public boolean isOpen() {
        return true;
    }

    public static void main(String[] args) {
        AchieveClass ac = new AchieveClass();
        ac.operating();
    }



}
