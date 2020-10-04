package 秋招笔试.京东;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Pattern;

public class test02 {
    public static void main(String[] args) {
        String strInt = "0123456789";
        //String s = "prospect 772 2009 the brawler syrah-viognier (calaveras county)";
        Scanner sc = new Scanner(System.in);
        String regex = " ";
        ArrayList<Integer> list = new ArrayList<>();
        String[] strs = sc.nextLine().split(regex);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < strs.length; i++) {
            String str = strs[i];
            for (int j = 0;j<str.length();j++){
                if (str.charAt(j) >= 0 && str.charAt(j) <= 9 && str.charAt(j+1) >= 0 && str.charAt(j+1) <= 9){
                    sb.append(str.charAt(j));
                }
                String str1 = sb.toString();
                int num = Integer.valueOf(str1);
                if (num >= 1000 && num <= 3999){
                    list.add(num);
                }
            }
        }

        for (int i = 0;i<list.size();i++){
            System.out.print(list.get(i) + " ");//按行输出，不加ln即可
        }
    }

}

