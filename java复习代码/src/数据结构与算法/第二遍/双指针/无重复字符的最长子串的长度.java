package 数据结构与算法.第二遍.双指针;

import java.util.HashMap;

public class 无重复字符的最长子串的长度 {
    public int lengthSubString(String str){
        int res = 0;
        //定义一个map作为字典表
        HashMap<Character, Integer> map = new HashMap<>();
        //定义双指针
        for (int left = 0,right = 0;right<str.length();right++){
            if (map.containsKey(str.charAt(right))){
                left = Math.max(left,map.get(str.charAt(right))+1);
            }
            map.put(str.charAt(right),right);
            res = Math.max(res,right-left+1);
        }
        return res;
    }
}
