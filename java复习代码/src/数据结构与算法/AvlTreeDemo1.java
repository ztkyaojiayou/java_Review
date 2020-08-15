package 数据结构与算法;

class Node {
    int value;
    Node left;
    Node right;

    public Node(int value) {

        this.value = value;
    }
    //左旋转
    private void leftRotate() {

        ////创建新的结点，以当前根结点的值
        //Node newNode = new Node(value);
        ////把新的结点的左子树设置成当前结点的左子树
        //newNode.left = left;
        ////把新的结点的右子树设置成带你过去结点的右子树的左子树
        //newNode.right = right.left;
        ////把当前结点的值替换成右子结点的值
        //value = right.value;
        ////把当前结点的右子树设置成当前结点右子树的右子树
        //right = right.right;
        ////把当前结点的左子树(左子结点)设置成新的结点
        //left = newNode;

        //左旋转，其实就是分为两步：
        //1.先安排/创建新节点（包括值，左右子树）
        //2.再安排当前节点（同样包括值，左右子树）

        //1.先安排/创建新节点（包括值，左右子树）
        //1.1 创建新的结点，值设置为当前(根)结点的值
        Node new_node = new Node(value);
        //1.2 再把新结点的左子树设置成当前结点的左子树
        new_node.left = this.left;//this表示当前节点
        //1.3 同时再把新结点的右子树设置成当前结点的右子树的左子树
        new_node.right = this.right.left;
        //2.再安排当前节点（同样包括值，左右子树）
        //2.1 把当前结点的值替换成当前结点的右子结点的值
        this.value = this.right.value;
        //2.2 同时再把当前结点的右子树设置成当前结点的右子树的右子树（一连串）
        this.right =this.right.right;
        //2.3 最后再把当前结点的左子树(左子结点)设置成新的结点，此时一颗平衡二叉树就应用而生了。
        this.left = new_node;

    }

    //右旋转，其实也就是分为两步：
    //1.先安排/创建新节点（包括值，左右子树）
    //2.再安排当前节点（同样包括值，左右子树）
    private void rightRotate() {
        //1.先安排/创建新节点（包括值，左右子树）
        //1.1 创建新的结点，值设置为当前(根)结点的值
        Node newNode = new Node(value);
        //1.2 再把新结点的右子树设置成当前结点的右子树
        newNode.right = this.right;//this表示当前节点
        //1.3 同时再把新结点的左子树设置成当前结点的左子树的右子树
        newNode.left = this.left.right;
        //2.再安排当前节点（同样包括值，左右子树）
        //2.1 把当前结点的值替换成当前结点的左子结点的值
        this.value = this.left.value;
        //2.2 同时再把当前结点的左子树设置成当前结点的左子树的左子树（一连串）
        this.left = this.left.left;
        //2.3 最后再把当前结点的右子树(左右子结点)设置成新的结点，此时一颗平衡二叉树就应用而生了。
        this.right = newNode;
    }
}

// 3.测试
        public class AvlTreeDemo1 {
            public static void main(String[] args) {
                //int[] arr = {4,3,6,5,7,8};
                //int[] arr = { 10, 12, 8, 9, 7, 6 };
                int[] arr = {10, 11, 7, 6, 8, 9};

            }
        }

