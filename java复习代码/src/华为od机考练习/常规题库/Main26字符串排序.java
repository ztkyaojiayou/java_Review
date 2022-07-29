package 华为od机考练习.常规题库;

/**
 * 26）字符串排序--典型题，务必掌握
 *
 * @author :zoutongkun
 * @date :2022/7/23 8:39 下午
 * @description :
 * @modyified By:
 */

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

class Main26 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        //同样地，题目没说一次性输入多行/多个测试用例，
        //因此可以不用考虑加while，就当只输入了一行处理即可--亲测通过
//        while (sc.hasNextLine()) {
        String str = sc.nextLine();
        char[] strArr = str.toCharArray();
        //先把字母拎出来存入list并排序
        List<Character> letterList = new ArrayList<>();
        for (char ch : strArr) {
            //
            if (Character.isLetter(ch)) {
                letterList.add(ch);
            }
        }
        //1）将英文字母先排序好
        //那么一个疑问：有相同英文按照输入排序 这个有考虑么？
        //答：该排序方法是将char转换成小写后再排序，
        //那么A和a转换后相减等于0，就不排序，保持原有的顺序~
        letterList.sort(Comparator.comparing(Character::toLowerCase));
        // 2）对字符串按照新顺序重新构建
        // 方法：
        // 2.1使用StringBuilder来构建
        // 2.2依次遍历原字符串：
        // 2.2.1当为字母时，则该位置要取新字母顺序的list中的字母，使用一个指针维护
        // 2.2.2而若是非英文字母，则直接拼接至原位置即可
        StringBuilder sb = new StringBuilder();
        for (int i = 0, j = 0; i < str.length(); i++) {
            if (Character.isLetter(str.charAt(i))) {
                sb.append(letterList.get(j++));
            } else {
                sb.append(str.charAt(i));
            }
        }
        //3）输出排序后的结果
        System.out.println(sb);
        // }
    }
}

/**
 * 自写一遍
 */
class Main330 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String str = sc.nextLine();
        char[] strArr = str.toCharArray();
        List<Character> letterList = new ArrayList<>();
        for (char ch : strArr) {
            //Character.isLetter(c)方法：判断指定字符是否为字母
            if (Character.isLetter(ch)) {
                letterList.add(ch);
            }
        }
        //对字母排序--先转为小写再自然排序
        letterList.sort(Comparator.comparing(Character::toLowerCase));
        //再查询构造结果字符串
        StringBuilder sb = new StringBuilder();
        int listIndex = 0;
        //直接再遍历那个字符数组也是一样的，因为当字符为空格时也同样会在里面~
        for (char c : strArr) {
            if (Character.isLetter(c)) {
                sb.append(letterList.get(listIndex));
                listIndex++;
            } else {
                sb.append(c);
            }
        }
        System.out.println(sb);
    }
}


