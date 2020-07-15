package 数据结构与算法.数据结构.栈;
//复制的百度的，没懂
public class LinkedListStackDemo {
    class Node {// 定义节点
        private Node next;
        public Object value;
    }

    Node top = null;

    void init() {//初始化头结点
        top = new Node();
        top.next = null;
        top.value = null;
    }

    public void push(Object element) {//采用头插法的方式模拟入栈
        Node e = new Node();
        e.value = element;
        if (top.next == null) {
            top.next = e;
        } else {
            e.next = top.next;
            top.next = e;

        }

    }

    public Object pop() {//弹出栈顶元素，也就是头结点后面的第一个元素
        Object ele = null;
        if (top.next == null) {
            System.out.println("栈为空!");
        } else {
            ele = top.next.value;
            top.next = top.next.next;//移动指针。相当于删除链表中第一个元素
        }
        return ele;
    }

    public Object peek()//返回栈顶元素,不执行出栈操作
    {
        if(top.next==null)
        {
            return -1;
        }
        else
            return top.next.value;
    }
    public boolean isempty()//判断栈是否为空
    {
        return top.next==null?true:false;
    }
    public int size() {//返回栈的大小，含有的元素个数
        Node temp = top;
        int i = 0;
        while (temp.next != null) {
            i++;
            temp = temp.next;
        }
        return i;
    }

    public void print() {//打印栈中存在的元素
        Node temp = top;
        if(temp.next==null)
        {
            System.out.println("栈为空！");
        }
        while (temp.next != null) {
            System.out.print(temp.next.value + "  ");
            temp = temp.next;
        }
    }

    public static void main(String[] args) {
        LinkedListStackDemo stack = new LinkedListStackDemo();
        stack.init();
        for (int i = 0; i < 5; i++) {
            stack.push(i);
        }
        /*Object ele1 = stack.pop();
        Object ele2 = stack.pop();
        Object ele3 = stack.pop();
        Object ele4 = stack.pop();
        Object ele5 = stack.pop();
        System.out.println(ele1);
        System.out.println(ele2);
        System.out.println(ele3);
        System.out.println(ele4);
        System.out.println(ele5);*/
        Object ele1 = stack.pop();
        System.out.println("此次弹出的元素为:"+ele1);
        System.out.print("栈中剩余的元素为:");
        stack.print();
        System.out.println();
        System.out.println("栈顶元素为:"+stack.peek());
    }
}