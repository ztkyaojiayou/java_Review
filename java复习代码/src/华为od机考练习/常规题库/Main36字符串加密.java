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
class Main36字符串加密 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String key = in.nextLine();
        String tarStr = in.nextLine();
        Set<Character> set = new TreeSet<>();
//   用set过滤重复的，list作为索引表--有序（非常巧妙）
        List<Character> list = new ArrayList<>(set);
        for (int i = 0; i < key.length(); i++) {
            //set保证唯一性，因此当能add进去时就表示该字符没重复，
            //则就顺道add进list，否则不需要add，也很巧妙
            if (set.add(key.charAt(i))) {
                list.add(key.charAt(i));
            }
        }
        //再将剩下的字母按照字母顺序add进list
        for (int i = 0; i < 26; i++) {
            //注意：这里的i+‘a’即为对应的ASC码计算，
            //因此还需要转为对应的字符--注意，都是小写（题意）
            if (set.add((char) (i + 'a'))) {
                list.add((char) (i + 'a'));
            }
        }
        //至此，list中存的26个字母及顺序就看成新的字母顺序索引表即可
        //再对要加密的字符串进行对应取值即可
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < tarStr.length(); i++) {
            //这里求得的就是当前字符在（新）字母顺序表中的索引呀
            int index = tarStr.charAt(i) - 'a';
            //取到对应的字母
            sb.append(list.get(index));
        }
        //最后打印即可
        System.out.println(sb);
    }
}

