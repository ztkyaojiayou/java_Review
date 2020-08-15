package 设计模式.单例模式;

// 静态内部类完成， 推荐使用
public class Singleton06 {

    //使用volatile的目的是为了在多线程下，线程之间对该对象的可见性
        private static volatile Singleton06 instance;
        //1.构造器私有化（防止直接new）
        private Singleton06() {}

        //写成static的原因就是为了可以使用类名直接调用，而不必new对象来调用
        //2.再写一个静态内部类,该类中有一个静态属性 Singleton
        private static class SingletonInstance {
            //静态方法可以访问静态属性
            private static final Singleton06 INSTANCE = new Singleton06();//在这里真正创建该对象
        }

        //3.提供一个静态的公有方法，直接返回SingletonInstance.INSTANCE
        public static synchronized Singleton06 getInstance() {
            return SingletonInstance.INSTANCE;
        }
    }

    //测试
class test{
    public static void main(String[] args) {
        System.out.println("使用静态内部类完成单例模式");
        Singleton061 instance = Singleton061.getInstance();
        Singleton061 instance2 = Singleton061.getInstance();
        System.out.println(instance == instance2); // true
        System.out.println("instance.hashCode=" + instance.hashCode());
        System.out.println("instance2.hashCode=" + instance2.hashCode());

    }
}