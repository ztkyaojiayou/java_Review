package 数据结构与算法.剑指offer题解.位运算;

import java.util.BitSet;

/**
 * 题目：在字符串中找出第一个只出现一次的字符。
 * 例如输入"abaccdeff"，则输出'b'。
 *
 * 【解】：
 * 最直观的解法是使用 HashMap 对出现次数进行统计，然后再扫描一次字符数组，当此字符出现次数为1时，直接return
 * 但是考虑到要统计的字符范围有限，
 * 因此可以使用整型数组【int[256]】代替 HashMap，从而将空间复杂度由 O(N) 降低为 O(1)。
 */
public class BitOperation49第一个只出现一次的字符 {
    //方法一：利用整形数组，但还是使用HashMap的思想（也是主推方法）
    public int FirstNotRepeatingChar(String str) {
        //1.先创建一个整形数组，然后遍历整个字符串一遍，记录下每个字符出现的次数
        int[] cnts = new int[256];
        for (int i = 0; i < str.length(); i++)
            //这就是具体做法：把值当索引
            //即把字符串的每一个索引处的值当做我们自定义的整形数组的索引，
            //每来一个重复的值，就在数组中把该索引（就是这个值）的值加1，即相当于统计了字符串中每个值的个数
            cnts[str.charAt(i)]++;
        //2.再再遍历一次这个数组，把只出现过一次的值的索引直接返回，即为它的位置
        for (int i = 0; i < str.length(); i++)
            if (cnts[str.charAt(i)] == 1)
                return i;
        //3.否则返回-1，说明没有找到
        return -1;
    }


    //（不懂的就不看，面向面试编程，解决问题最重要）
    //方法二：利用位运算（没太懂）
    //以上实现的空间复杂度还不是最优的。
    //考虑到只需要找到只出现一次的字符，那么需要统计的次数信息只有 0,1,更大，
    // 使用两个比特位就能存储这些信息。
    public int FirstNotRepeatingChar2(String str) {
        BitSet bs1 = new BitSet(256);//若该字符存在时，则为1，否则为0
        BitSet bs2 = new BitSet(256);
        for (char c : str.toCharArray()) {//先把字符串转化为字符串数组，再遍历
            if (!bs1.get(c) && !bs2.get(c))//若bs1和bs2中都没有该字符，则把该字符存入bs1，
                bs1.set(c);     // 0 0 -> 0 1
            else if (bs1.get(c) && !bs2.get(c))//若bs1中有该字符，而bs2中没有该字符，则把该字符存入bs2，
                bs2.set(c);     // 0 1 -> 1 1
        }
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (bs1.get(c) && !bs2.get(c))  // 0 1
                return i;
        }
        return -1;
    }

}
