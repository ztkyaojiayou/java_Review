package 数据结构与算法.剑指offer题解.第二遍.双指针;

import java.util.HashMap;

public class 无重复字符的最长子串 {
    public int lengthSubString(String str){
        int res = 0;
        //定义一个map作为字典表
        HashMap<Character, Integer> map = new HashMap<>();
        //定义双指针
        for (int start = 0,end = 0;end<str.length();end++){
            if (map.containsKey(str.charAt(end))){
                start = Math.max(start,map.get(str.charAt(end))+1);
            }
            map.put(str.charAt(end),end);
            res = Math.max(res,end-start+1);
        }
        return res;
    }
}
