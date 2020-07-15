package 多线程与并发.叶子猿代码.多线程高级.Atomoic原子类;

import java.util.concurrent.atomic.AtomicIntegerArray;

/**
 *2.原子型数组AtomicIntegerArray的使用
 *常用方法如下：
 * public final int get(int i) //获取 index=i 位置元素的值
 * public final int getAndSet(int i, int newValue)//返回 index=i 位置的当前的值，并将其设置为新值：newValue
 * public final int getAndIncrement(int i)//返回 index=i 位置元素的值，并让该位置的元素自增
 * public final int getAndDecrement(int i) //返回 index=i 位置元素的值，并让该位置的元素自减
 * public final int getAndAdd(int delta) //返回 index=i 位置元素的值，并加上预期的值
 * （注意：和原子整型一样，上述方法也有对应的“先操作再返回”的方法，这里不再赘述）
 * boolean compareAndSet(int i, int expect, int update) //如果输入的数值等于预期值，
 * 则以原子方式将 index=i 位置的元素值设置为输入值（update）
 * public final void lazySet(int i, int newValue)//最终 将index=i 位置的元素设置为newValue,
 * 使用 lazySet 设置之后可能导致其他线程在之后的一小段时间内还是可以读到旧的值。
 */

public class AtomicIntegerArrayTest {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        int temvalue = 0;
        int[] nums = { 1, 2, 3, 4, 5, 6 };
        AtomicIntegerArray i = new AtomicIntegerArray(nums);
        for (int j = 0; j < nums.length; j++) {//遍历该数组
            System.out.println(i.get(j));//1 2 3 4 5 6
        }
        temvalue = i.getAndSet(0, 2);//把第一个位置的元素设为2，但返回的还是原值1
        System.out.println("temvalue:" + temvalue + ";  i:" + i);//temvalue:1;  i:[2, 2, 3, 4, 5, 6]
        temvalue = i.getAndIncrement(0);
        System.out.println("temvalue:" + temvalue + ";  i:" + i);//temvalue:2;  i:[3, 2, 3, 4, 5, 6]
        temvalue = i.getAndAdd(0, 5);
        System.out.println("temvalue:" + temvalue + ";  i:" + i);//temvalue:3;  i:[8, 2, 3, 4, 5, 6]
    }

}
