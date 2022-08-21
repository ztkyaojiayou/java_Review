package 数据结构与算法.od机考.华为od机考练习.常规题库;

/**
 * 23）删除字符串中出现次数最少的字符
 * 描述
 * 实现删除字符串中出现次数最少的字符，若出现次数最少的字符有多个，则把出现次数最少的字符都删除。
 * 输出删除这些单词后的字符串，字符串中其它字符保持原来的顺序。
 *
 * @author :zoutongkun
 * @date :2022/7/23 8:13 下午
 * @description :
 * @modyified By:
 */

import java.util.*;

class Main23 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        //题目没说一次性输入多行/多个测试用例，
        //因此可以不用考虑加while，就当只输入了一行处理即可--亲测通过
//        while (sc.hasNextLine()) {
        String str = sc.nextLine();
        char[] charArr = str.toCharArray();
        // Map记录每个字母的次数
        Map<Character, Integer> map = new HashMap<>();
        for (char ch : charArr) {
            map.put(ch, map.getOrDefault(ch, 0) + 1);
        }
        // 快速找出最少次数
        int min = Integer.MAX_VALUE;
        for (int value : map.values()) {
            min = Math.min(min, value);
        }
        //再遍历原字符数组，丢弃出现次数最小的字符后输出
        StringBuilder sb = new StringBuilder();
        for (char ch : charArr) {
            if (map.get(ch) != min) {
                sb.append(ch);
            }
        }
        //不需要先转出String，println方法会默认转为String
        System.out.println(sb);
//        }
    }
}

/**
 * 自写一遍
 */
class Main230 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine();
        char[] charArr = s.toCharArray();
        HashMap<Character, Integer> map = new HashMap<>();
        for (char c : charArr) {
            map.put(c, map.getOrDefault(c, 0) + 1);
        }
        //找出最小次数
        int min = Integer.MAX_VALUE;
        for (Integer value : map.values()) {
            min = Math.min(min, value);
        }
        //再遍历并输出--丢掉出现次数最小的字符
        for (char c : charArr) {
            if (map.get(c) != min) {
                //注意：使用StringBuilder先拼接后一次性打印
                //和先使用print一个一个不换行全部打印后再使用println换行的效果是一样的~
                //亲测通过
                System.out.print(c);
            }
        }
        System.out.println();
    }
}
