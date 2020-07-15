package 数据结构与算法.联发科极限编程大赛;

import java.util.Scanner;

/**
 * Targaryen DaenerysStormborn死后的房子,她的第一个名字,未燃尽的,Meereen女王,女王安达Rhoynar和第一个男人,
 * 卡利熙的草,断路器的连锁店和龙的母亲,几位领导人决定玩一个游戏来决定谁将坐在铁王座,acircle有n个人,他们的id 1 ~ n (n是接近1)。
 * 从1开始计数，第一轮数到m的人会出局，第二轮开始从下一个人开始计数数到m^2的人会出局。
 * 以此类推，直到最后一轮中数到m^(n - 1)的人出局，剩下最后一轮。
 * 输出这个人的人数。
 *
 * 输入
 * 一行，包含两个整数sn和m。
 * 数据范围:n≤15,m≤5n≤15,m≤5
 *
 * 输出
 * 输出最后剩下的人的数量。
 */
class 圆圈中最后剩下的数字_平方 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        //while (in.hasNext()){
            String str = in.nextLine();
            String[] strArr = str.split(" ");
            int[] arr = new int[strArr.length];
            for (int i = 0; i < strArr.length; i++) {
                arr[i] = Integer.parseInt(strArr[i]);
            }
            int n = arr[0],m = arr[1];
            if (n == 0)     /* 特殊输入的处理 */
                System.out.println(-1);
            if (n == 1)     /* 递归返回的条件 */
                System.out.println(1);
            //return (LastRemaining_Solution(n - 1, m) + m) % n;
       // }


    }
}
