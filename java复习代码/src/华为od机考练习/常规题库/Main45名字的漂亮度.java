package 华为od机考练习.常规题库;

/**
 * 45)名字的漂亮度
 *
 * @author :zoutongkun
 * @date :2022/7/25 1:25 下午
 * @description :
 * @modyified By:
 */

import java.util.*;

public class Main45名字的漂亮度 {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        //注意：即便是多个case，因为第一行就会录入这个值，
        //因此后面只需for循环录入多个case处理即可，也不需要while，亲测通过！
//        while (sc.hasNextInt()) {
        //注意：在录入时，即便是int型，也当做字符串录入,然后再转就行，否则可能会因为换行的问题而出错
        //因此可以使用sc.next()或sc.nextLine()方法，二者在这里没啥差别，都会换行
        //因为若还有第二行，但使用的是nextInt录入，则由于不会换行，因此录不到下一行的值！！！
        int n = Integer.parseInt(sc.nextLine());
        //因为有多个case，因此使用循环，但循环体中处理的这就是单个case
        for (int i = 0; i < n; i++) {
            char[] strArr = sc.nextLine().toCharArray();
            //使用map存储各字母的频次
            Map<Character, Integer> map = new HashMap<>();
            for (char c : strArr) {
                map.put(c, map.getOrDefault(c, 0) + 1);
            }
            int res = 0;
            //再开一个数组保存各个频次并从高到低排序（因为由题意知，并不需要用到对应的字母，只需要频次即可）
            //int[] num = new int[map.keySet().size()];
            //换成Intger型，便于后续排序
            Integer[] num = new Integer[map.keySet().size()];
            int index = 0;
            for (Character c : map.keySet()) {
                num[index] = map.get(c);
                index++;
            }
            int mul = 26;
            //再排序-默认升序
            //注意：Arrays.sort()只能进行升序排列
//             Arrays.sort(num);
//                for (int j = num.length - 1; j >= 0; j--) {
//                    res += num[j] * mul;
//                    mul--;
//                }
            //若想逆序，则需要使用Integer型，于是我们可以使用integer型数组
            //然后使用lambda表达式自定义排序规则即可
            Arrays.sort(num, (o1, o2) -> o2 - o1);
            for (int j = 0; j < num.length; j++) {
                res += num[j] * mul;
                mul--;
            }
            System.out.println(res);
//            }
        }
    }
}
