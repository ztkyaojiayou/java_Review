package 秋招笔试.其他杂七杂八;

import java.util.ArrayList;
import java.util.Scanner;

public class test022 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String str = sc.nextLine();
        System.out.println(str);
        String s = " ";
        String[] s1 = str.split(s);
        ArrayList<String> list = new ArrayList<>();
        list.add(s);
    }
}
