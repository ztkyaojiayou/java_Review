package 秋招笔试.微众银行;

import com.sun.org.apache.bcel.internal.generic.IF_ACMPEQ;

import java.util.Scanner;

public class test02 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String regex = " ";
        String[] str1 = sc.nextLine().split(regex);
            int a = Integer.valueOf(str1[0]);//数组长度
            int b = Integer.valueOf(str1[1]);//查询次数
        String[] str2 = sc.nextLine().split(regex);

        int[] nums = new int[a];
        for (int i = 0;i<str2.length;i++){
            nums[i] = Integer.valueOf(str2[i]);
        }
      //处理
for (int i = 0;i<b;i++){
    int test = sc.nextInt();
    int res = method(nums, test);
    System.out.println(res);
}
    }

    public static int method(int[] nums,int num){
        int res = Integer.MAX_VALUE;
        for (int i = 0;i<nums.length;i++){
            if (num == nums[i]){
                return nums[i];
            }
            res = Math.min(res,Math.abs(num - nums[i]));
        }

        for (int i = 0;i<nums.length;i++){
            if (res == Math.abs(num - nums[i])){
                return nums[i];
            }
        }
return -1;
    }

}
