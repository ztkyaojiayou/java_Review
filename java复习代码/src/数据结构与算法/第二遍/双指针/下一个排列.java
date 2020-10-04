package 数据结构与算法.第二遍.双指针;

import java.util.Arrays;

//双指针
public class 下一个排列 {
    public void nextPermutation(int[] nums){
        for (int i = nums.length-2;i>0;i--){//从倒数第二个位置开始
            for (int j = nums.length-1;j>0;j--){//从倒数第一个位置开始
                if (nums[i] < nums[j]){//若当前元素小于其后面的元素，则交换
                    swap(nums,i,j);
                    //交换完后对后面的元素升序排列
                    Arrays.sort(nums,i+1,nums.length);
                }
            }
        }
    }
//交换
    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}
