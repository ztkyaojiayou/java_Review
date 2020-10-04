package 秋招笔试.爱奇艺;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class test02 {
    public static void main(String[] args) {

        //直接输入数字，然后变成数组的方法（切记）
        Scanner sc = new Scanner(System.in);
        List<Integer> list = new ArrayList<>();
        String[] str = sc.nextLine().split(" ");
        for (int i = 0; i < str.length; i++) {
            list.add(Integer.valueOf(str[i]));
        }
        int[] arr = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            arr[i] = list.get(i);
        }
        Arrays.sort(arr);//默认为升序
        System.out.println(arr[arr.length/2]);
    }
}
