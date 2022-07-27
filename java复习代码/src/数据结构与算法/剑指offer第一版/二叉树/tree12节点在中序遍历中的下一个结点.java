package 数据结构与算法.剑指offer第一版.二叉树;

/**
 * 题目：给定一棵二叉树和其中的一个结点，如何找出中序遍历顺序的下一个结点？
 * 树中的结点除了有两个分别指向左右子结点的指针以外，还有一个指向父结点的指针。
 *
 * 【解】：（此题有指向父结点的指针，极大的降低了难度，
 *      不然你还得先先序遍历一些这棵树，把达到此结点的路径记录下来）
 *      要寻找中序遍历二叉树的下一个结点，（画图易知）
 *      1. （先从此节点往下看）若此结点有右子树（此时此节点无论在去父节点的哪边都一样），
 *      则其下一个结点就为：其右子树上的最左结点,但若没有最左节点，则就为其右子节点。
 *      2. （再往上看）若没有右子树，则：
 *           - 2.1若它是其父节点的左结点（左孩子），那么它的下一个结点就是他的父节点
 *           - 2.2若它是其父节点的右结点（右孩子），而它自己又还没有右子树，
 *                这时我们就要沿着指向父节点的指针一直向上找了，
 *                直到根结点（此时无下一个节点）或者此结点是他父节点的左子结点，
 *                此时它的下一个节点就是它的父节点的右节点（即变为第一种情况）
 */

class TreeLinkNode {

    int val;
    TreeLinkNode left = null;
    TreeLinkNode right = null;
    TreeLinkNode parent = null;//即往上找，即找当前结点的父节点

    TreeLinkNode(int val) {
        this.val = val;
    }
}

public class tree12节点在中序遍历中的下一个结点 {

    public TreeLinkNode GetNext(TreeLinkNode pNode) {//pNode表示目标结点
        if (pNode.right != null) {//1.即情况1，要找的数为其右子树上的最左结点
            TreeLinkNode pNext = pNode.right;//pNext表示目标结点pNode的下一个结点，即我们要找的结点
            while (pNext.left != null)//1.1即去找其右子树上的最左结点,即为所求
            {
                pNext = pNext.left;
            }
            //1.2 但若没有最左节点，则就为其右子节点
            return pNext;
        } else {//2.而若连右节点都没有，则要去找其父节点了
            while (pNode.parent != null) {//判断其父节点是否存在
                //即情况2.1:当前节点没有右子树,若它是其父节点的左结点（孩子），那么它的父节点即为所求
                TreeLinkNode parent = pNode.parent;
                if (parent.left == pNode)//即表示它是其父节点的左结点（孩子）
                {
                    return parent;//于是，其父节点即为所求
                }
                //即情况2.2：若它是其父节点的右结点（孩子），而它自己又还没有右子树，
                // 这时我们就要沿着指向父节点的指针一直向上找了，
                // 直到根结点或者此结点是他父节点的右子结点，此时无下一个节点。
                pNode = pNode.parent;
            }
        }
        //3.若全部遍历完都没有找到就说明该节点没有下一个节点
        return null;
    }
}
