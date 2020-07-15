package 多线程与并发.叶子猿代码.多线程高级.Volatile关键字;

//作用1：能保证被原子性操作/修改的共享变量的可见性（但保证不了安全性）
public class Demo2 {

    public volatile boolean run = false;//标记位，修改时对所有线程都可见

    public static void main(String[] args) {

        Demo2 d = new Demo2();

        //创建2个线程并启动
        //线程1
        new Thread(new Runnable() {

            @Override
            public void run() {
                for(int i = 1;i<=10;i++) {
                    System.err.println("执行了第 " + i + " 次了");
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                d.run = true;//设置为true
            }
        }).start();

        //线程2
        new Thread(new Runnable() {

            @Override
            public void run() {
                while(!d.run) {//run为true时不执行，一直处于可运行状态
                    // 不执行
                }
                //否则，执行
                System.err.println("线程1执行了10次后，把用volatile修饰的run标记位修改了，" +
                        "因为对所有线程都可见，所有此时线程2就执行啦...");
            }
        }).start();


    }

}

