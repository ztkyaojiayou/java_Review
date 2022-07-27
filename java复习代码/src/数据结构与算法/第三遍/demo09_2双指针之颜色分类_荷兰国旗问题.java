package 数据结构与算法.第三遍;

//https://leetcode-cn.com/problems/sort-colors/solution/shu-ju-jie-gou-he-suan-fa-san-zhi-zhen-j-eirk/
//数组中只有3种数字0，1，2。我们只需要把0往前面移，2往后面移即可,难度不大.
public class demo09_2双指针之颜色分类_荷兰国旗问题 {
    public void sortColors(int[] nums) {
        int len = nums.length;
        //定义三个指针，表示三个区间
        int left = 0;
        int right = len - 1;
        int cur = 0;
        //即当当前指针遍历到了‘2’区间时，就表示已经排序完毕
        while (cur <= right) {
            if (nums[cur] == 0) {
                //交换，和左边的‘1’区间交换
                swap(nums, cur, left);
                left++;//右移，因为当前元素已经被换成了0了呀，即已经摆放正确啦
                cur++;//同时遍历的指针也右移
                //不用动，因为‘1’区间就是放在中间的
            } else if (nums[cur] == 1) {
                //处理下一位
                cur++;
            } else {
                //若为2，则应该放在右边，即与最右边的元素互换即可，同时指针左移，
                //也是因为当前元素已经被换成了2了呀，即已经摆放正确啦
                swap(nums, cur, right);//和右边的‘2’区间交换
                right--;//左移
                //那为什么cur不用左移呢？因为此时从右边调换过来的元素还不确定是什么元素，
                // 比如可能是0，可能是1，因此此时还要再次处理一次该位置的元素，因此此时不能移动cur！！！
            }
        }
    }

    //交换的具体实现
    private void swap(int[] nums, int a, int b) {
        int temp = nums[a];
        nums[a] = nums[b];
        nums[b] = temp;
    }

    //自写一遍
    public void sortColors02(int[] nums) {
        int left = 0;
        int right = nums.length - 1;
        int cur = 0;
        //不能使用for循环，因为不是每一种情况都要移动i
        while (cur <= right) {
            if (nums[cur] == 0){
                swap(nums,cur,left);
                left++;
                cur++;
            }else if (nums[cur] == 1){
                cur++;
            }else if (nums[cur] == 2){
                swap(nums,cur,right);
                right--;
            }
        }
    }
}
