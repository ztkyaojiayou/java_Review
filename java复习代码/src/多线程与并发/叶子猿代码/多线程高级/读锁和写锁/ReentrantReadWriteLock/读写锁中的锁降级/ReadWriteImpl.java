package 多线程与并发.叶子猿代码.多线程高级.读锁和写锁.ReentrantReadWriteLock.读写锁中的锁降级;


import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 锁降级：
 * 对于同一个线程，可以先获取写锁、再获取读锁，接着释放写锁，从而降级成读锁；即写锁能够降级成为读锁。
 * 但反之不行，即不可以先获取读锁，再获取写锁，接着释放读锁而升级为写锁，只能先把读锁释放后，才可以获取写锁
 * 所以总逻辑可以是：先获取读锁，再释放读锁，再获取写锁，此时可以再获取读锁，再释放写锁，最后再释放读锁
 */

//3.总逻辑的演示
public class ReadWriteImpl {

    private Map<String, Object> map = new HashMap<>();

    private ReadWriteLock rwl = new ReentrantReadWriteLock();//可重入的读写锁

    private Lock readLock = rwl.readLock();//得到一个读锁对象
    private Lock writeLock = rwl.writeLock();//得到一个写锁对象

    private volatile boolean isUpdate;//标志位，默认为false

    public void ReadWrite() {
        readLock.lock(); //1.获取读锁（此时不能同时获取写锁）为了保证isUpdate能够拿到最新的值
        if (isUpdate) {//判断标志位，即判断读操作释放完成
            readLock.unlock();//4.释放读锁
            writeLock.lock();//5.获取写锁
            map.put("xxx", "xxx");//6.执行写操作
            readLock.lock();//7.再获取读锁（这是允许的）
            writeLock.unlock();//8.释放写锁（此时降级为了读锁）
            isUpdate = false;//9.再一次修改标志位，即退出循环
        }
        //2.执行读操作
        Object obj = map.get("xxx");
        System.out.println(obj);
        isUpdate = true;//3.再把标志位修改成true，于是就可以释放读锁了
        readLock.unlock();//10.最后释放读锁，操作完成

    }

}

//1.读的同时想去写，会出现死锁
class Test1 {

    public static void main(String[] args) {
        ReentrantReadWriteLock rtLock = new ReentrantReadWriteLock();
        rtLock.readLock().lock();//获取读锁，但不释放
        System.out.println("get readLock.");
        rtLock.writeLock().lock();//读锁还没释放时，是获取不到写锁的，会发生死锁
        System.out.println("blocking");
    }
    //结果如下：
    //get readLock.+死锁
}

//2.但写的同时想去读，是可以的
class Test2 {

    public static void main(String[] args) {
        ReentrantReadWriteLock rtLock = new ReentrantReadWriteLock();
        rtLock.writeLock().lock();//获取写锁，但不释放
        System.out.println("writeLock");

        rtLock.readLock().lock();//写锁还没释放时，是可以获取到读锁的
        System.out.println("get read lock");
    }
    //结果如下：
    //writeLock
    //get read lock
}