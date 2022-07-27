package 华为od机考练习.常规题库;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * @author :zoutongkun
 * @date :2022/7/25 2:02 下午
 * @description :
 * @modyified By:
 */
public class Main45_自写 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = Integer.parseInt(sc.nextLine());
        //使用for循环来处理一个一个case
        for (int i = 0; i < n; i++) {
            String str = sc.nextLine();
            char[] strArr = str.toCharArray();
            //使用map记录频次
            Map<Character, Integer> map = new HashMap<>();
            for (char c : strArr) {
                map.put(c, map.getOrDefault(c, 0) + 1);
            }
            //再使用一个数组存储频次并排序
            Integer[] frequency = new Integer[map.keySet().size()];
            int index = 0;
            for (Integer value : map.values()) {
                frequency[index] = value;
                index++;
            }
            //再排序
            Arrays.sort(frequency, (o1, o2) -> o2 - 01);
            //再求漂亮度
            int num = 26;
            int res = 0;
            for (Integer integer : frequency) {
                res+=num*integer;
                num--;
            }
            //最后打印即可
            System.out.println(res);
        }
    }
}
