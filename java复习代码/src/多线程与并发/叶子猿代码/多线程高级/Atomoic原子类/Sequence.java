package 多线程与并发.叶子猿代码.多线程高级.Atomoic原子类;


import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicIntegerArray;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import java.util.concurrent.atomic.AtomicReference;


//使用原子类（AtomicXXX）解决安全性问题（很简单，不要被吓到，只不过是一些新知识而已，新不等于难）

public class Sequence {
    //1.具有原子性的int（基本）类型的创建方法
    //这个AtomicInteger就相当于具有原子性的int类型，对应的还有其他原子类（先new出此对象，再用它的实例对象使用对应的方法实现自增自减等操作）
    private AtomicInteger atomicValue = new AtomicInteger(0);
    //2.具有原子性的数组类型的创建方法,先创建一个普通数组
    private int [] s = {2,1,4,6};

    AtomicIntegerArray atomicArr = new AtomicIntegerArray(s);

    //3.具有原子性的抽象类型（如：对象）的创建方法（先要创建此对象）
    AtomicReference<User001> user = new AtomicReference<>();

    AtomicIntegerFieldUpdater<User001> old =  AtomicIntegerFieldUpdater.newUpdater(User001.class, "old");

    /**
     * @return
     */
    public  int getNext() {

//        User001 user = new User001();
//        System.out.println(old.getAndIncrement(user));//使old字段的值加一
//        System.out.println(old.getAndIncrement(user));
//        System.out.println(old.getAndIncrement(user));
//
//
//        atomicArr.getAndIncrement(2);
//        atomicArr.getAndAdd(2, 10);
        return atomicValue.getAndIncrement();//这就相当于在原子性前提下的 atomicValue++
    }

    public static void main(String[] args) {

        Sequence s = new Sequence();

        new Thread(new Runnable() {

            @Override
            public void run() {
//				while(true) {
                System.out.println(Thread.currentThread().getName() + " " + s.getNext());
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
//				}
            }
        }).start();

		new Thread(new Runnable() {

			@Override
			public void run() {
				while(true) {
					System.out.println(Thread.currentThread().getName() + " " + s.getNext());
					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}).start();

		new Thread(new Runnable() {

			@Override
			public void run() {
				while(true) {
					System.out.println(Thread.currentThread().getName() + " " + s.getNext());
					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}).start();

    }

}

