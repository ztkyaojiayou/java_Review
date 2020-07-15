package 多线程与并发.毕向东代码.线程通信_Join;

class Demo02 implements Runnable
{
    public void run()
    {
        for(int x=0; x<10; x++)
        {
            System.out.println(Thread.currentThread().toString()+"....."+x);
            //Thread.yield();//演示yield方法
        }
    }
}

public class JoinDemo
{
    public static void main(String[] args) throws Exception
    {
        Demo02 d = new Demo02();

        Thread t1 = new Thread(d);
        Thread t2 = new Thread(d);
        t1.start();
        t2.start();
        //用于演示yield方法
        //t1.setPriority(Thread.MAX_PRIORITY);
		//t2.setPriority(Thread.MAX_PRIORITY);

/**
 *“t1线程要申请加入进来运行”这个说法不太容易理解，毕竟t1线程本来就在运行呀，什么叫申请加入进来运行嘛？
 * 正确的说法应该是：主线程main等待t1线程先执行完（“主线程你给我t1让开，我执行完毕再说”），
 * 然后主线程再和t2线程一起随机执行，即保证了main线程一定是在t1线程先执行完毕后才执行。
 * 但要注意：此时只是主线程main在等待t1运行完毕，
 * 而t2线程则是和t1线程同时交替随机执行的，即与t2线程的执行情况是无关的。
 *
 * 不过要注意：若t1.join方法在另外一个线程t2里面，且是t2线程先执行，在t2线程里面再执行t1.join方法，则就是t2线程等待t1线程执行完毕而不再是主线程等t1
 * 执行完毕啦。
 */
		t1.join();

        //主线程代码
        for(int x=0; x<10; x++)
        {
			System.out.println(Thread.currentThread()+"....."+x);
        }
    }
}

