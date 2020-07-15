package 多线程与并发.叶子猿代码.多线程高级.异步获取线程执行结果之FutureTask类;
import java.util.concurrent.*;

//demo2，和demo1相同
class task02 implements Callable<Integer>{
//要执行的任务
    @Override
    public Integer call() throws Exception {
        System.out.println("子线程在进行计算");
        Thread.sleep(3000);
        int sum = 0;
        for(int i=0;i<100;i++)
            sum += i;
        return sum;
    }

}



public class Demo2 {

    public static void main(String[] args) {
        //创建线程池，用于执行任务
        ExecutorService executor = Executors.newCachedThreadPool();
        //创建Callable对象的任务
        task02 task = new task02();//task02实现了Callable接口
        //提交任务到Future，而不是直接执行，这样就可以获取该任务的执行结果啦（重点）
        Future<Integer> result02 = executor.submit(task);
        //关闭线程池
        executor.shutdown();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e1) {
            e1.printStackTrace();
        }

        System.out.println("主线程在执行任务");

        try {
            if(result02.get()!=null){
                /**
                 * 在Java中一般通过继承Thread类或者实现Runnable接口这两种方式来创建多线程，
                 * 但是这两种方式都有个缺陷，就是不能在执行完成后获取执行的结果，
                 * 因此Java 1.5之后提供了Callable和Future接口，
                 * 通过它们就可以在指定的任务执行完毕之后得到任务的执行结果。
                 */
                //这就是重点，使用了Future接口后，可以使用它提供的方法来获取线程任务执行完之后的结果
                System.out.println("task线程的运行结果为"+result02.get());
            }else{
                System.out.println("未获取到结果");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        System.out.println("所有任务执行完毕");
    }
}

