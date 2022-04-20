package 数据结构与算法.离职后刷题.第四遍必会版;

//考察的是后序遍历与二叉搜索树的性质的结合，不难
public class demo05_10判断所给数组是否为二叉搜索树的后序遍历序列 {
    public boolean verifySequenceOfBST(int[] nums) {
        //调用递归函数即可
        return method(nums, 0, nums.length - 1);
    }

    //使用递归
    public boolean method(int[] nums, int start, int end) {
        //递归结束的条件
        if (start >= end) {
            return true;
        }
        //根节点（二叉搜索树的后序遍历的性质）
        int root = nums[end];
        //用于遍历
        int index = 0;

        while (nums[index] < root && index < end - 1) {
            index++;
        }
        //标记一下，以此分为两段
        int mid = index;
        while (nums[index] > root && index < end - 1) {
            index++;
        }
        //即若没能遍历到最后，就说明不是后序遍历序列
        if (index != end - 1) {
            return false;
        }
        //再递归判断其左右子树
        return method(nums, start, mid - 1) && method(nums, mid, end - 1);
    }
}
