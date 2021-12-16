package 数据结构与算法.第三遍;

public class demo09_3双指针之沉最多水的容器 {
    public int maxArea(int[] height) {
        int res = 0;
        //双指针，一头一尾
        int left = 0;
        int right = height.length - 1;
        while (left < right) {
            int area = (right - left) * Math.min(height[right], height[left]);
            res = Math.max(res, area);
            // 移动矮柱子，因为在移动的过程中，宽肯定是减少的，
            // 因此要在移动的过程中尽量增加“有效”高度
            //易知，那边矮就移动哪边既可
            if (height[left] < height[right]) {
                left++;
            } else {
                right++;
            }
        }
        return res;
    }
}
