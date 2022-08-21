package 最近测试.多线程;

/**
 * 1）interrupted和isInterrupted的区别？都是中断线程，关键是中断哪个线程
 * 1.interrupted的对象是当前线程，也就是创建Thread对象的线程（比如是创建子线程的main线程），并且会清除中断状态，使之变为未中断状态，
 * 2.isInterrupted的对象是线程Thread对象（比如由main线程创建的子线程），并且不会清除中断状态
 * 2）线程中断就是让线程立即停止吗？
 * interrupt并不是强制停止程序，它仅仅是请求子线程中断，给它打上个中断标识。
 * 但是子线程要不要根据这个中断标识去处理，怎么处理，完全是子线程自己的事情，
 * 因为没有人比子线程更了解子线程要处理的业务逻辑，
 * 如果它认为自己的业务逻辑不能被中断，那么完全忽略这个中断请求，或者它认为自己收到中断请求就应该立刻停止，
 * 或者做一些收尾的工作再中断，这都是可以的。
 * 3）小总结：
 * 1.interrupt并不是强制停止线程。它是请求线程中断，给线程打上个中断标识。至于线程收到中断标识后，要不要处理，怎么处理，完全是线程自己的事情。
 * 2.可以通过interrupt+trycatch，或者interrupt+return来立刻终止线程，不过更推荐interrupt+trycatch
 * 3.stop因为会造成死锁跟数据不一致等安全性问题，已经被弃用，它会让线程立刻终止。
 * 4.suspend+resume也是弃用的方法，使用不当也会造成其它线程长时间的等待锁跟数据不一致。suspend可以暂停线程，resume用来恢复。
 *
 * <p>
 * 参考链接:https://blog.csdn.net/qq_20952591/article/details/121316085
 *
 * @author :zoutongkun
 * @date :2022/8/18 1:04 下午
 * @description :
 * @modyified By:
 */
public class InterruptedTest {
    public static void main(String[] args) {
        // 获取当前线程--这里即为main线程！！！
        Thread currentThread = Thread.currentThread();
        // 获取当前线程的中断状态
        System.out.println(currentThread.isInterrupted()); // false
        // 将当前线程标记为中断线程
        currentThread.interrupt();
        // 获取当前线程的中断状态
        System.out.println(currentThread.isInterrupted()); // true

        // 干了两件事
        //（1）获取当前线程的中断状态,（2）并且取消中断状态
        boolean interrupted = Thread.interrupted();
        System.out.println(interrupted); // true

        System.out.println(currentThread.isInterrupted()); // false 。因为Thread.interrupted()中取消中断状态
    }
}

class InterruptTest implements Runnable {
    public static void main(String[] args) {
        Thread thread = new Thread(new InterruptTest());
        thread.start();
        //将当前线程标记线程为中断状态（这又是一个新方法，不要和另外两个判断线程是否中断的方法混淆）
        //易知，中断的是子线程，但主线程未中断
        thread.interrupt();
        //再判断
        //interrupted：判断的是主线程是否中断，即便是使用子线程/thread去调用（标准的写法应该是Thread调用），易知为false
        System.out.println("interrupted:" + thread.interrupted());//false
        //标准写法
        System.out.println("interrupted:" + Thread.interrupted());//false
        //isInterrupted：判断的是子线程（某种意义上说也就是当前线程）是否中断，易知为true
        System.out.println("isInterrupted:" + thread.isInterrupted());//true
    }

    @Override
    public void run() {
        //给子线程找点事儿干，让它不要那么快结束
        for (int i = 0; i < 10; i++) {
            System.out.println(i);
        }
    }
}


class InterruptTest2 implements Runnable {
    public static void main(String[] args) {
        Thread thread = new Thread(new InterruptTest2());
        thread.start();
        //获取当前线程
        System.out.println(Thread.currentThread().getName());//Main线程
        //将Main线程中断
        Thread.currentThread().interrupt();
//        Thread.interrupted();
        //再将子线程也中断
        thread.interrupt();
        //再判断
        //main线程：中断-ture
        System.out.println("interrupted:" + Thread.interrupted());
        //子线程：中断-true
        System.out.println("isInterrupted:" + thread.isInterrupted());
    }

    @Override
    public void run() {
        //给子线程找点事儿干，让它不要那么快结束
        for (int i = 0; i < 10; i++) {
            System.out.println(i);
        }
    }
}


