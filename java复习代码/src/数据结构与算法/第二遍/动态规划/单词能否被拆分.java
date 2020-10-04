package 数据结构与算法.第二遍.动态规划;

import java.util.HashSet;
import java.util.List;

public class 单词能否被拆分 {
public boolean wordBreak(String str, List<String> wordDict){
    //1.定义dp数组,dp[i]就表示字符串str中的前i个字符能否拆分成字典表wordDict中的字符
    int len = str.length();
    boolean[] dp = new boolean[len + 1];
    //2.确定初始值
    dp[0] = true;//字典表中默认存在空字符串
    //3.处理一般情况
    //3.1先把字典表存入一个set中，用于后面的比对
    HashSet<String> temp_set = new HashSet<>(wordDict);//通过该构造函数传入的list会被拆成一个一个的元素存入set中
    //3.2开始处理
    for (int i=1;i<len;i++){
        for (int j = 0;j<i;j++){//通过下标j把每一个子字符串又拆分成两个子字符串（即前j个字符构成的字符串s1和由j至i所构成的字符串s2）
            if (dp[j] && temp_set.contains(str.substring(j,i))){ //不断地利用j来切分该字符串，只要存在一个切分点使得这两个被切分的子字符串在字典表中就将其设为true
                // 若这两个子字符串都在字典表中，则让dp[i]为true，否则令其为false。
                dp[i] = true;
                break;//对于当前的i，只要找到一个符合要求的值即可终止后面的查找，直接跳到下一个i即可
            }
        }
    }
    //4.最后，返回结果即可
    return dp[len];
}
}
