package 设计模式.单例模式;
//饿汉式(静态代码块)
public class Singleton02 {


        //1. 构造器私有化, 外部能new
        private Singleton02() {

        }

        //2.本类内部创建对象实例
        private  static Singleton02 instance;

        static { // 在静态代码块中，创建单例对象
            instance = new Singleton02();
        }

        //3. 提供一个公有的静态方法，返回实例对象
        public static Singleton02 getInstance() {
            return instance;
        }

    }
