package 多线程与并发.叶子猿代码.多线程基础.单例模式;


public class Singleton {
//饿汉式，简单且不存在安全性问题，思路也很好理解，真正开发一般就用这种方式
    // 私有化构造方法，则不可实例化，只能通过getInstance方法获取
    private Singleton () {}

    private static Singleton instance = new Singleton();//直接创建出对象

    public static Singleton getInstance() {//返回该对象即可
        return instance;
    }
    //线程安全问题的产生：
    // 多线程的环境下
    // 必须有共享资源
    // 对资源进行非原子性操作
}

