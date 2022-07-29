package 华为od机考练习.常规题库;

/**
 * 51）输出单向链表中倒数第k个结点--原题
 * 注意：在牛客网中，链表的结点和链表都需要自己构造--务必掌握！！！
 * 1）方法1:顺序查找--这里先掌握这个方法
 * 假设当前链表的长度为 n，则我们知道链表的倒数第 k 个节点即为正数第 n - k 个节点，
 * 此时我们只需要顺序遍历到链表的第 n - k + 1个节点即为倒数第 k个节点。
 * 我们首先求出链表的长度 n，然后顺序遍历到链表的第 n - k个节点返回即可
 * 2）方法2：快慢指针
 *
 * @author :zoutongkun
 * @date :2022/7/27 3:44 下午
 * @description :
 * @modyified By:
 */

import java.util.Scanner;

class Main51 {
    public static void main(String[] args) {
        // 1.输入内容
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            // 链表长度
            int len = sc.nextInt();
            // 定义表头，value任意取值，一般就取-1
            ListNode head = new ListNode(-1);
            //构建链表--务必掌握
            ListNode curNode = head;
            for (int i = 0; i < len; i++) {
                int value = sc.nextInt();
                ListNode newNode = new ListNode(value);
                curNode.next = newNode;
                //连接下次结点
                curNode = curNode.next;
            }

            // 2.遍历倒数第k节点的指针
            int k = sc.nextInt();
            // 求倒数第k个结点，就相当于求顺数第n-k+1个结点
            for (int i = 0; i < len - k + 1; i++) {
                head = head.next;
            }
            System.out.println(head.value);
        }
    }
}

/**
 * 自定义链表结点
 */
class ListNode {
    //不用定义为privat就行，否则不方便直接访问
    Integer value;
    ListNode next;

    // 有参构造函数
    // 无需对next变量进行构造
    public ListNode(Integer value) {
        this.value = value;
    }
}

