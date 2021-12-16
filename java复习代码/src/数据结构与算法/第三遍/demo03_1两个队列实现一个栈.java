package 数据结构与算法.第三遍;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

class myStack {
    //构造函数
    public myStack() {
    }

    //定义两个队列
    Queue<Integer> list1 = new LinkedList<Integer>();
    Queue<Integer> list2 = new LinkedList<Integer>();

    //入栈，直接入栈即可
    public boolean push(Integer num) {
        boolean res = list1.offer(num);
        return res;
    }

    //出栈:来回倒
    public Integer pop() {
        if (list1.isEmpty() && list2.isEmpty()) {
            return null;
        }
        if (!list1.isEmpty()) {//若list1不为空，则就在list1中出栈，但要先转移剩余的数到list2中
            int size = list1.size();
            for (int i = 0; i < size - 1; i++) {
                list2.offer(list1.poll());
            }
            return list1.poll();
        } else {
            //说明list1为空，则从list2出栈，同样先转移
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
