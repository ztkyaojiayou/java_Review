package 数据结构与算法.od机考.华为od机考练习.常规题库;

/**
 * 34)图片整理-即把字符串中的各字母按照ASCII码值从小到大排序后输出
 * 描述
 * Lily上课时使用字母数字图片教小朋友们学习英语单词，
 * 每次都需要把这些图片按照大小（ASCII码值从小到大）排列收好。
 * 请大家给Lily帮忙，通过代码解决。
 * Lily使用的图片使用字符"A"到"Z"、"a"到"z"、"0"到"9"表示。
 *
 * 方法：直接使用Arrays.sort(char ch)方法即可
 * @author :zoutongkun
 * @date :2022/7/25 1:21 上午
 * @description :
 * @modyified By:
 */
import java.util.*;

class Main34{
    public static void main(String args[]){
        Scanner sc = new Scanner(System.in);
        //同样地，对于一次只输入一个案例时，可以不用while
//        while(sc.hasNext()){
            String str = sc.nextLine();
            char[] ch = str.toCharArray();
            Arrays.sort(ch);
            for(char c : ch){
                System.out.print(c);
            }
            System.out.println();
        }
//    }
}


