package 数据结构与算法.第三遍;


/**
 * @author :zoutongkun
 * @date :2022/4/9 1:17 上午
 * @description :
 * @modyified By:
 */
//1.先创建HeroNode结点类，在其中编写具体的结点的遍历方法、删除结点方法和查找方法,因为所有的方法都是基于结点的
// （这是重点和核心）
class TreeNode {
    //1.1定义基本成员变量
    private int val;//序号
    private TreeNode left; //左结点，默认null
    private TreeNode right; //右节点，默认null

    //构造器，只构造value，不构造左右结点（使用set方法）
    public TreeNode(int val) {
        this.val = val;
    }

    //get和set方法（get方法在这里没啥用）
    public void setLeft(TreeNode left) {
        this.left = left;
    }
    public void setRight(TreeNode right) {
        this.right = right;
    }
//    public int getVal() {
//        return val;
//    }

//    public void setVal(int val) {
//        this.val = val;
//    }

//    public TreeNode getLeft() {
//        return left;
//    }

//    public TreeNode getRight() {
//        return right;
//    }
}

public class demo000_2构建二叉树 {
    public static void main(String[] args) {
        //创建/添加需要的结点（此时还没有安置到二叉树上）
        //根节点
        TreeNode root =  new TreeNode(1);
        //自定义子节点（先只设val。当然也可以直接是构造器设置左右子节点啦）
        TreeNode node2 = new TreeNode(2);
        TreeNode node3 = new TreeNode(3);
        TreeNode node4 = new TreeNode(4);
        TreeNode node5 = new TreeNode(5);

        //3.3再把以上结点按照要求放置到各个分支上(说明，我们先手动创建该二叉树，后面我们学习递归的方式创建二叉树)
        //至此，一颗包含指定结点的二叉树创建完成
        root.setLeft(node2);
        root.setRight(node3);
        node3.setRight(node4);
        node3.setLeft(node5);
    }
}
