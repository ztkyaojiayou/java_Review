package 数据结构与算法.第三遍;

import java.util.ArrayList;
import java.util.Arrays;

public class demo32_合并重叠区间 {
    public int[][] merge(int[][] nums) {
        ArrayList<int[]> list = new ArrayList<>();
        Arrays.sort(nums,(v1,v2) -> v1[0]-v2[0]);//按照第一个元素升序排列（当为二维数组时）
        int i = 0;
        while (i<nums.length-1){
            //每个小区间的左右两端
            int left = nums[i][0];
            int right = nums[i][1];
            while (right > nums[i+1][0]){//前一个区间的右端点和后一个区间的左端点比较，
                // 若大于，则更新前一个区间的右端点为后一个区间的右端点
                right = Math.max(right,nums[i+1][1]);
                i++;
            }
            //把新区间加入list中，同时处理下一个区间
            list.add(new int[]{left,right});
            i++;
        }
return list.toArray(new int[0][]);
    }
}
