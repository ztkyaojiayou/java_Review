package 秋招笔试.神策;

import java.util.Scanner;

public class test03 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int a = Integer.valueOf(sc.nextLine());
        String regex = " ";
        String[] strs = sc.nextLine().split(regex);
        int len = strs.length;
        int[] nums = new int[len];
        for (int i = 0;i<len;i++){
            nums[i] = Integer.valueOf(strs[i]);
    }
        int[] res = new int[len];
        int j = 0;
        for (int i = a;i<len;i++){
            res[j] = nums[i];
            j++;
        }
        for (int i = 0;i<a;i++){
            res[j] = nums[i];
            j++;
        }
        StringBuilder sb = new StringBuilder();

        for (int i = 0;i<res.length;i++){
            sb.append(nums[i]);
        }
        String ress = sb.toString();
        System.out.println(ress);
    }
}
