package 华为od机考练习.常规题库;

import java.util.Scanner;

/**
 * 59)找出字符串中第一个只出现一次的字符--剑指offer原题
 *
 * @author :zoutongkun
 * @date :2022/7/25 3:55 下午
 * @description :
 * @modyified By:
 */
public class Main59找出字符串中第一个只出现一次的字符 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String str = sc.nextLine();
        boolean isFind = false;
        //用数组代替map即可
        //若使用str.charAt(i) - 'a'（其结果是一个int型）这种写法，
        //则这个数组只需开26个长度就行，因为字母最多也就26个
        int[] map = new int[26];
//        //而若不转到字母表这个区间，则需要开256个长度
//        int[] map1 = new int[256];
        for (int i = 0; i < str.length(); i++) {
            //减a的目的是把当前字符/字母转换成在字母表中的位置/下标/索引--涉及ASC码
            map[str.charAt(i) - 'a']++;
//            //也可以不减a的--亲测都通过
//            map1[str.charAt(i)]++;
        }

        //再遍历一遍原字符串，查找频次为1的字符
        for (int j = 0; j < str.length(); j++) {
            //即有很多字母只出现了一次，但只找第一个，因此只要找到了就返回即可
            if (map[str.charAt(j) - 'a'] == 1) {
                System.out.println(str.charAt(j));
                isFind = true;
                break;
            }
        }
        if (!isFind) {
            System.out.println(-1);
        }

    }
}

