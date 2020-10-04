package 数据结构与算法.第二遍.栈;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 两个队列实现一个栈（简单）
 * 现有两个队列 q1 和 q2，
 * 1)入栈则将元素加到 q1,这没啥，关键是出栈！
 * 2)出栈的时候先判读 q1 是否为空，因为 q1 中的元素总是后进来的，后进先出,
 * 除了队列的最后一个元素，将其它元素添加到 q2，此时q1的最后一个元素出队，即保证了先进后出
 * 出栈的时候如果在 2 中判断 q1 为空，除了 q2 的最后一个元素，
 * 将 q2 中其它元素添加到 q1，然后 q2 中的最后一个元素出队，即来回交替出队即可！
 *
 * 注意：
 * 队列常用的方法就是：
 * 1）入队：add或offer（常用），返回的是true或false
 * 2）出队：poll（常用）或remove，会返回并删除队头元素
 * 3）是否为空：isEmpty
 *
 * 栈常用的方法就是：
 * 1）进栈：push
 * 2）出栈:pop，会返回栈顶元素
 * 3）是否为空：isEmpty
 */
    class MyStack {
        public MyStack() {
        }
        private Queue<Integer> q1 = new LinkedList<>();
        private Queue<Integer> q2 = new LinkedList<>();


        //1.入栈实现：直接把元素入q1即可，这个很简单
        public boolean push(Integer num) {
            return q1.offer(num);
        }
        //2.出栈实现：在q1和q2中来回出队即可，出队的那个队列只保留那个要出队的元素。
        public Integer pop() {
            //特判
            if (q1.isEmpty() && q2.isEmpty()) {
                return null;
            }
            //2.1先判断 q1 是否为空
            if (!q1.isEmpty()) {//1）不为空时,则先把除了最后一个元素外的所有元素都转移到q2,再弹出剩下的那个元素即可，即此时在q1出栈
                int size = q1.size();
                for (int i = 0; i < size - 1; i++) {
                    q2.offer(q1.poll());
                }
                //在q1中出栈
                return q1.poll();
            } else {//2）为空时，则同样地，把q2中除了最后一个元素外的其他所有元素都转移到q1中，再弹出q2中的最后一个元素即可，即此时在q2出栈，来回交替即可。
                int size = q2.size();
                for (int i = 0; i < size - 1; i++) {
                    q1.offer(q2.poll());
                }
                //在q2中出栈
                return q2.poll();
            }
        }

        //3.栈的长度，即为两个队列的长度之和
        public Integer size() {
            return q1.size() + q2.size();
        }

    }

