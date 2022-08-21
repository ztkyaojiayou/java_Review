package 数据结构与算法.od机考.华为od机考练习.常规题库;

import java.util.*;

/**
 * 36）字符串加密
 * 有一种技巧可以对数据进行加密，它使用一个单词作为它的密匙。
 * 下面是它的工作原理：首先，选择一个单词作为密匙，如TRAILBLAZERS。
 * 如果单词中包含有重复的字母，只保留第1个，将所得结果作为新字母表开头，
 * 并将新建立的字母表中未出现的字母按照正常字母表顺序加入新字母表。如下所示：
 * A B C D E F G H I J K L M N O P Q R S T U V W X Y Z
 * <p>
 * T R A I L B Z E S C D F G H J K M N O P Q U V W X Y (实际需建立小写字母的字母表，此字母表仅为方便演示）
 * <p>
 * 上面其他用字母表中剩余的字母填充完整。在对信息进行加密时，信息中的每个字母被固定于顶上那行，
 * 并用下面那行的对应字母一一取代原文的字母(字母字符的大小写状态应该保留)。
 * 因此，使用这个密匙， Attack AT DAWN (黎明时攻击)就会被加密为Tpptad TP ITVH。
 * <p>
 * 请实现下述接口，通过指定的密匙和明文得到密文。
 * <p>
 * 数据范围：1≤n≤100  ，保证输入的字符串中仅包含小写字母
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
        //构建list--即更新后的加密字母表
        for (int i = 0; i < key.length(); i++) {
            //边添加边判断--妙
            if (set.add(key.charAt(i))) {
                list.add(key.charAt(i));
            }
        }
        //再把剩余的字母加入list--会有顺序的呀，就是更新后的加密字母表
        for (int j = 0; j < 26; j++) {
            //j + 'a'：即偏移j索引后对应的字母
            if (!set.contains((char) (j + 'a'))) {
                list.add((char) (j + 'a'));
            }
        }

        //再加密
        StringBuilder sb = new StringBuilder();
        for (int k = 0; k < tarStr.length(); k++) {
            //获取当前字符在新版加密字母表中的下标
            int index = tarStr.charAt(k) - 'a';
            //得到加密后的字母
            sb.append(list.get(index));
        }
        System.out.println(sb);
    }
}

