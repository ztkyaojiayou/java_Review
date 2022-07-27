package 华为od机考练习.常规题库;

import java.util.Scanner;

/**
 * @author :zoutongkun
 * @date :2022/7/25 2:50 下午
 * @description :
 * @modyified By:
 */
public class Main52 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String str1 = sc.nextLine();
        String str2 = sc.nextLine();
        System.out.println(minDistance(str1,str2));
    }

    public static int minDistance(String word1, String word2) {
        //1.先获取两个字符串的长度，用于创建新数组
        int len1 = word1.length();
        int len2 = word2.length();
        //2.再创建一个二维数组，其大小设置为“在各自字符串的长度上加1”，目的是在各字符串前面加入一个空格，用于表示第一行和第一列的情况（即边界情况）
        //其中，dp[i][j]的含义是：把word1 的前 i 个字符转换成 word2 的前 j 个字符所需要的最少步数。（关键，务必理解）
        //这里的下标 i 包括 word[i]，同理下标 j 包括 word[j]，
        int[][] dp = new int[len1 + 1][len2 + 1];
        //3.确定初始/边界情况（即第一行和第一列的情况）:从一个字符串变成空字符串，非空字符串的长度就是编辑距离
        //3.1第一行：是 word1 为空变成 word2  的前 j 个字符所需要的最少步数，
        //易知，此时即为从无到有，直接不断地插入即可
        for (int j = 0; j < len2+1; j++) {
            dp[0][j] = j;
        }
        //3.2第一列：是 word2 为空，即把word1 的前i个字符变成空字符串所需要的最少步数，
        //易知直接不断地删除即可
        for (int i = 0; i < len1+1; i++) {
            dp[i][0] = i;
        }

        //4.再考虑一般情况，使用状态方程转化为初始情况即可，也是先j后i
        for (int j = 1; j < len2+1; j++) {
            for (int i = 1; i < len1+1; i++) {
                //4.1这里要考虑一种特殊情况，即当word1[i] == word2[j]时，此时只需要把word1的前i-1个字符串替换成word2的前j-1个字符串即可
                //这里的下标 i 不包括 word[i]，同理下标 j 不包括 word[j]，由于我们加了一个空字符，也即行数和列数多设置一行、一列，
                //也就是状态方程中的下标 i 和 j 和字符的下标 i、j 有一个位置的偏差。因此下面这里写的是word1.charAt(i - 1) == word2.charAt(j - 1)
                if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];//表示替换操作
                }
                else {//4.2对于其他情况，则都可以转化为：通过各种插入，删除，替换操作之后，选出能取到最小值的方案，最后再执行一次插入或删除或替换操作即可
                    //dp[i][j - 1]：表示插入操作；
                    //dp[i - 1][j]：表示删除操作
                    dp[i][j] = Math.min(Math.min(dp[i - 1][j - 1], dp[i][j - 1]), dp[i - 1][j]) + 1;
                }
            }
        }
        return dp[len1][len2];//把字符串的长度传入即可得到结果
    }
}
