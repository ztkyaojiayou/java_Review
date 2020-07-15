package 多线程与并发.毕向东代码.创建线程的两种方式;

/*class FairLockDemo implements Demo2.java
{
	public void run(Thread t)
	{}
}*/

//创建线程的两种方式可以简写，且这是常用的，务必掌握
public class 创建线程的简写方式
{
    public static void main(String[] args)
    {

        new Thread(new Runnable()
        {
            public void run()
            {
                System.out.println("runnable run");
            }
        })
        {
            public void run()
            {
                System.out.println("subThread run");
            }
        }.start();



		new Thread()
		{
			public void run()
			{
				for(int x=0; x<50; x++)
				{
					System.out.println(Thread.currentThread().getName()+"....x="+x);
				}

			}
		}.start();

		for(int x=0; x<50; x++)
		{
			System.out.println(Thread.currentThread().getName()+"....y="+x);
		}
		Runnable r = new Runnable()
		{
			public void run()
			{
				for(int x=0; x<50; x++)
				{
					System.out.println(Thread.currentThread().getName()+"....z="+x);
				}
			}
		};
		new Thread(r).start();



    }
}

