package 华为od机考练习.常规题库;

import java.util.Scanner;

/**
 * 65）查找两个字符串a,b中的最长公共子串--求该子串
 *
 * @author :zoutongkun
 * @date :2022/7/25 5:09 下午
 * @description :
 * @modyified By:
 */
class Main65 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            String s1 = sc.nextLine();
            String s2 = sc.nextLine();
            System.out.println(longestCommonString(s1, s2));
        }
    }

    public static String longestCommonString(String str1, String str2) {
        int len1 = str1.length();
        int len2 = str2.length();

        // 保证str1是较短字符串
        String temp = "";
        if (len1 > len2) {
            temp = str1;
            str1 = str2;
            str2 = temp;
        }
        //结果集
        int maxLen = 0;
        int endIndex = 0;
        //定义dp数组，dp[i][j]表示较短字符串s1以i结尾和s2以j结尾的字符串所构成的最长公共子串的长度
        int[][] dp = new int[len1 + 1][len2 + 1];

        //一般情况
        for (int i = 1; i < len1 + 1; i++) {
            for (int j = 1; j < len2 + 1; j++) {
                //若相等，就在原来的基础上加1
                if (str1.charAt(i - 1) == str2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                    //当有公共字符出现，且公共子串长度大于原子串长度时，就立马更新
                    if (dp[i][j] > maxLen) {
                        //此时记录该公共子串的长度以及该子串在较短字符串中的结尾下标
                        maxLen = dp[i][j];
                        endIndex = i;
                    }
                }
            }
        }
        return str1.substring(endIndex - maxLen, endIndex);
    }
}

/**
 * 75）公共子串计算--求该子串的长度（力扣原题，常考）
 *
 * @author :zoutongkun
 * @date :2022/7/25 5:09 下午
 * @description :
 * @modyified By:
 */
class Main75 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            String s1 = sc.nextLine();
            String s2 = sc.nextLine();
            System.out.println(longestCommonStringLen(s1, s2));
        }
    }

    public static int longestCommonStringLen(String s1, String s2) {
        int len1 = s1.length();
        int len2 = s2.length();
        //结果集
        int res = 0;
        //定义dp数组，dp[i][j]表示s1以i结尾和s2以j结尾的字符串所构成的最长公共子串的长度
        int[][] dp = new int[len1 + 1][len2 + 1];
//
//        //确定初始值，其实可以不写，因为数组默认就全为0
//        //空字符串与有长度的字符串的最长公共子串的长度肯定为0呀。
//        for (int j = 0; j < len2; j++) {
//            dp[0][j] = 0;
//        }
//        for (int i = 0; i < len1; i++) {
//            dp[i][0] = 0;
//        }

        //一般情况
        //注意：这里的长度也是len+1啦！！！
        for (int i = 1; i < len1 + 1; i++) {
            for (int j = 1; j < len2 + 1; j++) {
                //若相等，就在原来的基础上加1
                if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {//否则置零，因为子串需要连续
                    dp[i][j] = 0;
                }
                //更新结果，只要求出一个dp，就要赶紧更新一下呀（老套路啦）
                res = Math.max(res, dp[i][j]);
            }
        }
        return res;
    }
}

