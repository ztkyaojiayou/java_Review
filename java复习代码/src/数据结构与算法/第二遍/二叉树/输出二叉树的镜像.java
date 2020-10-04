package 数据结构与算法.第二遍.二叉树;

public class 输出二叉树的镜像 {
    public Node17 Mirror(Node17 root){
        //递归结束的条件
        if (root == null){
            return null;
        }

        swap(root);
        //递归交换
        Mirror(root.left);
        Mirror(root.right);
        return root;
    }
//交换
    private Node17 swap(Node17 node) {
        Node17 temp = node.right;
        node.right = node.left;
        node.left = temp;
        return node;
    }

}
