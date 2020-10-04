package 数据结构与算法.第二遍.队列的实现;
//定义一个节点
class Node {
    int data;
    Node next; //我们需要知道当前结点的后一个结点

    public Node(int data) {
        this.data = data;
    }
}
public class 用链表实现队列 {

        public Node head;//头结点
        public Node current;//当前节点

        //入队
        public void add(int data) {
            if (head == null) {
                head = new Node(data);
                current = head;
            } else {
                Node node = new Node(data);
                current.next = node;//current结点指向下一个节点（即新插入的节点）
                current = node; //使current结点永远为新添加的那个结点
            }
        }

        //出栈
        public Node poll() {
            if (head == null) {
                return null;
            }
            Node node = head; // 头结点是我们要出队的第一个结点
            head = head.next; //每出队一个结点后，head节点后退一位
            return node;
        }
    }

