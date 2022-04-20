package 数据结构与算法.离职后刷题.第四遍必会版;

import java.util.ArrayList;
import java.util.List;

//要注意的是：一个按键只提供一个字母进行组合，而并不是所有可能的组合！！！
public class demo105_递归之电话号码盘的数对应的字母组合 {
    //先建立一个号码盘的映射表，第二个位置/下标是"abc“,第三个位置/下标是"def"。。。
    String[] letter_map = {" ", "*", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
    //最终输出结果的list
    List<String> res = new ArrayList<>();

    //输入的是由数字组成的字符串（每一位数字对应一个字符串呀）
    public List<String> letterCombinations(String str_nums) {
        //直接递归
        method(str_nums, "", 0);
        return res;
    }

    //递归方法
    private void method(String str, String curPath, int curIndex) {
        //递归出口
        //即若遍历完了所有目标字符串，则表示已经找到了全部的组合，直接添加进res即可
        //（注意：这里没有再分成一个一个的小list了）
        if (curIndex == str.length()) {
            res.add(curPath);
            return;
        }
        //获取到当前位置的数字在号码盘中对应的字符串（如2对应abc）
        //获得当前字符（易知其实是一个数字）
        char cur_char = str.charAt(curIndex);
        //再把这个数字字符变成真正的数字
        int curNum = cur_char - '0';
        //再获取到其对应的字符串
        String map_str = letter_map[curNum];
        //再从第一个字母，开始递归地和下一个字符串进行拼接即可
        for (int i = 0; i < map_str.length(); i++) {
            //拼接当前字符
            curPath += map_str.charAt(i);
            //固定第一个数字中的第一个字母，递归地拼接下一个字符串中的每一个字符，
            // 要注意的是吗，每次也是选一个字母，然后就结束啦，而并不是穷尽所有可能！！！
            method(str, curPath, curIndex + 1);
        }
    }


    //自写一遍
    //最终输出结果的list
    List<String> res02 = new ArrayList<>();

    //输入的是由数字组成的字符串（每一位数字对应一个字符串呀）
    public List<String> letterCombinations02(String str_nums) {
        //直接递归
        method02(str_nums, "", 0);
        return res02;
    }

    private void method02(String str_nums, String curPath, int curIndex) {
        //递归出口，若目标数字的每一位都使用了时就可以结束啦
        if (curIndex == str_nums.length()) {
            res02.add(curPath);
            return;
        }
        char curChar = str_nums.charAt(curIndex);
        int curNum = curChar - '0';
        String curNumStr = letter_map[curNum];
        for (int i = 0; i < curNumStr.length(); i++) {
            //拼接当前字符（即第一个数字对应的字符串的第一个字符）
            curPath += curNumStr.charAt(i);
            //递归拼接下一个数字对应的字符串
            method02(str_nums, curPath, curIndex + 1);
        }

    }
}
