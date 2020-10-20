package 数据结构与算法.第三遍;

import java.util.HashSet;
import java.util.List;

public class demo90_动态规划之单词能否被拆分 {
    public boolean wordBreak(String str, List<String> wordDict) {
        //先把字典表存入set中
        HashSet<String> set = new HashSet<>(wordDict);
        int len = str.length();
        //dp[i]就表示字符串s中的前i个字符能否拆分成字典表wordDict中的字符
        boolean[] dp = new boolean[len + 1];
        //初始化
        dp[0] = true;
        //一般情况
        for (int i = 1;i<len;i++){
            for (int j = 0;j<i;j++){
                if (dp[j] && set.contains(str.substring(j,i))){//把前i个字符分成两块，分别判断既可
                    dp[i] = true;
                    break;//对于当前字符，找到就退出
                }
            }
        }
        return dp[len];
    }
}
