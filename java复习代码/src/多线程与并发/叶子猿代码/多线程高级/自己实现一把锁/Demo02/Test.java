package 多线程与并发.叶子猿代码.多线程高级.自己实现一把锁.Demo02;

//测试，也是可重入锁的测试
public class Test {

    private int value;
    private MyLock2 lock = new MyLock2();

    //a方法中嵌套b方法，且都有锁，但只要是同一把锁，就可以访问，畅通无阻
    public void a() {
        lock.lock();
        System.out.println("a");
        b();
        lock.unlock();
    }

    public void b() {
        lock.lock();
        System.out.println("b");
        lock.unlock();
    }

    public static void main(String[] args) {

        Test m = new Test();

        new Thread(new Runnable() {

            @Override
            public void run() {
                m.a();
            }
        }).start();

    }

}
//测试结果：
//a
//b

