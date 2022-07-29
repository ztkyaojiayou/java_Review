package 华为od机考练习.常规题库;

/**
 * 48）从单向链表中删除指定值的节点
 *
 * @author :zoutongkun
 * @date :2022/7/29 9:59 上午
 * @description :
 * @modyified By:
 */

import java.util.*;

public class Main48从单向链表中删除指定值的节点 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            //总结点数
            int totalNode = Integer.parseInt(sc.next());
            //头结点
            int headVal = Integer.parseInt(sc.next());
            //构造链表
            ListNode head = new ListNode(headVal);
            for (int i = 1; i < totalNode; i++) {
                //next
                int nextVal = Integer.parseInt(sc.next());
                //pre
                int preVal = Integer.parseInt(sc.next());
                //定义临时结点指向head结点来遍历链表，而不要使用head本身
                ListNode curNode = head;
                //遍历
                while (curNode != null) {
                    //找到要添加的结点
                    if (curNode.val == preVal) {
                        //添加新节点
                        //先构建新结点
                        ListNode newNode = new ListNode(nextVal);
                        //再插进去（下面这两步很好理解）
                        newNode.next = curNode.next;
                        curNode.next = newNode;
                    }
                    ////遍历下一个结点
                    curNode = curNode.next;
                }
            }
            //拼接结果
            StringBuilder sb = new StringBuilder();
            //获取要删除的结点
            int tarDeleteVal = Integer.parseInt(sc.next());
            //同理，使用一个临时结点遍历链表
            ListNode curNode = head;
            //删除目标结点，不过设立我们换个思路，即直接打印不删除的结点
            //代码上，和新增结点的逻辑几乎相同
            while (curNode != null) {
                //若当前结点为要删除的结点，则不打印即可（而不必先把删除后的链表求出来！！！）
                if (curNode.val != tarDeleteVal) {
                    //直接打印出来
                    //要求每个数后面都加空格
                    sb.append(curNode.val).append(" ");
                }
                //遍历下一个结点
                curNode = curNode.next;
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

        //构造函数--只构造val
        ListNode(int val) {
            this.val = val;
        }
    }

}
