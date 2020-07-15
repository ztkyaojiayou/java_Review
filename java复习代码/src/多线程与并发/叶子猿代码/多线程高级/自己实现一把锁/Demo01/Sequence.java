package 多线程与并发.叶子猿代码.多线程高级.自己实现一把锁.Demo01;

//测试，使用自己实现的lock锁实现队列
public class Sequence {

    private MyLock lock = new MyLock();//自己实现的锁哦

    private int value;

    public int getNext() {
        lock.lock();//上锁
        value++;
        lock.unlock();//释放锁
        return value;

    }

    public static void main(String[] args) {

        Sequence s = new Sequence();

        //创建5个线程同时执行同一个任务
        new Thread(new Runnable() {

            @Override
            public void run() {
                while(true)
                    System.out.println(s.getNext());
            }
        }).start();

        new Thread(new Runnable() {

            @Override
            public void run() {
                while(true)
                    System.out.println(s.getNext());
            }
        }).start();
        new Thread(new Runnable() {

            @Override
            public void run() {
                while(true)
                    System.out.println(s.getNext());
            }
        }).start();
        new Thread(new Runnable() {

            @Override
            public void run() {
                while(true)
                    System.out.println(s.getNext());
            }
        }).start();
        new Thread(new Runnable() {

            @Override
            public void run() {
                while(true)
                    System.out.println(s.getNext());
            }
        }).start();
    }

}

