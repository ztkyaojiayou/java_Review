package 华为od机考练习.常规题库;

/**
 * @author :zoutongkun
 * @date :2022/7/29 9:59 上午
 * @description :
 * @modyified By:
 */

import java.util.*;

public class Main48_自写 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            int totalNode = Integer.parseInt(sc.next());
            int headVal = Integer.parseInt(sc.next());
            //构造链表
            ListNode head = new ListNode(headVal);
            for (int i = 1; i < totalNode; i++) {
                //next
                int nextVal = Integer.parseInt(sc.next());
                //pre
                int preVal = Integer.parseInt(sc.next());
                ListNode temp = head;
                while (temp.val != preVal) {
                    temp = temp.next;
                }
                ListNode newNode = new ListNode(nextVal);
                newNode.next = temp.next;
                temp.next = newNode;
            }
            //拼接结果
            StringBuilder sb = new StringBuilder();
            //要删除的结点
            int tarDeleteVal = Integer.parseInt(sc.next());
            ListNode temp = head;
            while (temp != null) {
                //若当前结点为要删除的结点，则不打印即可（而不必先把删除后的链表求出来！！！）
                if (temp.val != tarDeleteVal) {
                    sb.append(temp.val).append(" ");
                }
                //遍历下一个结点
                temp = temp.next;
            }
            System.out.println(sb);
        }
    }


    /**
     * 定义链表结点
     */
    private static class ListNode {
        int val;
        ListNode next;

        ListNode(int val) {
            this.val = val;
        }
    }

}
