package 秋招笔试.网易;

import 数据结构与算法.第二遍.二叉树.TreeNode;

import java.util.Scanner;

public class test01 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String[] s = sc.nextLine().split(" ");//两个数

    }
    public int method(TreeNode root){
        if (root == null){
            return 0;
        }
        int left = 0;
        int right = 0;
        TreeNode cur = root;
        int count = 0;
        while (cur != null){
            if (cur.left != null && cur.right != null && cur.left.left == null && cur.right.right == null){
                count ++;
            }

            left = method(root.left);
            right = method(root.right);

        }
        return left+right+count;
    }
}
