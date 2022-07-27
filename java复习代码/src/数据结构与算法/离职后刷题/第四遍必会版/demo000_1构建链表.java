package 数据结构与算法.离职后刷题.第四遍必会版;

/**
 * @author :zoutongkun
 * @date :2022/4/9 1:27 上午
 * @description :
 * @modyified By:
 */

//定义HeroNode ， 每个HeroNode 对象就是一个节点
class ListNode {
    private int val;
    //指向下一个节点
    private ListNode next;

    //构造器（不能private，但是一般不使用带next的构造器，因为不够灵活呀）
    ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }

    //一般就是用这个构造方法即可，然后使用next进行连接，
    // 而不用直接在构造方法中定死下一个结点
    public ListNode(int val) {
        this.val = val;
    }

    //get和set方法

    public void setVal(int val) {
        this.val = val;
    }

    public void setNext(ListNode next) {
        this.next = next;
    }

    public int getVal() {
        return val;
    }

    public ListNode getNext() {
        return next;
    }

}


public class demo000_1构建链表 {
    public static void main(String[] args) {
        //猿辅导一面：单元测试自己写！！！
        //构建链表（3-->2--1>）
        //方式1：使用构造器（通常不用，因为一般而言，不会定义带有next的构造器，因为灵活性不够）
        ListNode p3 = new ListNode(1, null);
        ListNode p2 = new ListNode(2, p3);
        ListNode root = new ListNode(3, p2);
        //方法2：使用get和set方法（更常用，因为类中的变量通常要设置为private）
        root.setNext(p2);
        p2.setNext(p3);
        p3.setNext(null);
    }
}
