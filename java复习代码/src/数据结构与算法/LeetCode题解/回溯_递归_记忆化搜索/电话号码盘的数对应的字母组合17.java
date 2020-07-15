package 数据结构与算法.LeetCode题解.回溯_递归_记忆化搜索;

import java.util.ArrayList;
import java.util.List;

/**
 * 17. 电话号码的字母组合
 * 给定一个仅包含数字 2-9 的字符串，返回所有它能表示的字母组合。
 * 给出数字到字母的映射与电话按键相同。注意 1 不对应任何字母。
 *
 * 示例:
 * 输入："23"
 * 输出：["ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"].
 * 说明: 尽管上面的答案是按字典序排列的，但是你可以任意选择答案输出的顺序。
 */
public class 电话号码盘的数对应的字母组合17 {
        //一个映射表，第二个位置是"abc“,第三个位置是"def"。。。
        //这里也可以用map，用数组可以更节省点内存
        //下标为数字，其值为对应的字母
        String[] letter_map = {" ","*","abc","def","ghi","jkl","mno","pqrs","tuv","wxyz"};
    //最终输出结果的list
    List<String> res = new ArrayList<>();

        public List<String> letterCombinations(String digits) {//输入的是由数字组成的字符串

            //注意边界条件
            if(digits==null || digits.length()==0) {
                return new ArrayList<>();//返回空
            }
            //开始调用递归函数，先从第一个数字开始，此时下标为0
            iterStr(digits, "", 0);
            return res;
        }

        //递归函数（核心）
    //str表示传入的数字字符串，letter表示已经被拼接的结果字符串，index表示传入的数字字符串的下标，从0开始，即第一个数字字符
        void iterStr(String str, String letter, int index) {//最开始，下标为0，即从第一个数字开始
            //递归的终止条件，注意这里的终止条件看上去跟动态演示图有些不同，主要是做了点优化
            //动态图中是每次截取字符串的一部分，"234"，变成"23"，再变成"3"，最后变成""，这样性能不佳
            //而用index记录每次遍历到字符串的位置，这样性能更好
            if(index == str.length()) {//此时相当于已经遍历到了该数字字符串的最后一个字符，不能再往下了，此时就可以结束递归啦
                //此时的letter刚好就是一个
                res.add(letter);
                return;
            }
            //获取index位置的字符，假设输入的字符是"234"
            //第一次递归时index为0所以c=2，第二次index为1所以c=3，第三次c=4
            //subString每次都会生成新的字符串，而index则是取当前的一个字符，所以效率更高一点
            char c = str.charAt(index);
            //map_string的下表是从0开始一直到9，c-'0'就可以取到相对的数组下标位置
            //比如c=2时候，2-'0'，获取下标为2,letter_map[2]就是"abc"
            int pos = c - '0';
            String map_string = letter_map[pos];//获取到数字在号码盘中对应的字符串
            //遍历字符串，比如第一次得到的是2，就是遍历"abc"
            for(int i=0;i<map_string.length();i++) {
                //调用下一层递归，遍历当前数字所对应的的所有字母并拼接，然后继续递归。
                iterStr(str, letter+map_string.charAt(i), index+1);
            }
        }
    }
