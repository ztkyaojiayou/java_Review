package 多线程与并发.叶子猿代码.多线程高级.Volatile关键字;


public class Demo4 {

    private int a;
    private static final int b ;

    static  {
        b = 10;
    }

    public Demo4() { // 1
//		b = 20; // 2
        a = 10; // 3
    } // 4

    private Demo4 demo4;

    public void w() { // 5
        demo4 = new Demo4(); // 6
    } //

    public void r () {
        Demo4 d = demo4; // 7
        int temp1 = d.a; // 8
        int temp2 = d.b; // 9
    }


}

