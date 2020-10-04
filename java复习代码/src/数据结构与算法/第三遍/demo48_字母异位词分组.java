package 数据结构与算法.第三遍;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class demo48_字母异位词分组 {
    public List<List<String>> groupAnagram(String[] strs) {
        HashMap<String, List<String>> map = new HashMap<>();
        for (int i = 0;i<strs.length;i++){
            char[] chars = strs[i].toCharArray();
            Arrays.sort(chars);
            String sorted_str = new String(chars);
            if (map.containsKey(sorted_str)){//若有，则更新该字符串对应的异位词组（先取旧的，再把新的加进去，再存入map中）
                List<String> old_list = map.get(sorted_str);
                old_list.add(strs[i]);
                map.put(sorted_str,old_list);
            }else {
                ArrayList<String> list = new ArrayList<>();
                list.add(strs[i]);
                map.put(sorted_str,list);
            }
        }
        return new ArrayList<>(map.values());
    }
}
