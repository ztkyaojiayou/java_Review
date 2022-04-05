package 设计模式.练习;


/**
 * 2020.07.26 15:26
 */

//懒汉式之双重检测版（推荐）
class Singleton01 {
    private Singleton01() {
    }

    private static volatile Singleton01 instance;//即定义一个成员变量，初始值为null

    public static Singleton01 getInstance() {
        if (instance == null) {
            synchronized (Singleton01.class) {
                if (instance == null) {
                    instance = new Singleton01();
                }
            }
        }
        return instance;
    }
}

//饿汉式1（静态变量版）
class Singleton02 {
    private Singleton02() {
    }

    private final static Singleton02 INSTANCE = new Singleton02();

    //只有get方法public，其他都私有
    public static Singleton02 getInstance() {
        return INSTANCE;
    }
}

//饿汉式2（静态代码块版）
class Singleton03 {
    private Singleton03() {
    }

    private static Singleton03 instance;

    static {
        instance = new Singleton03();
    }

    public static Singleton03 getInstance() {
        return instance;
    }
}

//懒汉式1(普通版）
class Singleton04 {
    private Singleton04() {
    }

    //这里就不能声明为常量了呀
    private static Singleton04 instance;

    public static Singleton04 getInstance() {
        if (instance == null) {
            instance = new Singleton04();
        }
        return instance;
    }
}

//懒汉式2.1（线程安全版--同步代码块版）
class Singleton05 {
    private Singleton05() {
    }

    private static Singleton05 instance;

    public static Singleton05 getInstance() {
        synchronized (Singleton05.class) {
            if (instance == null) {
                instance = new Singleton05();
            }
        }
        return instance;
    }
}

//懒汉式2.2（线程安全版--同步方法版）
class Singleton06 {
    private Singleton06() {
    }

    private static Singleton06 instance;

    //该方法用于外部类调用，以获取到该单例对象
    public static synchronized Singleton06 getInstance() {
        if (instance == null) {
            instance = new Singleton06();
        }
        return instance;
    }
}