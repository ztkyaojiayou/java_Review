package 多线程与并发.叶子猿代码.多线程基础.Synchronized关键字_隐式锁;


public class Demo02 {
    public synchronized void a () {//锁为this，此时为d1对象,当线程进来以后就会获取到该锁
        System.out.println("a");
        b();//此时该线程要去调用b方法，由于也是一个同步方法，
        //因此也您必须先获取到该锁，也是this，这里就也是d1对象，因为就是d1在调用嘛

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public synchronized void b() {//锁为this，谁调用，那么这个锁就是谁
        System.out.println("b");
        //a();//注意：这里若再调用a方法，则会发生stackoverflowerror栈溢出异常，因为很明显这在相互调用形成死循环，则会一直运行到栈溢出
        //且这并不是死锁，因为不同对象的锁是不同的，是不受影响的
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
        Demo02 d1= new Demo02();
        Demo02 d2= new Demo02();
        //第一个线程
        new Thread(new Runnable() {

            @Override
            public void run() {
                d1.a();//调用a方法，会获取到该同步方法的锁，为this，这里就是d1对象
            }
        }).start();
        //第二个线程
        new Thread(new Runnable() {

            @Override
            public void run() {
                d2.b();
            }
        }).start();
    }

}

