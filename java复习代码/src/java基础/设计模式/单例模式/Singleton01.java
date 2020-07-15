package java基础.设计模式.单例模式;
//饿汉式(静态变量)
public class Singleton01 {



        //1. 构造器私有化, 外部能new
        private Singleton01() {

        }

        //2.本类内部创建对象实例
        private final static Singleton01 instance = new Singleton01();

        //3. 提供一个公有的静态方法，返回实例对象
        public static Singleton01 getInstance() {
            return instance;
        }

    }
