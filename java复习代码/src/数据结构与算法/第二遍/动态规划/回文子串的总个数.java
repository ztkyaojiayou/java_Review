package 数据结构与算法.第二遍.动态规划;

public class 回文子串的总个数 {
    public int CountSubString(String str){
        //0.特判
        if (str == null || str.length() == 0){
            return 0;
        }
        //1.1结果集，至少有len个回文串，因为每个单独的字符肯定是回文串
        int res_len = str.length();
        //1.2把字符串转化为字符数组，便于处理
        char[] arr = str.toCharArray();
        //2.定义dp数组，dp[i][j] 表示从i到j所构成的字符串是否为回文子串的状态，默认为false
        boolean[][] dp = new boolean[res_len][res_len];
        //3.确定初始值,单个字符必为回文串
        for (int i=0;i<res_len;i++){
            dp[i][i] = true;
        }
        //4.处理一般情况
        for (int j = 1;j<res_len;j++){
            for (int i=0;i<j;i++){
                if (arr[j] == arr[i]){//4.1第5题是用的"不等于"，思路是一模一样的
                    if (j-i == 1){
                        dp[i][j] = true;
                    }else {//4.2否则，使用状态方程
                        dp[i][j] = dp[i+1][j-1];
                    }
                }
                //4.3只要找到一个新的回文串，就更新一下结果集res
                if (dp[i][j]){
                    res_len++;
                }
            }
        }
        //6.最后，返回结果集即可
        return res_len;
    }
}
