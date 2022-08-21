package 数据结构与算法.od机考.华为od机考练习.重点关注;

/**
 * @author :zoutongkun
 * @date :2022/7/21 11:29 下午
 * @description :
 * @modyified By:
 */
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
public class Main27_自写 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()){
            //先录入
            String[] strArr = sc.nextLine().split(" ");
            //再做准备工作
            int len = Integer.parseInt(strArr[0]);
            String tarStr = strArr[ strArr.length- 2];
            int k = Integer.parseInt(strArr[strArr.length - 1]);
            //使用一个list存储兄弟词
            List<String> list = new ArrayList<>();
            //查找兄弟词
            for (String str : strArr) {
                if (isBrother(str,tarStr)){
                    list.add(str);
                }
            }
            //现在list中全是兄弟单词了
            //1)输出兄弟单词的个数
            System.out.println(list.size());
            //2)再输出排序后的第k个单词
            if (list.size() >= k){
                //字典排序
                Collections.sort(list);
                //输出
                System.out.println(list.get(k-1));
            }
        }

    }

    private static boolean isBrother(String str, String tarStr) {
        if ((str.length()!= tarStr.length()) || (str.equals(tarStr))){
            return false;
        }
        //先变成字符数组，再排序，再比较是否相等即可
        char[] strArr = str.toCharArray();
        char[] tarStrArr = tarStr.toCharArray();
        //排序--使用Arrays工具类，而不是Collections工具类
        Arrays.sort(strArr);
        Arrays.sort(tarStrArr);
        //再比较是否相等--转化为字符串再比较，方便
        return new String(strArr).equals(new String(tarStrArr));
    }
}


