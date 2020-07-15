package 多线程与并发.叶子猿代码.多线程高级.Atomoic原子类;

import java.util.concurrent.atomic.AtomicInteger;

//1.原子型整型类AtomicInteger的使用
public class AtomicIntegerTest {
    /**
     * （1）public final int get() //获取当前的值
     * （类似i++）
     * （2）public final int getAndSet(int newValue)//先获取旧值，并设置新的值
     * （3）public final int getAndIncrement()//先获取旧值，再自增1
     * （4）public final int getAndDecrement() //先获取旧值，再自减1
     * （5）public final int getAndAdd(int delta) //先获取旧值，再加上传入的值i++
     * （类似++i）
     * （6）public final int incrementAndGet()//先自增1，再返回
     * （7）public final int decrementAndGet()//先自减1，再返回
     * （8）public final int addAndGet(int delta)//先加上传入的值，再返回
     * （CAS操作）
     * （9）boolean compareAndSet(int expect, int update) //如果输入的数值等于预期值，
     * 则以原子方式将该值设置为输入值（update）
     * （懒设置）
     * （10）public final void lazySet(int newValue)//最终设置为newValue,使用 lazySet 设置之后可能
     * 导致其他线程在之后的一小段时间内还是可以读到旧的值。
     */
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        int temvalue = 0;
        AtomicInteger i = new AtomicInteger(0);
        temvalue = i.getAndSet(3);
        System.out.println("旧值temvalue为:" + temvalue + ";  i:" + i);//temvalue:0;  i:3
        temvalue = i.getAndIncrement();
        System.out.println("旧值temvalue为:" + temvalue + ";  i:" + i);//temvalue:3;  i:4
        temvalue = i.getAndAdd(5);
        System.out.println("旧值temvalue为:" + temvalue + ";  i:" + i);//temvalue:4;  i:9
    }

}
