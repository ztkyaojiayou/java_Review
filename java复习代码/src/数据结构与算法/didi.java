package 数据结构与算法;

/**
 * @author :zoutongkun
 * @date :2022/5/6 8:30 下午
 * @description :
 * @modyified By:
 */
public class didi {
    public boolean method(ListNode head1,ListNode head2){
        ListNode p1 = head1;
        ListNode p2 = head2;
        if (p1 == p2){
            return true;
        }
        while (p1 != p2){
            p1 = p1.next;
            p2 = p2.next;
            if (p1 == null ){
                p1 = head2;
            }
            if (p2 == null){
                p2 = head1;
            }
        }
        if (p1 != p2){
            return false;
        }
        return true;
    }
}
