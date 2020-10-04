package 秋招笔试.携程;

import java.util.HashMap;
import java.util.Scanner;

public class test02 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String min = sc.nextLine();
        String[] aa = sc.nextLine().split(",");
        String tar = sc.nextLine();
        String s = "";
        for (int i = 0; i < aa.length - 1; i++) {
            String[] tmp = aa[i].split(" ");
            s += replaceAllWord(min, tmp, tar);
            s += ",";
        }
        String[] cc = aa[aa.length - 1].split(" ");
        s += replaceAllWord(min, cc, tar);
        System.out.println(s);
    }

    private static String replaceAllWord(String min, String[] content, String tar) {
        HashMap<Character, Integer> scr = count(min);
        for (int i = 0; i < content.length; i++) {
            HashMap<Character, Integer> con = count(content[i]);
            int n = con.size();
            int count = 0;
            if (con.size() == scr.size()) {
                for (char key : con.keySet()) {
                    if (con.get(key).equals(scr.get(key))) {
                        count++;
                    }
                }
            }
            if (count == n) {
                content[i] = tar;
            }
        }
        String s = "";
        for (int i = 0; i < content.length - 1; i++) {
            s += content[i] + " ";
        }
        s += content[content.length - 1];
        return s;
    }


    public static HashMap<Character, Integer> count(String str) {
        HashMap<Character, Integer> map = new HashMap<>();
        char[] ch = str.toCharArray();
        for (int i = 0; i < str.length(); i++) {
            if (map.containsKey(ch[i])) {
                map.put(ch[i], map.getOrDefault(ch[i], 0) + 1);
            } else {
                map.put(ch[i], 1);
            }
        }
        return map;
    }

}