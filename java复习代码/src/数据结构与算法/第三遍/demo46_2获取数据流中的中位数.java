package 数据结构与算法.第三遍;

import java.util.PriorityQueue;

public class demo46_2获取数据流中的中位数 {
    //大顶堆，堆顶元素最大，但依然要设计成比小顶堆的堆顶元素小，这是重点
    PriorityQueue<Integer> left_min = new PriorityQueue<>((o1, o2) -> o2 - o1);
    //小顶堆（默认），堆顶元素最小，但依然要设计得比大顶堆的堆顶元素大
    PriorityQueue<Integer> right_max = new PriorityQueue<>();
    int num = 0;

    //方法1：先添加元素
    public void insert(int val){//表示要加这个元素
        if (num % 2 == 0){
            //若为偶数，则加入left，并把left中的最大元素加入到right
            left_min.add(val);
            right_max.add(left_min.poll());//注意，这里是真把这个元素移过去，即left中要删除此元素
        }else {
            //若为奇数，则反之,即加入right,同时把right中的最小元素加入到left中（注意，易知，该元素虽然是right中的最小元素，但是left中的最大元素，位于栈顶）
            right_max.add(val);
            left_min.add(right_max.poll());
        }
        num++;//表示添加下一个元素，具体为哪个元素不用管，这个不重要
    }

    //方法2：再实时求当前数据流的中位数（易知，题目就是要求我们能够随时获取当前数据流中的中位数）
    public Double getMedian(){
        if (num % 2 == 0){
            //为偶数时，易知即为两个堆的堆顶元素的平均值
            return (left_min.peek() + right_max.peek()) / 2.0;
        }else {
            //为奇数时，则取中间那个数即可，上述代码是把其放入了右边的堆中，因此取右边的堆顶元素即可
            return (double) right_max.peek();
        }
    }
}
