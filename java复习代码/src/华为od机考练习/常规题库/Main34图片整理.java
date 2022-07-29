package 华为od机考练习.常规题库;

/**
 * 34)图片整理-即把字符串中的各字母按照ASCII码值从小到大排序后输出
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
class Main340{
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String str = sc.nextLine();
        char[] strArr = str.toCharArray();
        //排序
        Arrays.sort(strArr);
        //输出
        for (char c : strArr) {
            System.out.print(c);
        }
        System.out.println();

    }
}


