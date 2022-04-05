package 数据结构与算法.离职后刷题;


import java.util.Objects;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

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

    private static volatile Singleton004 instance;

    public static Singleton004 getInstance() {
        if (instance == null) {
            synchronized (Singleton004.class) {
                if (instance == null) {
                    instance = new Singleton004();
                }
            }
        }
        return instance;
    }
}


//测试
public class 单例模式 {
    public static void main(String[] args) {
        System.out.println("单例模式test");
    }
}








