package 秋招笔试.滴滴;

import java.util.ArrayList;
import java.util.Scanner;

public class test01 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int len1 = Integer.valueOf(sc.nextLine());//长度
        String s = sc.nextLine();//目标串
        int len2 = s.length();//目标串长度
        ArrayList<String> list = new ArrayList<>();
        int ys = len2 / len1;
        if (ys == 0){
            for (int i = 0;i<len2-len1;i+=len1){
                list.add(s.substring(i,i+len1));
            }
        }else {
            for (int i = 0;i<len2-ys-len1+1;i+=len1){
                list.add(s.substring(i,i+len1));
            }
            list.add(s.substring(len2-ys-len1+1,len2));
        }

        StringBuilder sb = new StringBuilder();
        StringBuilder res1 = null;

        //全在list里面了
        for (int i = 0;i<list.size();i++){
            char[] chars = list.get(i).toCharArray();
            reverse(chars,0,chars.length-1);
            String res = new String(chars);
            res1 = sb.append(res);
        }
        String res112 = res1.toString();
        System.out.println(res112);
    }

    //反转每个单词
    private static void reverse(char[] chars, int i, int j) {
        while (i<j){
            swap(chars,i,j);
            i++;
            j--;
        }
    }
    //交换交换
    private static void swap(char[] chars, int i, int j) {
        char temp = chars[i];
        chars[i] = chars[j];
        chars[j] = temp;
    }
}
