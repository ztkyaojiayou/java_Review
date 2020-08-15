package 数据结构与算法.剑指offer题解.第二遍.二叉树;

class Node17 {

    int val;
    Node17 left = null;
    Node17 right = null;
    Node17 parent = null;//即往上找，即找当前结点的父节点

    Node17(int val) {
        this.val = val;
    }
}
public class 节点在中序遍历下的下一个节点 {
    public Node17 getNext(Node17 node){
        if (node.right != null){
            Node17 pNext = node.right;
            while (pNext.left != null){
                pNext = pNext.left;
            }
            return pNext;
        }else {
            while (node.parent != null){
                Node17 parent = node.parent;
                if (parent.left == node){
                    return parent;
                }
                node = node.parent;
            }
        }
        return null;
    }
}
