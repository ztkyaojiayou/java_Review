package 华为od机考练习.常规题库;

import java.util.*;

/**
 * 36）字符串加密
 *
 * @author :zoutongkun
 * @date :2022/7/25 9:35 上午
 * @description :
 * @modyified By:
 */
class Main36_自写 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String key = sc.nextLine();
        String tarStr = sc.nextLine();
        Set<Character> set = new HashSet<>();
        List<Character> list = new ArrayList<>();
        //构建list
        for (int i = 0; i < key.length(); i++) {
            //边添加边判断--妙
            if (set.add(key.charAt(i))) {
                list.add(key.charAt(i));
            }
        }
        //再把剩余的字母加入list
        for (int j = 0; j < 26; j++) {
            if (!set.contains((char) (j + 'a'))) {
                list.add((char) (j + 'a'));
            }
        }

        //再加密
        StringBuilder sb = new StringBuilder();
        for (int k = 0; k < tarStr.length(); k++) {
            int index = tarStr.charAt(k) - 'a';
            sb.append(list.get(index));
        }
        System.out.println(sb);
    }
}

