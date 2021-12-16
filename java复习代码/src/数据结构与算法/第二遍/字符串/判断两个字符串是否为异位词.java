package 数据结构与算法.第二遍.字符串;


import com.sun.org.apache.bcel.internal.generic.NEW;

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

    /**
     * 第三遍--tk.zou
     * @param s
     * @param t
     * @return
     */
    public boolean test03(String s,String t){
        if (s.length() != t.length()){
            return false;
        }
        //一加一减，最终每个元素都应当为0
        int[] arr = new int[26];
        for (int i = 0; i < s.length(); i++) {
            arr[s.charAt(i) - 'a']++;
            arr[t.charAt(i) - 'a']--;
        }
        //验证每个元素是否为0
        for (int i : arr) {
            if (i != 0){
                return false;
            }
        }
        return true;
    }
}


class 字母异位词分组 {
    public List<List<String>> groupAnagram(String[] strs) {
        //1.结果集
        Map<String, List<String>> map = new HashMap<>(strs.length);

        //2.再遍历字符串数组，将每一个字符串变为字符数组，并排序，作为分组的key
        for (String str : strs) {
            char[] s = str.toCharArray();
            Arrays.sort(s);
            String sorted_str = new String(s);
            if (map.containsKey(sorted_str)) {
                List<String> list = map.get(sorted_str);
                list.add(str);
                map.put(sorted_str, list);
            } else {
                List<String> list = new ArrayList<>();
                list.add(str);
                map.put(sorted_str, list);
            }
        }
        return new ArrayList<>(map.values());
    }

    /**
     * 第三遍--tk.zou
     * @param strs
     * @return
     */
    public List<List<String>> test03(String[] strs) {
        //结果集
        HashMap<String, List<String>> map = new HashMap<>(strs.length);
        for (String str : strs) {
            char[] chars = str.toCharArray();
            Arrays.sort(chars);
            String sorted_str = new String(chars);
            //往map里放
            //先判断是否已经有
            if (map.containsKey(sorted_str)){
                List<String> list = map.get(sorted_str);
                list.add(str);
                map.put(sorted_str,list);
            }
            List<String> new_list = new ArrayList<>();
            new_list.add(str);
            map.put(sorted_str,new_list);
        }
        //再把map转化为list--使用构造方法
        return new ArrayList<>(map.values());
    }
}
