package 数据结构与算法.离职后刷题;



import java.util.ArrayList;
import java.util.HashMap;

class Singleton001 {
    //1.饿汉模式--线程安全
    private Singleton001() {
    }

    //先直接new，且是static的，在类加载时就完成了，不存在线程安全问题
    private final static Singleton001 INSTANCE = new Singleton001();

    //再提供获取该对象的方法供外部调用
    public static Singleton001 getInstance() {
        return INSTANCE;
    }
}

//2.懒汉模式
//2.1普通版--线程不安全
class Singleton002 {
    private Singleton002() {
    }

    //先只声明一个对象
    private static Singleton002 instance;

    //再提供方法供外部获取--没有考虑线程安全
    public static Singleton002 getInstance() {
        if (instance == null) {
            instance = new Singleton002();
        }
        return instance;
    }
}

//2.2线程安全版
class Singleton003 {
    private Singleton003() {
    }

    //先只声明一个对象
    private static Singleton003 instance;

    //再提供方法供外部获取--没有考虑线程安全
    public static Singleton003 getInstance() {
//        //方法1：使用ReentrantLock锁
//        Lock lock = new ReentrantLock();
//        lock.lock();
//        try {
//            if (instance == null) {
//                instance = new Singleton003();
//            }
//        } finally {
//            lock.unlock();
//        }
//        return instance;

        //方法2：使用同步代码块（均可）
        synchronized (Singleton003.class) {
            if (instance == null) {
                instance = new Singleton003();
            }
            return instance;
        }


    }

    //方法3：使用同步方法
    public static synchronized Singleton003 getInstance02() {
        if (instance == null) {
            instance = new Singleton003();
        }
        return instance;
    }
}


//2.双重检锁模式--推荐
class Singleton004 {
    private Singleton004() {
    }

    //使用volatile关键字：禁止指令重排
    private static volatile Singleton004 instance;

    public static Singleton004 getInstance() {
        //第一重检查：和单线程和多线程情形下都有关系
        //1）单线程时：只要之前创建好了对象，则直接返回该对象即可，无需再次加锁并做无意义的操作
        //2）多线程时：可能多个线程同时通过该检测，然后分别排队去加锁并创建对象，此时就有可能创建多个对象，
        // 也因此在加锁后还需要再次判断是否已经创建了对象，即若第一个线程已经创建了对象，则此时也无需再次创建了，
        // 直接返回线程1创建的对象即可！
        if (instance == null) {
            synchronized (Singleton004.class) {
                //防止多个线程都通过第一重检查后（因为没加锁，因此完全有可能呀）再次创建多个对象
                if (instance == null) {
                    //这里也有问题，对于线程1，在创建对象时，由于指令重排现象的存在，
                    // 若不对instance加volatile关键字的话，则有可能创建的不算一个完整的对象，
                    // 因为：理论上，只要将对象赋值给对象引用就已经是一个完整的对象了，
                    // 但这不是我们所需要的对象，因为我们需要给对象赋自定义的值才算，
                    // 因此我们将这个“完整”对象看成是一个不完整的对象
                    // 此时若另一个线程3也来获取对象，当执行到第一重检查时发现，
                    // 该对象已经不为null（但其实是个不完整的对象），则直接就返回该不完整的对象了，
                    // 显然这不是我们想要的，因此我们需要使用volatile关键字，
                    // 目的是使在创建该实例对象时禁止指令重排现象发生，从而得到一个完整的对象！！！
                    // 但准确来讲：volatile阻止的不是singleton = new Singleton()这句话内部[1-2-3]的指令重排，
                    // 而是保证了在一个写操作（[1-2-3]）完成之前，不会调用读操作（if (instance == null)）。
                    instance = new Singleton004();
                }
            }
        }
        return instance;
    }

}

//自写一遍
class Test {
    private Test() {
    }

    private static volatile Test test = null;

    public static Test getTest() {
        if (test == null) {
            synchronized (Test.class) {
                if (test == null) {
                    test = new Test();
                }
            }
        }
        return test;
    }

    //懒汉式
    private static Test test01 = null;

    public static Test getTest02() {
        synchronized (Test.class) {
            if (test01 == null) {
                test01 = new Test();
            }
            return test01;
        }
    }

    //饿汉式
    private final static Test test02 = new Test();

    public static Test getTest03() {
        return test02;
    }

}

//测试
public class 单例模式 {
    public static void main(String[] args) {
        //乱七八糟的测试，与这里的单例无关
        ArrayList<Integer> list = new ArrayList<>();
        list.add(1);
        HashMap<Object, Object> map = new HashMap<>(18);
        map.put("tkzou", "111");
        System.out.println("单例模式test");
    }
}








