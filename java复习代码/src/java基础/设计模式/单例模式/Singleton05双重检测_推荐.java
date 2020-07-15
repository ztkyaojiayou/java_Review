package java基础.设计模式.单例模式;

// 懒汉式(线程安全，同步方法，双重检测)
class Singleton05 {
        private static volatile Singleton05 instance;

        private Singleton05() {}

        //提供一个静态的公有方法，加入双重检查代码，解决线程安全问题, 同时解决懒加载问题
        //同时保证了效率, 推荐使用

        public static synchronized Singleton05 getInstance() {
            if(instance == null) {//第一次检测
                synchronized (Singleton05.class) {//加锁
                    if(instance == null) {//第二次检测
                        instance = new Singleton05();//开始真正创建对象
                    }
                }

            }
            return instance;
        }
    }

    //测试
class Test{
    public static void main(String[] args) {
        Singleton05 singleton05 = Singleton05.getInstance();
        Singleton05 singleton1 = Singleton05.getInstance();
        System.out.println(singleton05 == singleton1);
        System.out.println(singleton05.hashCode());
        System.out.println(singleton1.hashCode());

    }
}
