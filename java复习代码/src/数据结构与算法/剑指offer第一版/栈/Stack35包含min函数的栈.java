package 数据结构与算法.剑指offer第一版.栈;

import java.util.Stack;

/**
 * 题目： 定义栈的数据结构，请在该类型中实现一个能够得到栈的最小元素的 min 函数。
 *       在该栈中，调用 min、push 及 pop 的时间复杂度都是 0(1)
 *       注意：保证测试中不会当栈为空的时候，对栈调用pop()或者min()或者top()方法。
 *
 * 【解】：
 *
 * 要用到两个栈：stack和minStack
 * 1.栈stack用来存所有的元素，即把传入的元素入栈
 * 2.栈minStack用来在push()的时候存加入新的元素后当前stack中对应的最小值。
 * （易知，只需比较要存入stack的新元素与minStack的栈顶元素比较即可）
 *   两个栈中的元素数量始终保持一致，当新的元素小于“minStack”栈顶元素时，
 *   “minStack”向栈顶push新来的元素，否则，“minStack”向栈顶加入原栈顶元素。
 *   执行“pop”方法时，两个栈同时弹出各自的栈顶元素，
 *   把栈minStack的栈顶元素弹出是为了保证minStack的栈顶元素始终为剩余元素中的最小值。
 */

public class Stack35包含min函数的栈 {

    private Stack<Integer> stack = new Stack<>();//定义一个普通栈，用于入栈和出栈操作
    private Stack<Integer> minStack = new Stack<>();//定义一个辅助栈，用于存放最小值

    //1.（简单）入栈操作（即插入/添加元素）
    public void push(int node) {
        //1.1直接入栈到dataStack即可
        stack.push(node);
        //1.2(关键）但同时要把较小元素存入（push）到minStack栈中
        //当minStack.isEmpty()时即插人第一个元素，之后每push之前都必须与栈顶元素进行比较，
        // 取最小值插入(注意：相同元素不会被覆盖，也是照样存入）
        //minStack.push(minStack.isEmpty() ? node : Math.min(minStack.peek(), node));这是骚操作写法，下面贴的是普通版
        if (minStack.isEmpty()){//若minStack中没有元素，则直接插入当前元素，否则就要和栈顶元素比较，插入较小值。
            minStack.push(node);
        }
        minStack.push(Math.min(minStack.peek(),node));

    }

    //2.（简单）出栈操作（即移除堆栈顶部的元素）
    public void pop() {
        //2.1直接从dataStack中弹出即可
        stack.pop();
        //2.2（关键）但务必注意：
        // 由于minStack中的元素和stack中的元素个数是相同的，
        // 只是minStack中存的都是每次存入stack中元素的最小值
        // 因此当从stack中每弹出一个元素时，也要把minStack中的第一个元素弹出，
        // 以保证minStack的栈顶元素始终为剩余元素的最小值
        minStack.pop();
    }

    // 3.（简单）查看栈顶部的元素（但不从堆栈中移除它），
    // 其实就是调用peek（）方法即可；考的是我们对Stack的API是否熟悉
    public int top() {
        return stack.peek();
    }

    //4.获取栈的最小元素，直接从minStack中弹出第一个元素即可
    //这是关键，但也不难，它的关键是在push和pop操作时考虑如何使得该栈中存的是最小元素
    public int getMin() {
        return minStack.peek();
    }
}
