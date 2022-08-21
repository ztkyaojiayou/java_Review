package 数据结构与算法.od机考.华为od机考练习.常规题库;

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

public class Main51_自写 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        //这里虽然也是常规的输入，但似乎需要加while才行
        while (sc.hasNext()) {
            //录入第一行--链表长度
            int len = sc.nextInt();
            //构造链表
            //定义头结点
            ListNode51 head = new ListNode51(-1);
            //使用一个临时结点构造链表
            //先指向头结点
            ListNode51 curNode = head;
            for (int i = 0; i < len; i++) {
                int curVal = sc.nextInt();
                //构建新节点--先填充val
                ListNode51 newNode = new ListNode51(curVal);
                //连接新节点
                curNode.next = newNode;
                //后移
                curNode = curNode.next;
            }

            //至此，链表连接完毕
            //打印倒数第K个结点即可--也即顺数第len-k+1个结点
            int k = sc.nextInt();
            //用于遍历
            ListNode51 temp = head;
            for (int i = 0; i < len - k + 1; i++) {
                temp = temp.next;
            }
            System.out.println(temp.val);
        }


    }

}

/**
 * 自定义链表结点
 */
class ListNode51 {
    public int val;
    public ListNode51 next;

    //有参构造函数--构造val即可
    public ListNode51(int val) {
        this.val = val;
    }
}


