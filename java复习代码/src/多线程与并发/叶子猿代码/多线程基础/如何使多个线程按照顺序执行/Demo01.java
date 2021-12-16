package 多线程与并发.叶子猿代码.多线程基础.如何使多个线程按照顺序执行;

//如何使多个线程按照顺序执行？其实就是一个“线程之间的通信问题”，
//只需在每个线程执行前加一个判断条件即可，不满足条件时就让它先等待，很容易理解
// （要用到wait等待和notifyAll唤醒，但不能使用notify，因为它只能随机唤醒某一个线程）
//（之后会用到Condition来指定唤醒某一个线程）

//任务1
class A1 implements Runnable {

    private Demo01 demo01;

    public A1(Demo01 demo) {
        this.demo01 = demo;
    }

    @Override
    public void run() {
        while(true) {
            demo01.a();//调用a方法
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
//任务2
class B1 implements Runnable {

    private Demo01 demo02;

    public B1(Demo01 demo01) {
        this.demo02 = demo01;
    }

    @Override
    public void run() {
        while(true) {
            demo02.b();//调用b方法
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}

//任务3
class C1 implements Runnable {

    private Demo01 demo03;

    public C1(Demo01 demo03) {
        this.demo03 = demo03;
    }

    @Override
    public void run() {
        while(true) {
            demo03.c();//调用c方法
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}

public class Demo01 {
    private int flag;//定义一个标记，用于条件的判断，初始值为0

    /**
     * 方法1（相当于线程1）
     */
    public synchronized void a() {//同步方法,锁为this
        while(flag != 0 ) {//不为0则等待，等于0时才执行
            try {
                this.wait();//当前线程等待
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("a");
        flag++;//加一，则为1
        this.notifyAll();//唤醒全部线程，但此时只有b方法的线程执行，因为此时signal为1
    }
    /**
     *   方法2（相当于线程2）
     */
    public synchronized void b() {
        while(flag != 1) {//不为1则等待，等于1时才执行
            try {
                this.wait();//当前线程等待
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        System.out.println("b");
        flag++;//再加一，则为2
        this.notifyAll();//唤醒全部线程，但此时只有c方法的线程执行，因为此时signal为2
    }

    /**
     * 方法3（相当于线程3）
     */
    public synchronized void c () {
        while(flag != 2) {//不为2则等待，等于2时才执行
            try {
                this.wait();//当前线程等待
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        System.out.println("c");
        flag = 0;//重新设置为0
        this.notifyAll();//唤醒全部线程，但此时只有a方法的线程执行，因为此时signal又设置为了1
    }

    //测试
    public static void main(String[] args) {

        //创建三个线程并启动
        Demo01 d = new Demo01();//共用
        A1 a1 = new A1(d);
        B1 b1 = new B1(d);
        C1 c1 = new C1(d);

        new Thread(a1).start();
        new Thread(b1).start();
        new Thread(c1).start();

    }
}


