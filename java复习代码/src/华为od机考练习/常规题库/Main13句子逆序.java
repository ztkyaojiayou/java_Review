package 华为od机考练习.常规题库;

import java.util.Scanner;

/**
 * 13）句子逆序
 * @author :zoutongkun
 * @date :2022/7/27 12:19 下午
 * @description :
 * @modyified By:
 */
public class Main13句子逆序 {
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        Scanner in=new Scanner(System.in);
        String str=in.nextLine();
        String s[]=str.split(" ");
        for(int i=s.length-1;i>=0;i--)
            if(i!=0)
                System.out.print(s[i]+" ");
            else
                //最后一个单词不用再加空格啦
                System.out.print(s[i]);
    }
}

