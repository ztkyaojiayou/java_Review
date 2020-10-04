package 秋招笔试.贝壳;

import java.util.ArrayList;
import java.util.Scanner;

public class test01 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int a = Integer.parseInt(sc.nextLine());
        ArrayList<String[]> list = new ArrayList<>();
        for (int i = 0;i<a;i++){
            String str = sc.nextLine();
            String regex = " ";
            String[] strs = str.split(regex);

            list.add(strs);
        }
        for (int i = 0;i< a;i++){
            String[] res = list.get(i);
            System.out.println(res[i]);
        }

        //
        //for (int i = 0;i<num;i++){
        //    char a1 = list.get(i).charAt(0);
        //    char a2 = list.get(i).charAt(2);
        //    char b1 = list.get(i).charAt(4);
        //    char b2 = list.get(i).charAt(6);
        //    if ((a1 == 'S' && a2 =='B' && b1 == 'J' && b2 == 'J') || (a1 == 'J'&& a2 =='S'&& b1 == 'B' && b2 == 'B')|| (a1 == 'B' && a2 =='J' && b1 =='S'&& b2 =='S')) {
        //        System.out.println("left");
        //    }else if ((a2 == 'S'&& a1 =='B' && b1 == 'J' && b2 == 'J') || (a2 == 'J' && a1 =='S'&& b1 == 'B' && b2 == 'B')|| (a2 == 'B' && a1 =='J' && b1 == 'S' && b2 == 'S')){
        //        System.out.println("right");
        //    }else {
        //        System.out.println("same");
        //    }
        //}

    }
}
