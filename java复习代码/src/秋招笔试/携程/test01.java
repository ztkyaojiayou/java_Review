package 秋招笔试.携程;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class test01 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<String> list = new ArrayList<>();
        ArrayList<String> res = new ArrayList<>();

        String s1 = sc.nextLine();//敏感词
        char[] array1 = s1.toCharArray();
        Arrays.sort(array1);
        String str1 = new String(array1);
        String s2 = sc.nextLine();//句子
        String regex = " ";
        //变成字符串数组，去掉了空格
        String[] s20 = s2.split(regex);
        int len = s20.length;
        String s3 = sc.nextLine();//替换词
        //加入list
        for (int i=0;i<len;i++){
            list.add(s20[i]);
        }
        //再一个一个比对
for (int i = 0;i<len;i++){
    char[] array = list.get(i).toCharArray();
    Arrays.sort(array);
    String sorted_str = new String(array);
    //若相等，就替换
    if (sorted_str == str1){
      res.add(s3);
    }else {
        //否则不动
        res.add(list.get(i));
    }
}

//输出
      String res1 = " ";
for (int i = 0;i<res.size();i++){
    res1 = res1 + res.get(i);
}

        System.out.println(res1);
    }
}
