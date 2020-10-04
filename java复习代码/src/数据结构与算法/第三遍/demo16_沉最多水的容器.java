package 数据结构与算法.第三遍;

public class demo16_沉最多水的容器 {
    public int maxArea(int[] height) {
        int res = 0;
        //双指针，一头一尾
        int left = 0;
        int right = height.length-1;
        while (left < right){
            int area = (right - left) * Math.min(height[right], height[left]);
res = Math.max(res,area);
if (height[left] < height[right]){//移动矮柱子，因为在移动的过程中，宽肯定是减少的，因此要在移动的过程中尽量增加“有效”高度
left++;
}else {
    right++;
}
        }
        return res;
    }
}
