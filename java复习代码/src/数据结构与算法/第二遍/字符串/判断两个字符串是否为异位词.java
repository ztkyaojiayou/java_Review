package 数据结构与算法.第二遍.字符串;


import java.util.*;

public class 判断两个字符串是否为异位词 {
    //写法1：
    public boolean isAnagram01(String s,String t){
        if (s.length() != t.length()){
            return false;
        }
        char[] str1 = s.toCharArray();
        char[] str2 = t.toCharArray();
        Arrays.sort(str1);
        Arrays.sort(str2);
       if (str1 == str2){
           return true;
       }
       return false;
    }

    //写法2:
    public boolean isAnagram02(String s,String t){
        if (s.length() != t.length()){
            return false;
        }
        int[] map = new int[26];
        for (int i = 0;i<s.length();i++){
            map[s.charAt(i) - 'a'] ++;
            map[t.charAt(i) - 'a'] --;
        }
        for (int j = 0;j<map.length;j++){
            if (map[j] != 0){
                return false;
            }
        }
        return true;
    }
}

class 字母异位词分组 {
    public List<List<String>> groupAnagram(String[] strs) {
        Map<String, List<String>> map = new HashMap<>();

        //2.再遍历字符串数组，将每一个字符串变为字符数组，并排序，之后再把其变回字符串
        for (String str : strs) {
            char[] s = str.toCharArray();
            Arrays.sort(s);
            String sorted_str = new String(s);
            if (map.containsKey(sorted_str)) {
                List<String> list = map.get(sorted_str);
                list.add(str);
                map.put(sorted_str, list);
            } else {
                ArrayList<String> list = new ArrayList<>();
                list.add(str);
                map.put(sorted_str, list);
            }
        }
        return new ArrayList<>(map.values());
    }
}
