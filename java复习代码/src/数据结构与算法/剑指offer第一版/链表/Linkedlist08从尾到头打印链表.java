package 数据结构与算法.剑指offer第一版.链表;

//注意：在牛客系统上，只要是需要用到额外的包，一般都是util目录下的，因此可以直接写成：
//import java.util.*;
import java.util.ArrayList;
import java.util.Stack;

/**
 * 【题目】：从尾到头反过来打印出每个结点的值。
 * （务必注意：这里的打印并不需要真正的打印出来，而只需要把满足要求的序列放入到指定的ArrayList<Integer>即可）
 *
 * 【思路】：不改变原数据结构
 *
 * （1）用栈（先进后出）
 * 由于listNode 是链表，只能从头遍历到尾，但是输出却要求从尾到头，这是典型的"先进后出"，因此可以使用栈
 * 1.先把链表的值一个一个存入到栈中
 * 2.再一个一个出栈，把pop出的元素存入到list中，返回此list即可（由于先进后出，因此可以得到倒序的序列）
 *
 * （2）递归（先递归到最后一个结点，再逆序添加每个结点至list中即可）
 * 既然非递归都实现了，那么我们也可以利用递归实现，不过由于递归方法一般都较简单，因此面试一般不太感兴趣，但是也必须要会。
 * 分析：例如，要逆序打印链表 1->2->3（3,2,1)，可以先逆序打印链表 2->3(3,2)，最后再打印第一个节点 1。
 *    而链表 2->3 可以看成一个新的链表，要逆序打印该链表可以继续使用求解函数，也就是在求解函数中调用自己，这就是递归函数。
 *
 * 【回顾】：栈Stack的常用方法(先进后出)
 * (1)属于stack自己的方法包括
 * push(num) //把数num入栈
 * pop() //栈顶元素出栈（或删除）
 * empty() //判定栈是否为空
 * peek() //获取栈顶元素
 * search(num) //判断元素num是否在栈中，如果在返回1，不在返回-1。
 * (2)注意pop()和peek()的区别：
 * pop()会弹出/删除栈顶元素并返回栈顶的值
 * peek()只是获取栈顶的值，但并不会把元素从栈顶弹出来。
 */
class ListNode08 {
    int val;
    ListNode08 next = null;
    ListNode08(int val) {
             this.val = val;
         }
   }

public class Linkedlist08从尾到头打印链表 {
    /**
     * 方法一：用栈
     * @param listNode
     * @return
     */
    public ArrayList<Integer> printListFromTailToHead01(ListNode08 listNode) {

        Stack stack = new Stack();
        //1.先把链表的值一个一个存入到栈中
        while (listNode != null) {
            stack.push(listNode.val);
            listNode = listNode.next;//指向下一个结点
        }
        //2.再一个一个出栈，把pop出的元素存入到list中，返回此list即可（由于先进后出，因此可以得到倒序的序列）
        ArrayList list = new ArrayList();
        while (!stack.empty()) {
            list.add(stack.pop());//只能从栈顶一个一个出栈，不能指定参数
        }
        return list;
    }

    /**
     * 方法二：递归
     * 如：要逆序打印链表 1->2->3（3,2,1)，可以先逆序打印链表 2->3(3,2)，最后再打印第一个节点 1。
     * 而链表 2->3 可以看成一个新的链表，要逆序打印该链表可以继续使用求解函数，也就是在求解函数中调用自己，这就是递归函数。
     */
    ArrayList list = new ArrayList();
    public ArrayList printListFromTailToHead(ListNode08 listNode) {
        if(listNode!=null){
            //即只要没有到达最后一个结点，就一直递归下去
            //直到listNode=null，退出if语句，开始倒序执行list.add(listNode.val)
            //（这是递归的过程和特点，先递归到最后的判断条件/已知条件，再利用已知条件一步一步逆序执行）
            //这样就实现了倒序序列
            printListFromTailToHead(listNode.next);
            list.add(listNode.val);//递归到最后才开始执行这条语句，务必理解
        }
        return list;
    }
}