/**
 * 测试中断对程序的影响
 * 会无视线程中断。而主线程在休眠1ms后去请求子线程中断。
 * 可以看到，子线程是可以忽略这个中断请求，继续处理自己的逻辑的。
 * 并且子线程的中断并不影响主线程的执行，主线程依旧会输出程序结束。
 */
class Test01 {
    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(() -> {
            int i = 0;
            while (i < 1000) {
                if (i % 200 == 0) {
                    System.out.println(i + "是100的倍数");
                }
                i++;
            }
        });
        thread.start();
        Thread.sleep(1);
        //中断子线程，也即自己创建的线程
        //影响：完全无影响
        thread.interrupt();
        System.out.println("程序结束！");
    }
}


/**
 * 结果分析
 * 可以看到，当我们加上了对中断标识的判断并处理，程序就会按照我们的期望处理中断了。
 * <p>
 * 也就证明了interrupt并不是强制停止程序，它仅仅是请求子线程中断，给它打上个中断标识。
 * 但是子线程要不要根据这个中断标识去处理，怎么处理，完全是子线程自己的事情，因为没有人比子线程更了解子线程要处理的业务逻辑，
 * 如果它认为自己的业务逻辑不能被中断，那么完全忽略这个中断请求，或者它认为自己收到中断请求就应该立刻停止，
 * 或者做一些收尾的工作再中断，这都是可以的。
 * <p>
 * 但是如果while后面还有语句，还是会继续执行的。
 */
class Test02 {
    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(() -> {
//            System.out.println(Thread.currentThread().getName());//子线程
            int i = 0;
            while (i < 10000) {
                //查看子线程是否中断，并做处理(易知这个处理是自定义的，也易知就可以无视该中断，如test01）
                if (Thread.currentThread().isInterrupted()) {
                    System.out.println("线程已经被interrupt了！我要退出了！");
                    //此时退出当前子线程的while循环，但并不是结束子线程，while外的程序还是会执行
                    break;
                }
                if (i % 100 == 0) {
                    System.out.println(i + "是100的倍数");
                }
                i++;
            }
            System.out.println("这句话输出表明该子线程并未停止/退出/终止！");
        });
        thread.start();
        //这代表主线程
//        System.out.println(Thread.currentThread().getName());//main
        Thread.sleep(1);
        //中断子线程，也即自己创建的线程,而非main线程
        thread.interrupt();
        System.out.println("程序结束！");
    }
}


/**
 * 结果分析
 * 可以看到，如果希望子线程在循环中遇到了中断，希望循环后面的语句不要执行，可以用trycatch来处理
 */
class Test03 {
    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(() -> {
            try {
                int i = 0;
                while (i < 10000) {
                    if (Thread.currentThread().isInterrupted()) {
                        System.out.println("线程已经被interrupt了！我要退出了！");
                        //此时退出当前子线程的while循环，同时抛出一个异常
                        //则就会结束子线程，while外的程序也不会执行，这是很推荐的一种结束子线程的方式！！！
                        throw new InterruptedException();
                    }
                    if (i % 100 == 0) {
                        System.out.println(i + "是100的倍数");
                    }
                    i++;
                }
                System.out.println("我是while循环后的语句！");
            } catch (InterruptedException e) {
                System.out.println("子线程异常，进入catch语句，子线程结束/退出/终止");
                e.printStackTrace();
            }
        });
        thread.start();
        Thread.sleep(1);
        //中断子线程
        thread.interrupt();
        System.out.println("程序结束！");
    }
}

/**
 * 使用标志位flag做判断来终止线程（之前推荐的方式，现在更推荐上面的中断+try-catch处理）
 * 但是如果在子线程的while条件中线程阻塞了，就无法来终止线程了
 */
class VolatileStopThreadTest {
    //volatile可加可不加，主要是可见性
    private static volatile boolean flag = false;

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(() -> {
            int i = 0;
            try {
                //若标志位为true则会退出该while循环，即停止当前线程
                while (i < 1000 && !flag) {
                    if (i % 100 == 0) {
                        System.out.println(i + "是100的倍数。");
                    }
                    //当前线程（子线程）休眠
                    Thread.sleep(1);
                    i++;
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("flag:" + flag);
        });
        //启动子线程
        thread.start();
        //当前线程（主线程）休眠
        Thread.sleep(500);
        //将标志位置为true
        flag = true;
    }
}

