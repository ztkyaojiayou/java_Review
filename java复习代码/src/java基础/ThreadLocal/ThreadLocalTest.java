package java基础.ThreadLocal;

import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

    public class ThreadLocalTest {
        static ThreadLocal<Integer> threadLocal = new ThreadLocal<>();
        public static void main(String[] args) throws InterruptedException {
            //线程1
            Thread t1 = new Thread(()->{
                System. out. println( threadLocal.get());
                threadLocal.set(0);
                System. out.println( threadLocal.get());
            });

            //线程之间是相互隔离的，互不影响，即线程2读取不到线程1对threadLocal对象设置的值，保证了多线程场景下的安全性

            //线程2
            Thread t2 = new Thread(()->{
                System. out . println( threadLocal.get());

                //即先获取当前线程的ThreadLocalMap（注意：每个线程都有该map），
                //并将threadLocal对象当做key，要设置的值5当做value存入这个map中
                threadLocal.set(5);

                //即获取当前线程的ThreadLocalMap中的key，即threadLocal对象，
                //再根据该key来获取到其值value，即5并返回。
                System. out . println( threadLocal.get());
            });
            t1. start();
            t1. join();//即等t1运行完毕，t2才运行，保证顺序性
            t2. start();
        }

        }

