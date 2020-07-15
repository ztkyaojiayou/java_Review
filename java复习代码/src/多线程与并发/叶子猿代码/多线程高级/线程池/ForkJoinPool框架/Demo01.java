package 多线程与并发.叶子猿代码.多线程高级.线程池.ForkJoinPool框架;


import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.Future;
import java.util.concurrent.RecursiveTask;

//ForkJoinPool框架的使用：ForkJoinPool的优势在于，可以充分利用多cpu，多核cpu的优势
// 把一个任务拆分成多个“小任务”，把多个“小任务”放到多个处理器核心上并行执行,再把多个“小任务”的结果合并成总的计算结果
// 当多个“小任务”执行完成之后，再将这些执行结果合并起来即可。
// ForkJoinPool是ExecutorService的实现类，因此是一种特殊的线程池。

// 使用方法：创建了ForkJoinPool实例之后，就可以调用ForkJoinPool的submit(ForkJoinTask<T> task) 或invoke(ForkJoinTask<T> task)方法来执行指定任务了。
// 其中ForkJoinTask代表一个可以并行、合并的任务。ForkJoinTask是一个抽象类，它还有两个抽象子类：RecusiveAction和RecusiveTask
// 其中RecusiveTask代表有返回值的任务，而RecusiveAction代表没有返回值的任务。

// 使用ForkJoinPool能够使用数量有限的线程来完成非常多的具有父子关系的任务，比如使用4个线程来完成超过200万个任务。
//但是，使用ThreadPoolExecutor时，是不可能完成的，因为ThreadPoolExecutor中的Thread无法选择优先执行子任务，
//需要完成200万个具有父子关系的任务时，也需要200万个线程，显然这是不可行的。

//使用场景：执行“大任务”：计算1~1000的数值之和，
//使用ForkJoinPool之后，程序会将此“大任务”拆分成多个“小任务”，并将任务交给ForkJoinPool来执行
public class Demo01 extends RecursiveTask<Integer> {//继承一个有返回值的ForkJoinPool类来实现“可分解”的任务

    private int begin;
    private int end;

    public Demo01(int begin, int end) {//构造器
        this.begin = begin;
        this.end = end;
    }

    //compute()方法：把大任务拆分成小任务
    @Override
    protected Integer compute() {//这个方法并不是我们自己定义的，而是源码里面就有的，这是一个抽象方法，
        // 因此我们在继承父类（RecursiveTask）时必须实现它的抽象方法。（基础知识）
        //千万不要混淆（老师竟然没有指出来！！！）
        System.out.println(Thread.currentThread().getName() + " ... ");

        int sum = 0;
        // 当拆分成只有三个数相加时，就定义为“小任务”，此时直接计算即可
        if (end - begin <= 2) {
            for (int i = begin; i <= end; i++) {
                sum += i;
            }
        } else {
            //否则，拆分（这里先把大任务拆分成前后两段，再对每一个大段分别继续拆分，一直拆分到“小任务”）

            Demo01 d1 = new Demo01(begin, (begin + end) / 2);
            Demo01 d2 = new Demo01((begin + end)/2 + 1, end);
/**
 * fork和join的执行原理：
 * ForkJoinPool 的每个工作线程都维护着一个工作队列（WorkQueue），这是一个双端队列（Deque），里面存放的对象就是各个子任务（ForkJoinTask）。
 * 每个工作线程在运行中产生新的任务（通常是因为调用了 fork()）时，会放入工作队列的队尾，
 * 并且工作线程在处理自己的工作队列时，使用的是 LIFO 方式，即每次从队尾取出任务来执行。
 * 每个工作线程（包括正在处理自己的工作队列的线程或空闲线程）在处理自己的工作队列同时，会尝试窃取一个任务（或是来自于刚刚提交到 pool 的任务，或是来自于其他工作线程的工作队列），
 * 窃取的任务位于其他线程的工作队列的队首，也就是说工作线程在窃取其他工作线程的任务时，使用的是 FIFO 方式。
 * 在遇到 join() 时，如果需要 join 的任务尚未完成，则会先处理其他任务，并等待其完成。
 * 在既没有自己的任务，也没有可以窃取的任务时，进入休眠。
 *
 * 注意：
 * FIFO: First in, First out.先进先出。
 * LIFO: Last in, First out.后进先出。
 */
            // 1.1开始执行任务
            d1.fork();//前半段（会继续细分）
            d2.fork();//后半段（也会继续细分）

            //1.2再把上面的值合并
            Integer a = d1.join();
            Integer b = d2.join();

            sum = a + b;
        }

        return sum;
    }

    public static void main(String[] args) throws Exception {

        //0.创建一个有3个线程的ForkJoinPool线程池（而不是普通线程池）
        ForkJoinPool pool = new ForkJoinPool(3);
        //1.提交一个“大任务”：计算1-1000进行累加的和,返回的是一个Future对象，易知就可以异步获取线程执行的返回值了
        Future<Integer> future = pool.submit(new Demo01(1, 1000));

        System.out.println("....");

        System.out.println("计算的值为：" + future.get());//2.异步获取刚才提交的“大任务”的结果
    }


}

