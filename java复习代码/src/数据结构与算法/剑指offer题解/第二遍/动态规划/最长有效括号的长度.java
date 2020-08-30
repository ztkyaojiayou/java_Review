package 数据结构与算法.剑指offer题解.第二遍.动态规划;

//非常标准的动态规划写法
public class 最长有效括号的长度 {
    public int longestKuoHao(String str){
        //1.结果集
        int res = 0;
        int len = str.length();
        //2.定义dp数组，dp[i]表示字符串前i个字符的最长有效括号
        int[] dp = new int[len];
        //3.确定初始值
        dp[0] = 0;
        dp[1] = 0;
        //4.处理一般情况
        for (int i=2;i<len;i++){
            if (str.charAt(i) == ')'){//这是大前提，即最后一个括号必须为右括号
                if (str.charAt(i-1) == '('){
                    dp[i] = dp[i-2] + 2;
                }else if (str.charAt(str.charAt(i-dp[i-1]-1)) == '('){
                    dp[i] = dp[i-1] + dp[i-dp[i-1]-1-1]+2;
                }
                //每找到一对新的有效括号，就更新结果集res
                res = Math.max(res,dp[i]);
            }
        }
        //5.最后，返回结果即可
return res;
    }

}
