package java基础.设计模式.单例模式;
//懒汉式
public class Singleton03 {
        private static Singleton03 instance;

        private Singleton03() {}

        //提供一个静态的公有方法，当使用到该方法时，才去创建 instance
        //即懒汉式
        public static Singleton03 getInstance() {
            if(instance == null) {
                instance = new Singleton03();
            }
            return instance;
        }
    }
