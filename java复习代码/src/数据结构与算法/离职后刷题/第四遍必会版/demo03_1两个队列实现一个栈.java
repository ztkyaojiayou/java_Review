package 数据结构与算法.离职后刷题.第四遍必会版;

import java.util.LinkedList;
import java.util.Queue;

//只是相比两个栈实现一个队列较难，但也简单
class myStack {
    //构造函数
    public myStack() {
    }

    //定义两个队列（先进先出）
    Queue<Integer> list1 = new LinkedList<>();
    Queue<Integer> list2 = new LinkedList<>();

    //入栈，直接入栈即可--offer
    public boolean push(Integer num) {
        boolean res = list1.offer(num);
        return res;
    }

    //出栈:来回倒
    public Integer pop() {
        if (list1.isEmpty() && list2.isEmpty()) {
            return null;
        }
        // 若list1不为空，则就在list1中出栈--poll
        // 即取出其最后一个进队列的元素，则先要将前面先进的元素转移到list2中
        if (!list1.isEmpty()) {
            int size = list1.size();
            //将先进list1的元素都转移到list2中，只剩最后一个用于弹出即可
            for (int i = 0; i < size - 1; i++) {
                list2.offer(list1.poll());
            }
            //弹出list1中剩余的那个元素，即为所求
            return list1.poll();
        } else {
            //此时同理
            //ist1为空（即最后一个元素已经弹出了），此时则从list2出栈，同样先转移
            int size = list2.size();
            for (int i = 0; i < size - 1; i++) {
                list1.offer(list2.poll());
            }
            return list2.poll();
        }
    }


    /**
     * 第四遍
     */
    class myStack02 {
        public myStack02() {
        }

        Queue<Integer> list1 = new LinkedList<>();
        Queue<Integer> list2 = new LinkedList<>();

        public boolean push(Integer num) {
            boolean res = list1.offer(num);
            return res;
        }

        public Integer pop() {
            if (list1.isEmpty() && list2.isEmpty()) {
                return null;
            }
            if (!list1.isEmpty()) {
                for (int i = 0; i < list1.size() - 1; i++) {
                    list2.offer(list1.poll());
                }
                return list1.poll();
            } else {
                for (int i = 0; i < list2.size() - 1; i++) {
                    list1.offer(list2.poll());
                }
                return list2.poll();
            }
        }
    }

}
