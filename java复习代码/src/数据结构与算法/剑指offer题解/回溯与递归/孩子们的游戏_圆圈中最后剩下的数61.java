package 数据结构与算法.剑指offer题解.回溯与递归;

import 数据结构与算法.ListNode;
/**
 * 题目描述
 * 每年六一儿童节,牛客都会准备一些小礼物去看望孤儿院的小朋友,今年亦是如此。
 * HF作为牛客的资深元老,自然也准备了一些小游戏。
 * 其中,有个游戏是这样的:首先,让小朋友们围成一个大圈。
 * 然后,他随机指定一个数m,让编号为0的小朋友开始报数。
 * 每次喊到m-1的那个小朋友要出列唱首歌,然后可以在礼品箱中任意的挑选礼物,
 * 并且不再回到圈中,从他的下一个小朋友开始,继续0...m-1报数....这样下去....直到剩下最后一个小朋友,可以不用表演,
 * 并且拿到牛客名贵的“名侦探柯南”典藏版(名额有限哦!!^_^)。
 * 请你试着想下,哪个小朋友会得到这份礼品呢？(注：小朋友的编号是从0到n-1)
 *
 * 如果没有小朋友，请返回-1
 */

/**
 * 思路解析：递归法
 * 该题即为经典的约瑟夫环问题，圆圈长度为 n 的解可以看成长度为 n-1 的解再加上报数的长度 m。
 * 因为是圆圈，所以最后需要对 n 取余。
 *
 * 题目抽象：给定一个由[0...n-1]构成的数组，第一次从0开始数m个数，然后删除，
 * 以后每次都从删除的数下一个位置开始数m个数，然后删除，直到剩余一个数字，找出那个数字。
 * 比如：arr = [0 1 2 3 4]， m = 3
 * 第一次：删除2，变成 arr = [0 1 3 4]
 * 第二次，删除0，变成 arr = [1 3 4]
 * 第三次，删除4，变成 arr = [1 3]
 * 第四次，删除1，变成 arr = [3]
 *
 * 假设f(n, m) 表示最终留下元素的序号。比如上例子中表示为:f(5,3) = 3
 * 首先，长度为 n 的序列会先删除第 m % n 个元素，然后剩下一个长度为 n - 1 的序列。
 * 那么，我们可以递归地求解 f(n - 1, m)，就可以知道对于剩下的 n - 1 个元素，
 * （即把这个圈拉直，把这个退出的同学的前面的数接在后面）
 * 最终会留下第几个元素，我们设答案为 x = f(n - 1, m)。
 * 由于我们删除了第 m % n 个元素（下标从0开始），将序列的长度变为 n - 1。
 * 当我们知道了 f(n - 1, m) 对应的答案 x 之后，我们也就可以知道，长度为 n 的序列最后一个删除的元素，
 * 应当是从 m % n 开始数的第 x 个元素。因此有 f(n, m) = (m % n + x) % n = (m + x) % n，再把x = f(n - 1, m)代入就可得：
 * f(n, m) = (f(n - 1, m) + m) % n，当n等于1时，f(1,m) = 0。
 *
 * 思路2：迭代
 * 同时还可以继续简化，即把f(n, m)的m去掉，即有： f[n] = (f[n-1] + m) % n。
 * 当n等于1时，有f[1] = 0。
 * 其迭代过程为：
 * f[2] = (f[1] + m) % 2
 * f[3] = (f[2] + m) % 3
 * ...
 * f[n] = (f[n-1] + m) % n
 */

/**
 * 方法1：递归法
 */
class 孩子们的游戏_圆圈中最后剩下的数61_1 {
    public int LastRemaining_Solution(int n, int m) {//n为人数，m为随机指定的一个数
            if (n == 0)     /* 特殊输入的处理 */
                return -1;
            if (n == 1)     /* 递归返回的条件 */
                return 0;
            return (LastRemaining_Solution(n - 1, m) + m) % n;
        }
    }

/**
 * 方法2：迭代法（推荐）
 */
class Solution61_2 {
    public int LastRemaining_Solution(int n, int m) {
        //0.特判
        if(n == 0)
            return -1;
        //person_i就表示有i个人时，最后剩下的小朋友的编号
        int person_i = 0;//1.即代表f[1]，其值就为0
        for(int i = 2; i <= n; i++){
            //2.使用遍历，从“两个人”开始求，一直通过迭代的方式求到“n个人”
            person_i = (person_i + m) % i;//该式由上面的分析可知
        }
        return person_i;//3.最终，返回结果即可（此时i == n）
    }
}


/**
 *方法3：经典解法：即使用环形链表模拟圆圈的经典解法（了解即可）
 */
class Solution61_3 {

    public int LastRemaining_Solution(int n, int m) {

        if (n <= 0 || m <= 0) {
            return -1;
        }

        ListNode head = new ListNode(0);
        ListNode node = head;
        for (int i = 1; i < n; i++) {
            node.next = new ListNode(i);
            node = node.next;
        }
        node.next = head;

        int k = 0;
        while (node.next != node) {
            if (++k == m) {
                node.next = node.next.next;
                k = 0;
            } else {
                node = node.next;
            }
        }

        return node.val;
    }

}