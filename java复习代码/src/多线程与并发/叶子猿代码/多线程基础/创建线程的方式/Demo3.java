package 多线程与并发.叶子猿代码.多线程基础.创建线程的方式;


//创建线程的方式3:通过匿名内部类
public class Demo3 {

    public static void main(String[] args) {

		/*new Thread() {
			public void run() {
				System.out.println("thread start ..");
			};
		}.start();*/


		/*new Thread(new Demo2.java() {
			@Override
			public void run() {
				System.out.println("thread start ..");
			}
		}).start();*/


        new Thread(new Demo2() {
            @Override
            public void run() {
                System.out.println("runnable");
            }//这里的run方法会被下面的run方法覆盖，因此，这里的代码根本不会执行。
        }) {
            public void run() {
                System.out.println("sub");
            };
        }.start();
        //结果是：sub

    }

}
