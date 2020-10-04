package 秋招笔试.其他杂七杂八;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class 跟谁学test01 {
    public static void main(String[] args) {
        //直接输入数字，然后变成数组的方法（切记）
        Scanner sc = new Scanner(System.in);
        List<Integer> list = new ArrayList<>();
        String[] str = sc.nextLine().split(" ");
        for (int i = 0; i < str.length; i++) {
            list.add(Integer.valueOf(str[i]));
        }
        int[] nums = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            nums[i] = list.get(i);
        }
        int target = sc.nextInt();
        System.out.println(method(nums, target));
    }

    public static int method(int[] arr, int target) {
if (arr == null || arr.length == 0){
    return 0;
}
        int left = 0;
        int right = arr.length - 1;
        while(left <= right) { //说明继续查找
            int mid = (left + right) / 2;
            if(arr[mid] == target) {
                return mid;
            } else if ( arr[mid] > target) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
 return left;
    }
}
