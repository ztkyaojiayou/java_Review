package 数据结构与算法.第二遍.map;


/**
 * 题目：在字符串中找出第一个只出现一次的字符。
 * 例如输入"abaccdeff"，则输出'b'。
 *
 * 【解】：
 * 最直观的解法是使用 HashMap 对出现次数进行统计，然后再扫描一次字符数组，当此字符出现次数为1时，直接return
 * 但是考虑到要统计的字符范围有限，
 * 因此可以使用整型数组【int[256]】代替 HashMap，从而将空间复杂度由 O(N) 降低为 O(1)。
 */
public class 第一个只出现一次的字符 {
public int firstOf1(String str){
    int[] arr_count = new int[256];//使用一个数组计数，类似于map
    for (int i =0;i< str.length();i++){
        arr_count[str.charAt(i)]++;//即把i处的字符的ASC码的值作为下标存入数组中，其值设为1.
    }

    for (int i = 0;i< str.length();i++){
        if (arr_count[str.charAt(i)] == 1){
            return i;
        }
    }
    return -1;
}
}
