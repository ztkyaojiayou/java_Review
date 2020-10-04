package 数据结构与算法.剑指offer题解.二叉树;

/**
 * 题目：输入一棵二叉树的根结点，判断该树是不是平衡二叉树。
 * 如果某二叉树中任意结点的左右子树的深度相差不超过 1 ，那么它就是一棵平衡二叉树。
 *
 * 【法1】：
 *      可以根据上一题（题53）已求出的TreeDepth，
 *      再判断：若此结点的左右子树的深度相差如果如果不超过1，则平衡
 *
 * 【法2】：
 *      由于法1，每次判断都会重复遍历最下面叶子结点，影响性能！
 *      所以此题最优解：
 *          应该采用后序遍历的方法
 *          因为后序遍历，每遍历到一个根结点，他的左右子树都已经走过一遍了，
 *          所以 我们可以每走过一个结点，标上他的深度。然后即可直接判定此结点下面的二叉树是否平衡了
 *
 * Created by nibnait on 2016/10/1.
 */

class TreeNode54 {
    int val = 0;
    TreeNode54 left = null;
    TreeNode54 right = null;

    public TreeNode54(int val) {
        this.val = val;

    }

}

public class tree54判断二叉树是否平衡 {
    //方法二：推荐，没有重复判断
    //约定：当不是平衡二叉树时返回-1，空树时，返回0
    public boolean IsBalanced02(TreeNode54 root) {
        //depth(root)为已经判断是平衡树了之后的树的高度，而-1则表示该树不是平衡二叉树
        return depth(root) != -1;//depth(root)不为-1，则为true，说明是平衡二叉树，反之则不是
    }
    public int depth(TreeNode54 root){
        //1.递归结束的条件
        // 空树也是一颗平衡二叉树
        if(root == null)
            return 0;

        //2.通过遍历，求深度
        //2.1向左遍历，求深度
        int left = depth(root.left);
        if(left == -1) {//说明左子树已经不平衡了
            return -1; //则没有必要进行下面的高度的求解了，返回-1，表示不是平衡二叉树
        }
        //2.2向右遍历，求深度
        int right = depth(root.right);
        if(right == -1) {//说明右子树已经不平衡了
            return -1;//则没有必要进行下面的高度的求解了，返回-1，表示不是平衡二叉树
        }
        //3.开始判断其左右子树的深度差的绝对值是否大于1
        //3.1若满足，则返回-1，说明不是平衡二叉树.
            if (Math.abs(left - right) > 1)//abs：求绝对值
         // 若不用绝对值方法，则可以写成：
         // if(left - right <(-1) || left - right > 1)
            {
                return -1;
            }
        else{
            //3.2否则，返回深度值
                return Math.max(left,right)+1;
            }

    }


    //方法一：（思路很自然，但有重复判断，并不是最优解，不推荐，但在牛客系统上的运行结果似乎差别不大）
    private static boolean IsBalanced01(TreeNode54 root){
        if (root == null){
            return true;
        }
        //先判断根节点两侧是否平衡
        int left = TreeDepth(root.left);
        int right = TreeDepth(root.right);
        int diff = left - right;
        if (diff>1 || diff<-1){
            return false;
        }
        //还要判断每一个节点的左右是否平衡，这很关键
        return IsBalanced01(root.left) && IsBalanced01(root.right);
    }

    //需要调用题53的代码：求二叉树的深度方法TreeDepth（）
    public static int TreeDepth(TreeNode54 root){
        if (root==null){
            return 0;
        }
        int left = TreeDepth(root.left);
        int right = TreeDepth(root.right);
        return Math.max(left,right)+1;
    }
}

