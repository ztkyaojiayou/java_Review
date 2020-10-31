package 数据结构与算法.第三遍;

/**
 *  * 461. 汉明距离
 *  * 两个整数之间的汉明距离指的是这两个数字对应二进制位不同的位置的数目。
 *  * 给出两个整数 x 和 y，计算它们之间的汉明距离。
 */
public class demo104_位运算之汉明距离 {
    public int hammingDistance(int x, int y) {
        int num = x ^ y;//先亦或，于是问题就转化为了统计该数的二进制形式中1的个数（关键）
        int res_count = 0;
        while (num != 0){
            if ((num & 1) == 1){//再将其和1做与运算，验证num的末位是否为1
              res_count++;
            }
            num = num >> 1;//右移一位，判断下一位是否为1
        }
        return res_count;
    }
}
