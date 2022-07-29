package 华为od机考练习.常规题库;

/**
 * 70）矩阵乘法计算量估算
 * 知识点：矩阵乘法次数的计算：
 * 以两个矩阵相乘为例，A1xA2，A1和A2为两个矩阵，
 * 假设A1的行列数是pxq，A2的行列数是qxr。
 * 那么对于A1xA2而言，我们需要分别执行pxr次对应A1的行元素乘以A2的列元素，
 * 根据线性代数知识，可以得到一共需要执行pxqxr次乘法。
 *
 * @author :zoutongkun
 * @date :2022/7/26 12:32 上午
 * @description :
 * @modyified By:
 */

import java.util.*;

/**
 * 思路：
 * 1、字符 是左括号，什么也不做，continue
 * 2、字符 是右括号，出栈，计算
 * 3、字符 是非括号，入栈
 */
public class Main70矩阵乘法计算量估算 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        //while根本没有意义，有n和for循环即可
//        while (sc.hasNextLine()) {
        int n = Integer.parseInt(sc.nextLine());
        //定义一个二维数组，用于存放各个矩阵的行和列
        int[][] num = new int[n][2];
        for (int i = 0; i < n; i++) {
            //录入每一行的元素，也即每个矩阵对应的行和列
            String[] matrixInfoStrArr = sc.nextLine().split(" ");
            //行
            num[i][0] = Integer.parseInt(matrixInfoStrArr[0]);
            //列
            num[i][1] = Integer.parseInt(matrixInfoStrArr[1]);
        }
        //录入要计算的表达式
        String tarFormulaStr = sc.nextLine();
        //使用栈计算
        Stack<Integer> stack = new Stack<>();
        //用于统计结果
        int timesCnt = 0;
        //遍历要计算的表达式
        for (int j = 0; j < tarFormulaStr.length(); j++) {
            char curChar = tarFormulaStr.charAt(j);
            //1）字符是左括号时，什么也不做，continue
            if (curChar == '(') {
                continue;
            } else if (curChar == ')') {
                //2）字符是右括号，出栈，计算
                int cc = stack.pop();
                int cr = stack.pop();
                int bc = stack.pop();
                int br = stack.pop();
                //计算两个矩阵相乘所需要的计算次数（属于数学知识）
                timesCnt += br * cc * bc;
                //同时将这空矩阵相乘后的结果矩阵的行和列入栈，
                //用于和其他矩阵相乘（也属于数学知识）
                stack.push(br);
                stack.push(cc);
            } else {
                //3）字符是非括号时，入栈
                //注意：由于大写字母A的ASC码就是65，
                //因此curChar - 'A'也就是有些解答中的curChar - 65
                //即找到该字母对应的矩阵
                int index = curChar - 'A';
                //将该矩阵对应的行和列进栈
                stack.push(num[index][0]);
                stack.push(num[index][1]);
            }
        }
        //最后，打印结果
        System.out.println(timesCnt);
//        }
    }
}

