package 数据结构与算法.第三遍;

public class demo09_2双指针之颜色分类_荷兰国旗问题 {
    public void sortColors(int[] nums) {
        int len = nums.length;
        //定义三个指针，表示三个区间
        int left = 0;
        int right = len-1;
        int cur = 0;
        while (cur <= right){//即当当前指针遍历到了‘2’区间时，就表示已经排序完毕
            if (nums[cur] == 0){
                swap(nums,cur,left);//交换，和左边的‘1’区间交换
                left++;//右移
                cur++;//同时遍历的指针也右移
            }else if (nums[cur] == 1){//不用动，因为‘1’区间就是放在中间的
                cur++;
            }else {
                swap(nums,cur,right);//和右边的‘2’区间交换
                right--;//左移
            }
        }
    }

    //交换的具体实现
    private void swap(int[] nums, int a, int b) {
        int temp = nums[a];
        nums[a] = nums[b];
        nums[b] = temp;
    }
}
