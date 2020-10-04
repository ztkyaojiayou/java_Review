package 秋招笔试.同程艺龙;

class ListNode {//要返回的链表的节点类
    public int val;
    public ListNode next;//定义一个节点，用于表示下一个节点
    public ListNode(int x) { val = x; }
}
public class test01 {
    //递归法很简单，先会做再说
    public ListNode method(ListNode list1, ListNode list2) {
        if (list1 == null){
            return list2;
        }
        if (list2 == null){
            return list1;
        }

        if (list1.val < list2.val){
            list1.next = method(list1.next,list2);
            return list1;
        }else{
            list2.next = method(list1,list2.next);
            return list2;
        }
    }
}
