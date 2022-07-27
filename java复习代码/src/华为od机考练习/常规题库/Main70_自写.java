package 华为od机考练习.常规题库;

import java.util.Scanner;
import java.util.Stack;

/**
 * @author :zoutongkun
 * @date :2022/7/26 12:00 下午
 * @description :
 * @modyified By:
 */
public class Main70_自写 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        //矩阵个数
        int n = Integer.parseInt(sc.nextLine());
        //二维数组，存矩阵的行列值
        int[][] matrixArr = new int[n][2];
        //存储
        for (int i = 0; i < n; i++) {
            String[] matrixStr = sc.nextLine().split(" ");
            //行
            matrixArr[i][0] = Integer.parseInt(matrixStr[0]);
            //列
            matrixArr[i][1] = Integer.parseInt(matrixStr[1]);
        }
        //录入目标计算式
        String tarFormulaStr = sc.nextLine();
        //统计总计算次数
        int totalCnt = 0;
        //使用一个栈来计算--以(A(BC))为例
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < tarFormulaStr.length(); i++) {
            char tarChar = tarFormulaStr.charAt(i);
            if (tarChar == '(') {
                continue;
            } else if (tarChar == ')') {
                //出栈并计算
                //矩阵C的行和列
                Integer row1 = stack.pop();
                Integer col1 = stack.pop();
                Integer row2 = stack.pop();
                Integer col2 = stack.pop();
                //计算
                totalCnt+=row1*row2*col2;
                //再把得到的新矩阵的行和列入栈
                //但务必注意，此时应该先入列再入行，否则不符合矩阵的计算规则
                stack.push(col2);
                stack.push(row1);
            }else {
                //当为非括号时，直接入栈--入栈的是该字母对应的矩阵的行和列，因此需要定位到该字符对应的矩阵
                int index = tarChar - 'A';
                stack.push(matrixArr[index][0]);
                stack.push(matrixArr[index][1]);
            }
        }
        System.out.println(totalCnt);
    }
}
