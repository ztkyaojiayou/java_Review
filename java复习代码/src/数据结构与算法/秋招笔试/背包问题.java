package 数据结构与算法.秋招笔试;

import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int knapsackCapacity = Integer.parseInt(input.nextLine().trim());

        String[] volumesStr = input.nextLine().split(" ");
        int[] volumes = new int[volumesStr.length];
        for (int i = 0; i < volumesStr.length; i++) {
            volumes[i] = Integer.parseInt(volumesStr[i].trim());
        }

        String[] valuesStr = input.nextLine().split(" ");
        int[] values = new int[valuesStr.length];
        for (int i = 0; i < valuesStr.length; i++) {
            values[i] = Integer.parseInt(valuesStr[i].trim());
        }

        if (volumes.length == values.length) {
            System.out.println(method(knapsackCapacity, volumes, values));
        } else {
            System.out.println("道具数量不一致。");
        }
        input.close();
    }

    private static int method(int knapsackCapacity, int[] volumes, int[] values) {
            int[] vol = volumes;//道具体积
            int[] val = values;//价值
            int bag_vol = knapsackCapacity;//背包容量

            int len = val.length;
            int[][] v = new int[len+1][bag_vol+1];
            //初始化
            for(int i=0;i<v.length;i++) {
                v[i][0]=0;
            }
            for(int i=0;i<v[0].length;i++) {
                v[0][i]=0;
            }
            //一般情况
            for(int i=1;i<v.length;i++) {
                for(int j=1;j<v[0].length;j++) {
                    if(vol[i-1]>j) {
                        v[i][j]=v[i-1][j];
                    }else {
                        v[i][j]=Math.max(v[i-1][j], val[i-1]+v[i-1][j-vol[i-1]]);
                    }
                }
            }
            //返回结果即可
            return v[len][bag_vol];
        }
}
