package 秋招笔试.其他杂七杂八;

import java.util.ArrayList;

public class bilibil {
    public int[] printMatrix(int[][] matrix) {

            ArrayList<Integer> ret = new ArrayList<>();

            int r1 = 0, r2 = matrix.length - 1;//行的最小下标和最大下标（用于列遍历）
            int c1 = 0, c2 = matrix[0].length - 1;//列的最小下标和最大下标（用于行遍历）

        while (r1 <= r2 && c1 <= c2) {
                //（先从左上到右上）1.先遍历第一行（遍历完第一圈时，则为新的一行，c1++即可）到最后，把其添加到ArrayList中（先从左到右）
                for (int i = c1; i <= c2; i++)
                    ret.add(matrix[r1][i]);//添加第一行到ArrayList中

                //（再从右上到右下）2.接着再遍历最右边的那一列（遍历完第一圈时，则为新的一列，r2--即可），但从第二行（即r1 + 1）的最右边那个数开始（因为第一行的最右边那个数在上次已经遍历完啦）
                //把其添加到ArrayList中
                for (int i = r1 + 1; i <= r2; i++)//
                    ret.add(matrix[i][c2]);

                //（再先从右下到左下）3.再遍历最底行（遍历完第一圈时，则为倒数的新的一行，r2--即可），但从高索引列向低索引列遍历，且从倒数第二列（即c2 - 1）开始；再存入ArrayList中
                if (r1 != r2)//表示不能上行与下行不能交错
                    for (int i = c2 - 1; i >= c1; i--)
                        ret.add(matrix[r2][i]);
                //（最后从左下到左上）4.再遍历最左边列（遍历完第一圈时，则为新的顺数的一列，c1++即可），但从高索引行向低索引行遍历，且从倒数第二行（即r2 - 1）开始；再存入ArrayList中
                if (c1 != c2)//表示不能右列与左列不能交错
                    for (int i = r2 - 1; i > r1; i--)
                        ret.add(matrix[i][c1]);
                r1++; r2--; c1++; c2--;//这代表一圈一圈地遍历，即每遍历完一圈就把相应的索引改变1
            }
        int size = ret.size();
        int[] res = new int[size];
        for (int i = 0;i<size;i++){
            res[i] = ret.get(i);
        }
        return res;//最后返回该ArrayList即可
    }
}
