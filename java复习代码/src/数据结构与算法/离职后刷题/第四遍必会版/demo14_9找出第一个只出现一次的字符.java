package 数据结构与算法.离职后刷题.第四遍必会版;

public class demo14_9找出第一个只出现一次的字符 {
    public int firstOf1(String str) {
        //用数组代替map即可
        int[] map = new int[256];
        for (int i = 0; i < str.length(); i++) {
            //减a的目的是把当前字符变成数字（涉及ASC码）
            map[str.charAt(i) - 'a']++;
        }
        for (int i = 0; i < map.length; i++) {
            //即有很多字母只出现了一次，但只找第一个，因此只要找到了就返回即可
            if (map[str.charAt(i)] == 1) {
                return i;
            }
        }
        return -1;
    }
}
