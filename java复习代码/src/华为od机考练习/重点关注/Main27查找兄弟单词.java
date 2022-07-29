package 华为od机考练习.重点关注;

import java.util.*;

/**
 * 27)查找兄弟单词
 * @authore:
 * @data: 2021/10/13
 * @Description:
 */
/**
 * 功能描述: <br>
 * @Param: 输入：
 * 6 cab ad abcd cba abc bca abc 1
 * 复制
 * 输出：
 * 3
 * bca
 * 复制
 * 说明：
 * abc的兄弟单词有cab cba bca，所以输出3
 * 经字典序排列后，变为bca cab cba，所以第1个字典序兄弟单词为bca
 * @Return:
 * @Author: guokun
 * @Date: 2021/10/13 14:18
 */
public class Main27查找兄弟单词 {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        while (scanner.hasNext()){
            String[] ss = scanner.nextLine().split(" ");
            Integer a = Integer.parseInt(ss[0]);
            String x = ss[ss.length-2];
            Integer k = Integer.parseInt(ss[ss.length-1]);
            List<String> list = new ArrayList<>();

            for (int i = 1; i <=a ; i++) {
                if (isBrother(x,ss[i])){
                    list.add(ss[i]);
                }
            }
            int size = list.size();
            System.out.println(size);
            if (size>=k){
                Collections.sort(list);
                System.out.println(list.get(k-1));
            }

        }
    }
    public static boolean isBrother(String x,String y){
        if (x.length()!=y.length()||y.equals(x)){
            return false;
        }
        char[] s = x.toCharArray();
        char[] j= y.toCharArray();
        Arrays.sort(s);
        Arrays.sort(j);
        return new String(s).equals(new String(j));


    }
}


