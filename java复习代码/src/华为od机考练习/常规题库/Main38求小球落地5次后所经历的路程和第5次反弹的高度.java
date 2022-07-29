package 华为od机考练习.常规题库;

/**
 * 38）求小球落地5次后所经历的路程和第5次反弹的高度
 *
 * @author :zoutongkun
 * @date :2022/7/28 10:43 下午
 * @description :
 * @modyified By:
 */

import java.util.*;

public class Main38求小球落地5次后所经历的路程和第5次反弹的高度 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while (in.hasNextInt()) {
            int initHeight = in.nextInt();
            //每次弹起的高度
            double curHeight = initHeight;
            //记录下落的路程
            double sum1 = 0;
            //记录弹起的路程
            double sum2 = 0;
            //落地和弹起先都按5次算，再将结果减去最后一次弹起的高度即为总路程（因为题目要求这一次只需落地即可）
            for (int i = 0; i < 5; i++) {
                sum1 += curHeight;
                curHeight = curHeight / 2;
                sum2 += curHeight;
            }
            //第五次之后未弹起，须减掉
            System.out.println(sum1 + sum2 - curHeight);
            System.out.println(curHeight);
        }
    }
}

