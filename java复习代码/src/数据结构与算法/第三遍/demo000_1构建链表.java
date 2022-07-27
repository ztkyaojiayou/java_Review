package 数据结构与算法.第三遍;

/**
 * @author :zoutongkun
 * @date :2022/4/9 1:27 上午
 * @description :
 * @modyified By:
 */

//定义HeroNode ， 每个HeroNode 对象就是一个节点
class ListNode {
    public int val;
    //指向下一个节点
    public ListNode next;

    //构造器
    public ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }

    public ListNode(int val) {
        this.val = val;
    }

    //get和set方法
    public int getVal() {
        return val;
    }

    public void setVal(int val) {
        this.val = val;
    }

    public ListNode getNext() {
        return next;
    }

    public void setNext(ListNode next) {
        this.next = next;
    }
}


public class demo000_1构建链表 {
    public static void main(String[] args) {
        //猿辅导一面：单元测试自己写！！！
        //构建链表（3-->2--1>）
        //方式1：使用构造器
        ListNode p3 = new ListNode(1, null);
        ListNode p2 = new ListNode(2, p3);
        ListNode root = new ListNode(3, p2);

        //方法2：使用get和set方法

    }
}
