package 数据结构与算法.剑指offer第一版.数组;

/**
 * 题目：给定一个数组 A[0,1,…,n-1]，请构建一个数组 B[0,1,…,n-1]，
 * 其中 B 中的元素 B[i]=A[0]×A[1]×…×A[i-1]×A[i+1]×…×A[n-1]（刚好无A[i]），不能使用除法。
 *
 * 【例】： 已知A[]={1,2,3}，求B[]
 *B[0]=A[1]×A[2]=2×3=6，没有A[0]
 *B[1]=A[0]×A[2]=1×3=3，没有A[1]
 *B[2]=A[0]×A[1]=1×2=2，没有A[2]
 *所以 B 数组为{6,3,2}
 *
 解析：其实就是这个当前节点元素所有在原数组左边的元素乘积乘以原数组在这个索引右边的元素乘积；
 那么我们的做法就是，先把每一个元素的左边的乘积和右边的乘积都算出来，
 最后所求数组就是这两个数据的相同位置索引的乘积构成的新数组；
 */
public class array03构建乘积数组 {
    public int[] constructArr(int[] nums) {
        if (nums == null || nums.length == 0) {
            return new int[]{};
        }
        int[] res = new int[nums.length];
        //左边乘积
        int left = 1;
        for (int i = 0; i < res.length; i++) {
            res[i] =left;
            left *= nums[i];
        }
        //右边乘积
        int right = 1;
        for (int j = res.length - 1; j >= 0; j--) {
            res[j] *= right;
            right *= nums[j];
        }
        return res;
    }
}
