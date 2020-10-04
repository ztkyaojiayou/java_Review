package 数据结构与算法.第三遍;

import java.util.HashMap;

public class demo14_无重复字符的最长子串的长度 {
    public int lengthSubString(String str){
        int res = 0;
        HashMap<Character, Integer> map = new HashMap<>();//存放当前字符和对应的下标
        for (int left = 0,right = 0;right < str.length();right++){
            if (map.containsKey(str.charAt(right))){//若存在，则把左下标移至该下标的下一个位置
                left = Math.max(left,map.get(str.charAt(right)) + 1);
            }
            map.put(str.charAt(right),right);
            res = Math.max(res,right-left+1);
        }
        return res;
    }
}
