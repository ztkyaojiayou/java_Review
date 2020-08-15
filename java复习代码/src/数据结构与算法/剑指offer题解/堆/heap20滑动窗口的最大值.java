package 数据结构与算法.剑指offer题解.堆;

import java.util.*;

/**
 * 题目：给定一个数组和滑动窗口的大小，请找出所有滑动窗口里的最大值。
 * 例如，如果输入数组{2,3,4,2,6,2,5,1}及滑动窗口的大小3，
 * 那么一共存在 6 个滑动窗口，它们的最大值分别为{4,4,6,6,6,5}。
 *
 *【解】：有两种方法，
 * 方法一：使用普通方法，用一个list保存最大值即可
 * 方法二：使用大顶堆的思想（利用优先队列实现）
 * 方法三：使用一个双端队列
 *        队列里存的是数组元素的下标
 *        当前队列头永远放的是当前窗口的最大值
 *
 */

/**
 * 方法一：直接法（暴力破解法）
 * 特点：简单易懂，且不占内存，速度快，虽然思路简单，但还是比较推荐的
 */
public class heap20滑动窗口的最大值 {
    public ArrayList<Integer> maxInWindows01(int [] num, int size){
            //0.创建一个list。用于存储结果集，最终返回
            ArrayList<Integer> resList = new ArrayList();
            //1.先处理特例/边界情况
            if(size ==0) return resList;//这种情况不能省
            if(num.length<size){//但这种情况可以省
            }
            //2.使用两个变量 i，j 开始遍历整个数组，和max比较，把最大值存入resList中
            for(int i=0;i<num.length-size+1;i++){//length-size+1为窗口个数，即：寻找/循环这么多次即可
                int max =0;
                for(int j=i;j<i+size;j++){
                    if(max<num[j]){
                        max =num[j];
                    }
                }
                resList.add(max);
            }
            return resList;
    }

    /**
     * 方法二：大顶堆法：使用 “优先队列+自定义一个降序比较器” 实现大顶堆
     * 特点：方法较新奇（思路还是简单），但耗时长（148ms），且耗内存（15M），不太推荐
     */

    public ArrayList maxInWindows02(int[] num, int size) {
            if (num == null || num.length == 0 || size == 0 || size > num.length) {
                return new ArrayList<>();
            }
            //1.创建一个“窗口个数”大小的list（length - size + 1即为窗口个数，可以验证），用于存储每个窗口的最大值，即为所求，最后返回
            ArrayList<Integer> resList = new ArrayList<>(num.length - size + 1);
            // 2.利用优先队列实现一个大顶堆（则要降序）
            Queue<Integer> maxQueue = new PriorityQueue<>(((o1, o2) -> o2 - o1));//这是自定义的比较器，为了实现降序
            //3.开始寻找最大值
            // 3.1先处理第一个窗口（大小为size），再把最大值存入到resList中
            for (int i = 0; i < size; i++) {
                maxQueue.offer(num[i]);//把前三个数添加到优先队列中，添加完之后就为降序排列啦
            }
        resList.add(maxQueue.peek());//再把最大值（此时即优先队列maxQueue中的队首元素）添加到resList中，这次添加的是第一个窗口的最大值
            //3.2接着处理后面的窗口，索引从size开始即可
        for (int i = size; i < num.length; i++) {
                // 3.2.1先移除num数组最左的那个数
                maxQueue.remove(num[i - size]);
                // 3.2.2再往num数组加入下一个数/一个新的数
                maxQueue.offer(num[i]);
                // 3.2.3再找到最大的存入resList中，size加一，不断循环即可
            resList.add(maxQueue.peek());
            }
            return resList;
        }
    /**
     * 方法三：双端队列法（似乎都在推荐这个方法，但我没有搞懂）
     * 滑动窗口的最大值总是保存在队列首部，队列里面的数据总是从大到小排列。
     */
    public ArrayList maxInWindows03(int[] num, int size) {
        ArrayList res = new ArrayList();
        if (num == null || num.length == 0 || size == 0 || size > num.length) {
            return res;
        }
        Deque deque = new LinkedList();//双端队列
        for (int i = 0; i < num.length; i++) {
            if (!deque.isEmpty()) {
                // 如果队列头元素不在滑动窗口中了，就删除头元素
                if (i >= (Integer)deque.peek() + size) {
                    deque.pop();
                }
                // 如果当前数字大于队列尾，则删除队列尾，直到当前数字小于等于队列尾，或者队列空
                while (!deque.isEmpty() && num[i] >= num[(Integer)deque.getLast()]) {
                    deque.removeLast();
                }
            }
            deque.offer(i); // 入队列
            // 滑动窗口经过一个滑动窗口的大小，就获取当前的最大值，也就是队列的头元素
            if (i + 1 >= size) {
                res.add(num[(Integer)deque.peek()]);
            }
        }
        return res;
    }
}
