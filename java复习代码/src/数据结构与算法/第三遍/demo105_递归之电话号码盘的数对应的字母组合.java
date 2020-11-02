package 数据结构与算法.第三遍;

import java.util.ArrayList;
import java.util.List;

public class demo105_递归之电话号码盘的数对应的字母组合 {
    //先建立一个号码盘的映射表，第二个位置/下标是"abc“,第三个位置/下标是"def"。。。
    String[] letter_map = {" ", "*", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
    //最终输出结果的list
    List<String> res = new ArrayList<>();

    public List<String> letterCombinations(String str_nums) {//输入的是由数字组成的字符串
        //直接递归
        method(str_nums, "", 0);
        return res;
    }

    //递归方法
    private void method(String str, String path, int start) {
        //递归出口
        //即若遍历完了所有目标字符串，则表示已经找到了全部的组合，直接添加进res即可
        //（注意：这里没有再分成一个一个的小list了）
        if (start == str.length()) {
            res.add(path);
            return;
        }
        //获取到当前位置的数字在号码盘中对应的字符串（如2对应abc）
        char cur_char = str.charAt(start);//获得当前字符（易知其实是一个数字）
        int cur_index = cur_char - '0';//再把这个数字字符变成真正的数字
        String map_str = letter_map[cur_index];//再获取到其对应的字符串
        for (int i = 0; i < map_str.length(); i++) {//再从第一个字母，开始递归地和下一个字符串进行拼接即可
            path += map_str.charAt(i);//拼接当前字符
            method(str, path, start + 1);//递归地拼接下一个字符串中的每一个字符
        }
    }
}
