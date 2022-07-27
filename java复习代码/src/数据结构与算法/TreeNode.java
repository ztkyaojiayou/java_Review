package 数据结构与算法;

//二叉树的节点类（通用）
public class TreeNode {
        public int val;//节点的值
        public TreeNode left;//指向左节点的节点
        public TreeNode right;//指向右节点的节点
        public TreeNode(int x) { val = x; }//节点的构造函数（即对节点赋值）
    }

    class Demo{
        public static void main(String[] args) {
            TreeNode node = new TreeNode(1);
            int val = node.val;
        }
    }
