package 数据结构与算法.剑指offer题解.位运算;

import java.util.ArrayList;

/**
 * 题目：一个整型数组里除了两个数字之外，其他的数字都出现了两次，请写程序找出这两个只出现一次的数字。
 * 要求时间复杂度是 O(n)，空间复杂度是 O(1)。
 * 例如输入数组｛5，2, 4, 3, 6, 3, 2, 5 }，
 *     因为只有 4 、6 这两个数字只出现一次，其他数字都出现了两次，
 *     所以输出 4 和 6 。
 *
 * 【解】：
 * 方法一：使用arraylist
 *  这个可以使用ArrayList来解决，代码比较简洁。
 *  首先判断ArrayList中是否已存在，如果存在，则删除。
 *  删除时需要注意一下，如果直接传入当前数作为参数，它会按照下标进行删除，不会按照对象进行删除，可能会出现越界。
 *  所以需要new Integer()。
 *
 * 方法二：位运算法（没太明白）
 * 两个不相等的元素在位级表示上必定会有一位存在不同，将数组的所有元素异或得到的结果为不存在重复的两个元素异或的结果。
 * diff &= -diff 得到出 diff 最右侧不为 0 的位，也就是不存在重复的两个元素在位级表示上最右侧不同的那一位，利用这一位就可以将两个元素区分开来。
 *  1. 假如数组中只有1个数字只出现过一次，其他的都出现了两次，怎么找到它？
 *      我们可以用异或，因为异或：相同为0，不同为1
 *      所以我们可以从头到尾依次异或数组中的每一个数字，最终的异或结果刚好是只出现一次的那个数字。（那些成对出现的两个数字刚好在异或中抵消了）
 *  2. 那数组中又两个只出现一次的数字呢？
 *      我们可以把大数组分成两个子数组啊，每个子数组中都只有1个只出现一次的数字。
 *      这样分：
 *          - 还是从头到位依次异或数组中的每一个数字，最终得到的结果 也是这两个只出现一次的数字异或的结果。
 *          - 假设最终得到的异或结果为 0010，也就是说： 这两个数字的二进制表示时的第3位，一定是一个为1，一个为0
 *          - 因此 我们可以将数组中的所有数字按第3位是1的分成一组，第3位是0的分成一组。
 *          - 然后，再分别求出两个子数组中的那个只出现过一次的数字吧。
 *
 */
public class BitOperation55数组中只出现一次的两个数字 {
    /**
     * 方法一：利用ArrayList
     * 关于remove方法：
     * public E remove(int index)
     * 移除此列表中指定位置上的元素。向左移动所有后续元素（将其索引减 1），这一点很重要。
     * @param array
     * @param num1
     * @param num2
     */
    public void FindNumsAppearOnce01(int[] array, int num1[], int num2[]) {
        if (array.length < 2) {
            return;
        }
        //1.先创建一个ArrayList，用于存储
        ArrayList<Integer> list = new ArrayList();
        for (int i = 0; i < array.length; i++) {//遍历目标数组
            //2.判断数组中的值在ArrayList中是否已存在（即是否已经存入），使用contains方法
            //2.1若存在，则删除
            //易知，这个元素并没有添加到list中，而是当发现有相同元素时，自己并不加入list中，而且还把已经在list当中的那个相同元素删除。
            if (list.contains(array[i])) {
                //注意：若直接传入当前数作为参数，它会按照下标/索引进行删除，而不会按照对象进行删除，则有可能会出现越界。
                //所以需要new Integer()，把其变为Integer类型的对象。
                list.remove(new Integer(array[i]));
            } else {
                //2.2否则，添加进去
                list.add(array[i]);
            }
        }
        //3.再获取剩下的元素，返回即可，即为只出现一次的元素
        //删除完所有重复元素之后，list中只剩下那两个不重复的数了
        num1[0] = list.get(0);
        num2[0] = list.get(1);
    }

    /**
     * 方法二：利用位运算（主推方法，但没太懂）
     * @param array
     * @param num1
     * @param num2
     */
    public void FindNumsAppearOnce02(int [] array,int num1[] , int num2[]) {

        int xor1 = 0;
        for(int i=0; i < array.length; i++)
            xor1 = xor1^array[i];
        //在xor1中找到第一个不同的位对数据进行分类，分类为两个队列对数据进行异或求和找到我们想要的结果
        int index = 1;
        while((index & xor1)==0)
            index = index <<1;//因为可能有多个位为1所以需要求一下位置
        int result1 = 0;
        int result2 = 0;
        for(int i=0; i < array.length; i++){
            if((index & array[i]) == 0)
                result1 = result1^array[i];
            else
                result2 = result2^array[i];
        }
        num1[0] = result1;
        num2[0] = result2;
    }
}
