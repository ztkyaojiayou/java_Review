package 多线程与并发.叶子猿代码.多线程基础.单例模式;


public class Singleton2 {

    private Singleton2() {}

    private static volatile Singleton2 instance;//volatile就是用于禁止指令重排

    /**
     * 懒汉式的双重检查加锁版（推荐）
     *
     * @return
     */
    public static Singleton2 getInstance () {
        // 自旋   while(true)
        if(instance == null) {//用于提高效率，如果有此实例了，则直接返回即可
            synchronized (Singleton2.class) {
                if(instance == null) {
                    instance = new Singleton2();  // 指令重排序

                    // 申请一块内存空间   // 1
                    // 在这块空间里实例化对象  // 2
                    // instance的引用指向这块空间地址   // 3
                }
            }
        }
        return instance;
    }

    // 多线程的环境下
    // 必须有共享资源
    // 对资源进行非原子性操作

}

