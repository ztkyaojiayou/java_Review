package 数据结构与算法.离职后刷题.第四遍必会版;

import 数据结构与算法.TreeNode;

import java.util.ArrayList;
import java.util.List;

//推荐做法：还是中序遍历，不过是找到了就不再遍历啦！！！
//时间复杂度：令 h 为树高，先到达叶子位置（最小节点位置），复杂度为 O(h)，
//然后找到第 k 小的元素，复杂度为 O(k)。整体复杂度为 O(h + k)
//空间复杂度：令 h 为树高，复杂度为 O(h)

public class demo05_8二叉搜索树的第k小的结点 {
    int res = -1;
    int cnt = 0;

    public int kthSmallest(TreeNode root, int k) {
        inorder(root, k);
        return res;
    }

    //中序遍历
    public void inorder(TreeNode root, int k) {
        if (root == null) {
            return;
        }
        //1.向左遍历
        inorder(root.left, k);
        //2.处理根节点
        //因为是第k小的元素，又由于二叉搜索树的中序遍历本就是升序，
        //因此当到第k个元素时就可以得到答案并退出啦！！！
        cnt++;
        if (cnt == k) {
            res = root.val;
            return;
        }
        //3.向右遍历
        inorder(root.right, k);
    }
}

//自写一遍
class Solution02 {
    int ans = -1;
    int cnt = 0;

    public int kthSmallest(TreeNode root, int k) {
        method(root, k);
        return ans;
    }

    //中序遍历
    private void method(TreeNode root, int k) {
        if (root == null) {
            return;
        }
        method(root.left, k);
        cnt++;
        if (cnt == k) {
            ans = root.val;
            return;
        }
        method(root.right, k);
    }
}


//方法2：最朴素的做法（不推荐）
class Solution {
    List<TreeNode> list = new ArrayList<>();//全局变量

    public TreeNode method(TreeNode root, int k) {
        //先中序遍历，把节点保存到list中，此时为递增序列
        inorder(root);
        //再去list中找第k-1个节点即为所求
        if (k > 0 && list.size() >= k) {
            return list.get(k - 1);
        }
        return null;
    }

    //中序遍历，结果存入list中
    private void inorder(TreeNode root) {
        //递归结束的条件
        if (root == null) {
            return;
        }
        //不断往左递归
        inorder(root.left);
        //到达最左结点时，将这个结点加入list
        list.add(root);
        // 此时，再将该最左节点作为根节点向右进行递归，
        // 一直到最右子节点，然后再将该最右结点加入list，
        // 最后再从下往上以同样的逻辑递归出其他节点
        inorder(root.right);
    }


    //自写一遍
    //存的是结点（而不是值val）
    List<TreeNode> list02 = new ArrayList<>();//全局变量

    public TreeNode method02(TreeNode root, int k) {
        //中序遍历后就成了升序序列，此时就可以直接取目标值了
        inorder02(root);
        if (k > 0 && list02.size() > k) {
            return list02.get(k - 1);
        }
        return null;
    }

    //中序遍历，结果存入list中
    private void inorder02(TreeNode root) {
        if (root == null) {
            return;
        }
        inorder02(root.left);
        list.add(root);
        inorder02(root.right);
    }
}

