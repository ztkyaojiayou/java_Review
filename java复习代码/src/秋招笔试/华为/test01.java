package 秋招笔试.华为;


import java.util.ArrayList;
import java.util.Scanner;

public class test01 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<String> list = new ArrayList<>();
        while (sc.hasNext()) {
            list.add(sc.next());
        }
        int n = list.size();
        String[] str = new String[n];
        for (int i = 0; i < n - 2; i++) {
            str[i] = list.get(i);
        }
        String temp_num = list.get(n - 2);
        int a = Integer.valueOf(list.get(n - 2));

        String tar_num = list.get(n - 1);
        String tar = "";
        for (int i = 0; i < tar_num.length(); i++) {
            if (tar_num.charAt(i) < temp_num.charAt(0)) {
                tar += tar_num.substring(i, i + 1);
            }
        }
        String[] str2 = new String[n - 2];
        for (int i = 0; i < n - 2; i++) {
            String tmp = "";
            for (int j = 0; j < str[i].length(); j++) {
                if (str[i].charAt(j) < temp_num.charAt(0)) {
                    tmp += str[i].substring(j, j + 1);
                }
            }
            str2[i] = tmp;
        }
        for (int i = 0; i < n - 2; i++) {
            if (str2[i].indexOf(tar) != -1) {
                System.out.println(str[i]);
            }
        }

    }
}

