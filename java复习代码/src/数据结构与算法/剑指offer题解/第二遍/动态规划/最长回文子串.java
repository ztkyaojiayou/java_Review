package 数据结构与算法.剑指offer题解.第二遍.动态规划;

public class 最长回文子串 {
    public String longestSubString(String str){
        //0.特判
        int len = str.length();
        if (len < 2){
            return str;
        }
        //用于记录回文串的位置
        int maxLen = 1;
        int begin = 0;
        //把字符串转化为字符数组，方便处理
        char[] arr = str.toCharArray();
        //1.定义dp数组，dp[i][j] 表示 s[i, j] 是否是回文串
        boolean[][] dp = new boolean[len][len];
        //2.确定初始值
        for (int i=0;i<len;i++){
            dp[i][i] = true;
        }
        //3.再处理一般情况
        for (int j = 1;j<len;j++){//使用遍历，即以j结尾，依次判断前面的字符串是否为回文子串，
            // 如：若j = 5，则判读（0,5），（1,5）...(4,5)的情况
            for (int i=0;i<j;i++){
              if (arr[j] != arr[i]){//3.1只有两头的字符相等时才有可能是回文串，这是前提
                  dp[i][j] = false;
              }else {
                  if (j -i < 3){//3.2此时该区间就只有两个字符了，可以直接下结论了
                      dp[i][j] = true;
                  }else {//3.3否则，就用状态方程进行转移
                      dp[i][j] = dp[i+1][j-1];
                  }
              }
              //3.4只要以一个新的回文串诞生，就记录该回文串的长度
              if (dp[i][j] && j-i+1 > maxLen){
                  maxLen = j-i+1;
                  begin = i;
              }
            }

        }
        //4.当全部判断完毕之后，返回该回文串即可
        return str.substring(begin,begin+maxLen);
    }
}
