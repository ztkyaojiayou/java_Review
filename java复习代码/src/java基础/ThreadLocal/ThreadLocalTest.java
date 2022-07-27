package java基础.ThreadLocal;

import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

    public class ThreadLocalTest {
        //若要对同一个线程设置多组值，则就需要先new出多个ThreadLocal对象，
        // 但这些对象可以在不同的线程中循环使用
        static ThreadLocal<Integer> threadLocal = new ThreadLocal<>();
        static ThreadLocal<Integer> threadLocal02 = new ThreadLocal<>();
        public static void main(String[] args) throws InterruptedException {
            //线程1
            Thread t1 = new Thread(()->{
                System. out. println( threadLocal.get());
                //对于当前线程，会有一个对应的ThreadLocalMap（也即不同线程会对应不同的ThreadLocalMap）
                //易知:所有设置的值都属于这个线程,其他线程是拿不到该值的!!!
                // （但要注意：这个关系并不是一个map，而只是单纯给它分配了一个ThreadLocalMap而已），
                //而这个map中的结构为：
                // key：ThreadLocal对象（因此要设置多组值的话就需要先new多个ThreadLocal对象，这也是一个弊端之一），
                // value：要存储的具体值（也就是set方法中的值，一般为业务值）
                // 同一个ThreadLocal，则最终也只会存储一个value

                threadLocal.set(0);
                //由于是对同一个ThreadLocal对象赋值，因此会覆盖原有值（即最终get的不再是0，而是新值3）
                threadLocal.set(3);
                //由于是不同的ThreadLocal对象，因此可以正常存入

                //值为3，而不是0，因为被覆盖了
                System. out.println( threadLocal.get());

                threadLocal02.set(5);
                //值为5
                System.out.println(threadLocal02.get());
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

