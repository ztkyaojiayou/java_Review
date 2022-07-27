package 数据结构与算法.离职后刷题.第四遍必会版;

public class demo09_3双指针之沉最多水的容器 {
    public int maxArea(int[] height) {
        int res = 0;
        //双指针，一头一尾
        int left = 0;
        int right = height.length - 1;
        //注意：在双指针的题目中，经常使用while来进行循环遍历
        while (left < right) {
            //算面积：高度要取有效高度，即以矮柱子为主！！！
            int curArea = (right - left) * Math.min(height[right], height[left]);
            //时刻更新面积
            res = Math.max(res, curArea);
            // 移动矮柱子，因为在移动的过程中，宽肯定是减少的，
            // 因此要在移动的过程中尽量增加“有效”高度
            //易知，那边矮就移动哪边既可（非常精辟）
            if (height[left] < height[right]) {
                left++;
            } else {
                right--;
            }
        }
        return res;
    }

    //自写一遍
    int res02 = 0;
    public int maxArea02(int[] height) {
        int left = 0;
        int right = height.length - 1;
        while (left < right) {
            int curArea = (right - left) * Math.min(height[left], height[right]);
            res02 = Math.max(res02, curArea);
            // 移动，移动较矮的柱子，因为反正要移动，因此宽度肯定减少，
            // 那么就应该移动那根有可能增加有效高度的柱子！！！
            if (height[left] < height[right]) {
                left++;
            } else {
                right--;
            }
        }
        return res02;
    }
}
