package java基础.构造方法_静态代码块执行顺序;

//括号内即为执行顺序
    class HelloA {//父类
        public HelloA() {//（5）父类的构造方法，可执行多次
            System.out.println("HelloA");
        }
        { System.out.println("I'm A class"); }//（4）普通代码块，可执行多次，先于构造方法

        //不管三七二十一，先执行了静态代码块再说
        static { System.out.println("static A"); }//（1）父类的静态代码块，只执行一次
    }
    class HelloB extends HelloA {//子类，继承了父类
        public HelloB() {//（7）子类的构造方法，可执行多次
            System.out.println("HelloB");
        }
        { System.out.println("I'm B class"); }//（6）普通代码块，可执行多次，先于构造方法

        static { System.out.println("static B"); }//（2）子类的静态代码块，只执行一次

        public static void main(String[] args) {
            System.out.println("-------main start-------");//（3）
            new HelloB();//开始执行各种构造方法和普通代码块
            new HelloB();//再重复执行一次各种构造方法和普通代码块
            System.out.println("-------main end-------");//（最后执行）
        }
    }
/**
 * 执行结果如下：
 * static A
 * static B
 * -------main start-------
 * I'm A class
 * HelloA
 * I'm B class
 * HelloB
 * （再重复执行一次）
 * I'm A class
 * HelloA
 * I'm B class
 * HelloB
 *
 * -------main end-------
 */

