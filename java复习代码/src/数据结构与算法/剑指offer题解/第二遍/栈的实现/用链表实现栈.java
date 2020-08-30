package 数据结构与算法.剑指offer题解.第二遍.栈的实现;
//定义一个节点
class Node {
    int data;
    Node pre; //我们需要知道当前结点的前一个结点

    public Node(int data) {
        this.data = data;
    }
}
public class 用链表实现栈 {

    public Node head;//头结点
    public Node current;//当前节点

    //入栈
    public void push(int data) {
        if (head == null) {
            head = new Node(data);
            current = head;
        } else {
            Node node = new Node(data);
            node.pre = current;//current结点将作为当前结点的前驱结点
            current = node; //让current结点永远指向新添加的那个结点
        }
    }

    //出栈
    public Node pop() {
        if (current == null) {
            return null;
        }
        Node node = current; // current结点是我们要出栈的结点
        current = current.pre; //每出栈一个结点后，current后退一位
        return node;
    }
}
