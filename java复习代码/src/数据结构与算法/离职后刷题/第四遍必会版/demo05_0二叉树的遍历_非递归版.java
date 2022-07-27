package 数据结构与算法.离职后刷题.第四遍必会版;

import 数据结构与算法.TreeNode;

import java.util.*;

public class demo05_0二叉树的遍历_非递归版 {
    //写法2：（也很推荐）
    //我们使用栈来进行迭代，过程如下：
    //初始化栈，并将根节点入栈；
    //当栈不为空时：
    //弹出栈顶元素 node，并将值添加到结果中；
    //如果 node 的右子树非空，将右子树入栈；
    //如果 node 的左子树非空，将左子树入栈；
    //由于栈是“先进后出”的顺序，所以入栈时先将右子树入栈，
    //这样使得前序遍历结果为 “根->左->右”的顺序。
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if (root == null) {
            return res;
        }

        Stack<TreeNode> stack = new Stack<>();
        while (!stack.isEmpty() || root != null) {
            while (root != null) {
                //先取根节点呀
                res.add(root.val);
                //并将其入栈
                stack.push(root);
                //不断往其左节点遍历并入栈，直到为空
                root = root.left;
            }
            //此时再开始出栈，即得到的就是其左节点
            TreeNode temp = stack.pop();
            //然后转向该节点的右边节点，继续上面整个过程
            root = temp.right;
        }
        return res;
    }


    //写法1：类比层次遍历，思路清晰
    public List<Integer> preOrderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if (root == null) {
            return res;
        }
        Stack<TreeNode> stack = new Stack<>();
        //先把根节点入栈
        stack.push(root);
        //再处理栈中的节点
        while (!stack.isEmpty()) {
            //出栈
            TreeNode cur = stack.pop();
            //加入res，即先把根节点取出呀
            res.add(cur.val);
            //再把其左右节点入栈
            //因为是栈，所以是先进后出，而前序遍历的顺是根节点-左节点-右节点，
            //因此要先把右节点入栈，再把左节点入栈
            if (cur.right != null) {
                stack.push(cur.right);
            }

            if (cur.left != null) {
                stack.push(cur.left);
            }
        }
        return res;
    }

    //自写一遍
    public List<Integer> preOrderTraversal01(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if (root == null) {
            return res;
        }

        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            res.add(stack.pop().val);
            if (root.right != null) {
                stack.push(root.right);
            }
            if (root.left != null) {
                stack.push(root.left);
            }

        }
        return res;
    }


    //附：递归版（前序遍历）
    public List<Integer> preorderTraversal01(TreeNode root) {
        //结果集
        List<Integer> res = new ArrayList<>();
        //开始递归（带上结果集res，当然，也可以将其定义为全局变量，这样就不用带入啦~）
        dfs01(res, root);
        return res;
    }

    void dfs01(List<Integer> res, TreeNode root) {
        if (root == null) {
            return;
        }
        res.add(root.val);//把根节点存入结果集中
        //System.out.print(root.val + " ");打印
        dfs01(res, root.left);
        dfs01(res, root.right);
    }

    //（2）非递归中序遍历（比较好理解）
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        while (root != null || !stack.isEmpty()) {
            //不断往左子树方向走，每走一次就将当前节点保存到栈中
            //这是模拟递归的调用
            while (root != null) {
                stack.push(root);
                root = root.left;
            }
            //当前节点为空，说明左边走到头了，从栈中弹出左节点并保存
            //（易知最先弹出的就是最左边的结点）
            TreeNode tmp = stack.pop();
            res.add(tmp.val);
            //然后转向该节点的右边节点，继续上面整个过程
            //（易知，对于叶子节点，此时右节点为空，则上面的while不会走，
            // 则又会从栈中弹出一个元素，而这个元素即为第一个根元素呀，
            // 该元素也就在中间啦，实现了中序遍历的效果~）
            root = tmp.right;
        }
        return res;
    }

    //附：递归版（中序遍历）
    public List<Integer> inorderTraversal001(TreeNode root) {
        List<Integer> res = new ArrayList<Integer>();
        dfs02(res, root);
        return res;
    }

    void dfs02(List<Integer> res, TreeNode root) {
        if (root == null) {
            return;
        }
        //按照 左-打印-右的方式遍历
        dfs02(res, root.left);
        res.add(root.val);
        dfs02(res, root.right);
    }

    //（3）非递归后序遍历

    /**
     * 后序遍历的输出顺序是左、右、根，当我们采用前序遍历的方法，
     * 但是先遍历右子树，实现的效果是根、右、左，刚好和后序遍历的结果想法，
     * 所以我们通过add(0, node)的方式将顺序反序，达到我们想要的效果。
     *
     * @param root
     * @return
     */
    public List<Integer> postOrderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        while (root != null || !stack.isEmpty()) {
            while (root != null) {
                //对比前序遍历
                // 改变1：每次都将结点存入头部！！！
                res.add(0, root.val);
                //入栈
                stack.push(root);
                //改变2.1:先遍历右子树（而不是左子树）
                root = root.right;
            }
            //弹出（但不加入res）
            TreeNode temp = stack.pop();
            //改变2.2：再遍历左子树
            root = temp.left;
        }
        return res;
    }

    //附：递归版（后序遍历）
    public List<Integer> postorderTraversal(TreeNode root) {
        //结果集
        List<Integer> res = new ArrayList<>();
        //开始递归（带上结果集res）
        dfs03(res, root);
        return res;
    }

    void dfs03(List<Integer> res, TreeNode root) {

        //递归终止的条件
        if (root == null) {
            return;
        }
        //开始遍历
        dfs03(res, root.left);
        dfs03(res, root.right);
        res.add(root.val);//把根节点存入结果集中
        //System.out.print(root.val + " ");
    }

}
