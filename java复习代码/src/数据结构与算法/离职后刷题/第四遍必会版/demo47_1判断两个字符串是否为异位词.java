package 数据结构与算法.离职后刷题.第四遍必会版;

/**
 * 解题思路：使用hashMap即可，因为二者只是异位词，
 * 但他们的各个字母出现的频率肯定是一样的，抓住这一点即可
 * 两字符串长度不等则一定不是异位词
 * s，t都是小写字母时，可建一个大小为26的int数组map用力存每个小写字母出现的次数，初始值都为0
 * 1)从下标0开始遍历s和t
 * 2)每次循环，取出s中第i个元素，将其ASCII码减去字母a对应的ASCII码，即可知道当前字符在数组中的位置，然后将该位置对应的字符出现次数+1
 * 3)然后取出t中第i个元素，将其ASCII码减去字母a对应的ASCII码，即可知道当前字符在数组中的位置，然后将该位置对应的字符出现次数-1
 * 4)循环结束后，遍历数组map，如果map中存在个数不为0的字符，则说明不是字母异位词，否则就是字母异位词
 */
public class demo47_1判断两个字符串是否为异位词 {
    public boolean isAnagram01(String s, String t) {
        int[] map = new int[26];
        for (int i = 0; i < s.length(); i++) {
            //1.一加一减即可
            map[s.charAt(i) - 'a']++;
            map[t.charAt(i) - 'a']--;
        }

        //2.再排查map是否为空
        for (int i = 0; i < map.length; i++) {
            if (map[i] != 0) {
                return false;
            }
        }

        return true;
    }


    //再写一遍
    public boolean isAnagram02(String s, String t) {
        int[] map = new int[26];
        for (int i = 0; i < s.length(); i++) {
            map[s.charAt(i) - 'a']++;
            map[t.charAt(i) - 'a']--;
        }
        for (int i = 0; i < map.length; i++) {
            if (map[i] != 0) {
                return false;
            }
        }
        return true;
    }
}
