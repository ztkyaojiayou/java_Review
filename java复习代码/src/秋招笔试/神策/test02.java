package 秋招笔试.神策;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class test02 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String str = sc.nextLine();
        ArrayList<String> res = method(str);
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int i = 0;i<res.size()-1;i++){
    sb.append("'"+res.get(i) +"'"+ ", ");
}
        sb.append("'"+res.get(res.size()-1)+"'");
        sb.append("]");
        String ress = sb.toString();
        System.out.println(ress);
//        for (int i = 0;i<res.size();i++){
//            System.out.print(res.get(i));
//        }

    }

    public static ArrayList<String> method(String str) {
        ArrayList<String> res=new ArrayList<String>();
        if(str.length()==0||str==null)return res;
        //int n= str.length();
        helper(res,0,str.toCharArray());
        Collections.sort(res);
        return res;

    }
    public static void helper( ArrayList<String> res,int index,char[] s) {
        if(index==s.length-1)
            res.add(new String(s));

        for(int i=index;i<s.length;i++) {
            // 第一次循环i与index相等，相当于第一个位置自身交换，关键在于之后的循环，
            // 之后i != index，则会交换两个不同位置上的字符，直到index==str.size()-1，进行输出；
            if(i==index||s[index]!=s[i])
            {
                swap(s,index,i);
                helper(res,index+1,s);
                swap(s,index,i);//复位，用以恢复之前字符串顺序，达到第一位依次跟其他位交换的目的
            }
        }
    }

    public static void swap(char[]t,int i,int j) {
        char c=t[i];
        t[i]=t[j];
        t[j]=c;
    }
}
