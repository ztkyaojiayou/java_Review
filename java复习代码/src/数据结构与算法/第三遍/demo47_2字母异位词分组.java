package 数据结构与算法.第三遍;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class demo47_2字母异位词分组 {
    public List<List<String>> groupAnagram(String[] strs) {
        // 对于map，最重要的是要用它存啥！
        // 这里存的就是：每一个异位词排序后的结果（key）以及与之对应的所有异位词list（value）
        HashMap<String, List<String>> map = new HashMap<>();
        for (int i = 0;i<strs.length;i++){
            char[] chars = strs[i].toCharArray();
            Arrays.sort(chars);//把每一个字符串都先转化为排序后的字符串，目的是把当前字符串放到对应的value中
            String sorted_str = new String(chars);

            if (map.containsKey(sorted_str)){//若有，则更新该字符串对应的异位词组（先取旧的，再把新的加进去，再存入map中）
                List<String> old_list = map.get(sorted_str);
                old_list.add(strs[i]);
                map.put(sorted_str,old_list);
            }else {//否则，新建一个list（当做value）
                ArrayList<String> list = new ArrayList<>();
                list.add(strs[i]);
                map.put(sorted_str,list);
            }
        }
        //返回结果
        return new ArrayList<>(map.values());
    }
}
