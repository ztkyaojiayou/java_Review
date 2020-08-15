package 数据结构与算法.剑指offer题解.第二遍.堆排序_topK问题;

import java.util.ArrayList;
import java.util.PriorityQueue;

// 典型的topK问题，使用堆排序，
//（1）首先明确一个基本概念：
// 1）大顶堆：堆顶元素最大，其他元素都小于它，且删除时删除的也是堆顶元素，
// 适用于“最小的k个数”问题，可以使用优先队列的降序排序（需自定义）实现。
// 2）小顶堆：堆顶元素最小，其他元素都大于它，且删除时删除的也是堆顶元素，
// 适用于“最大的k个数”问题，可以使用优先队列的升序排序（默认）实现。
//
//（2）对于本题，因为是找最小的k个数，则说明这k个数有最大值，
// 因此当堆中有k个数时，则对于后面来的更大的数，我们要把它踢出去，
// 而元素只能从堆顶删除，因此我们就是要维护一个大顶堆即可，
// 这样就可以确保把更大的值踢出去，
// 我们不需要自己去实现了，没意思，直接使用优先队列就行，
// 但因为优先队列默认是升序排列，
// 即队首元素为最小值，因此我们要自定义排序规则，
// 使得其按照降序排列，也即使得队首元素为最大值。
public class 最小的k个数 {
public ArrayList<Integer> getTopK(int[] nums,int k){
    if (k > nums.length || k <= 0){
        return new ArrayList<>();
    }
    // 对优先队列中的元素降序排列，实现大顶堆，
    // 具体做法就是：对于o1和o2，若o2-o1>0,则返回1，即o2>o1,即为正确顺序，
    // 即就按照（02,01）的顺序排列，否则返回-1，即o1>o1,即就按照（o1，o2）的顺序排列，
    // 易知，这就实现了降序排列
    PriorityQueue<Integer> maxheap = new PriorityQueue<>((o1, o2) -> o2 - o1);
    for (int i= 0;i < nums.length; i++){
        maxheap.add(nums[i]);
        if (maxheap.size() > k){
            maxheap.poll();
        }
    }
    return new ArrayList<>(maxheap);
}
}
