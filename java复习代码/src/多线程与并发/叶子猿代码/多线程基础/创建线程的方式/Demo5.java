package 多线程与并发.叶子猿代码.多线程基础.创建线程的方式;


import java.util.Timer;
import java.util.TimerTask;

//创建线程的方式5:通过定时器来创建线程（主要就是一些API的应用，没有什么技术含量）
public class Demo5 {

    public static void main(String[] args) {

        Timer timer = new Timer();

        timer.schedule(new TimerTask() {

            @Override
            public void run() {
                // 实现定时任务
                System.out.println("定时任务 is run");
            }
        }, 0, 1000);//每隔1秒 零延时地执行此定时任务

    }

}

