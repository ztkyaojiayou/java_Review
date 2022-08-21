package 数据结构与算法.od机考.华为od机考练习.重点关注;

import java.util.*;

/**
 * 27)查找兄弟单词
 * 描述
 * 定义一个单词的“兄弟单词”为：交换该单词字母顺序（注：可以交换任意次），而不添加、删除、修改原有的字母就能生成的单词。
 * 兄弟单词要求和原来的单词不同。例如： ab 和 ba 是兄弟单词。 ab 和 ab 则不是兄弟单词。
 * 现在给定你 n 个单词，另外再给你一个单词 x ，让你寻找 x 的兄弟单词里，按字典序排列后的第 k 个单词是什么？
 * 注意：字典中可能有重复单词。
 *
 * @authore:
 * @data: 2021/10/13
 * @Description:
 */

/**
 * 功能描述: <br>
 *
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
        while (scanner.hasNext()) {
            String[] strArr = scanner.nextLine().split(" ");
            Integer len = Integer.parseInt(strArr[0]);
            String tarStr = strArr[strArr.length - 2];
            Integer k = Integer.parseInt(strArr[strArr.length - 1]);
            //使用一个list存储目标字符串的兄弟单词
            List<String> broList = new ArrayList<>();
            for (int i = 1; i <= len; i++) {
                //判断是否为目标字符串的兄弟单词
                if (isBrother(tarStr, strArr[i])) {
                    broList.add(strArr[i]);
                }
            }
            //打印兄弟单词的个数
            int size = broList.size();
            System.out.println(size);
            //再打印第k个兄弟单词
            if (size >= k) {//这里可以不判断，题干已经说明了k<len
                Collections.sort(broList);
                System.out.println(broList.get(k - 1));
            }
        }
    }

    /**
     * 是否为兄弟单词判断
     */
    public static boolean isBrother(String x, String y) {
        //若长度不等或长度相等但值也相等则不互为兄弟单词
        if (x.length() != y.length() || y.equals(x)) {
            return false;
        }
        //将两个字符串变成字符数组，
        //再按照统一规则排序，
        //排序后再转为字符串，
        //最后再判断是否二者是否相等即可
        char[] s = x.toCharArray();
        char[] j = y.toCharArray();
        Arrays.sort(s);
        Arrays.sort(j);
        return new String(s).equals(new String(j));


    }
}


