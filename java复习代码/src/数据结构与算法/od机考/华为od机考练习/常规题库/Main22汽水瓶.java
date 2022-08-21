package 数据结构与算法.od机考.华为od机考练习.常规题库;

import java.util.Scanner;

/**
 * 22)汽水瓶
 * 描述
 * 某商店规定：三个空汽水瓶可以换一瓶汽水，允许向老板借空汽水瓶（但是必须要归还）。
 * 小张手上有n个空汽水瓶，她想知道自己最多可以喝到多少瓶汽水。
 * 数据范围：输入的正整数满足 1 \le n \le 100 \1≤n≤100
 * <p>
 * 注意：本题存在多组输入。输入的 0 表示输入结束，并不用输出结果。
 *
 * @author :zoutongkun
 * @date :2022/7/27 1:43 下午
 * @description :
 * @modyified By:
 */
public class Main22汽水瓶 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNextLine()) {
            int bottleNum = sc.nextInt();
            //当输入为0时，则终止输入--break
            if (bottleNum == 0) {
                break;
            }
            //取巧思路：当只要有两个空瓶时，就可以借一个空瓶，兑到一瓶，喝完后刚好还给老板即可
            //相当于每两个空瓶就可以换一瓶水
            System.out.println(bottleNum / 2);
        }
    }
}
