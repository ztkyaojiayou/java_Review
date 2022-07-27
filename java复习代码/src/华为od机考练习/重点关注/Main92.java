package 华为od机考练习.重点关注;

/**
 * 92）在字符串中找出连续最长的数字串
 * 方法：使用正则最方便--推荐
 * @author :zoutongkun
 * @date :2022/7/22 9:30 下午
 * @description :
 * @modyified By:
 */
import java.util.*;

public class Main92{
    public static void main(String args[]){
        Scanner sc = new Scanner(System.in);
        while(sc.hasNextLine()){
            String str = sc.nextLine();
            //根据正则表达式分割出数字字符串
            String s[] = str.split("[^0-9]");
            int max = 0;
            //遍历分割的字符串，找到最大长度
            for(int i = 0; i < s.length; i++){
                if(s[i] != "")
                    max = Math.max(max, s[i].length());
            }
            //再次遍历输出最大长度
            for(int i = 0; i < s.length; i++){
                if(s[i] != "" && s[i].length() == max)
                    System.out.print(s[i]);
            }
            System.out.println("," + max);
        }
    }
}

