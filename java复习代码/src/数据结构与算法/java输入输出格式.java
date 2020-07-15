package 数据结构与算法;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

// 本题为考试单行输入输出规范示例，无需提交，不计分。
class Main_1 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        /**
         * public boolean hasNext()用于判断此扫描器中是否还有输入，若有，则返回 true。
         */
        while (in.hasNextInt()) {// 注意，如果输入是多个测试用例，请通过while循环处理多个测试用例
            //public int nextInt():用于获取下一个int类型的值。
            int a = in.nextInt();
            int b = in.nextInt();
            System.out.println(a + b);
        }
    }
}

// 本题为考试多行输入输出规范示例，无需提交，不计分。
// 矩阵的输入
class Main_2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int ans = 0, x;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                x = sc.nextInt();
                ans += x;
            }
        }
        System.out.println(ans);
    }
}


class Main_3 {
    public static void main(String[] args) throws IOException {
        //通过将输入流 InputStreamReader传入到BufferedReader中，再用BufferedReader来读取数据
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());//再一行一行地开始读，同时将其转化为int类型
        int[] arr = new int[n];
        String[] strArr = br.readLine().split(" ");//读的另外一种方式：跳过空格，只获取数据，得到的就是一个数组（常用）

        int sum = 0;
        for (int i = 0; i < n; i++) {
            sum += Integer.parseInt(strArr[i]) - 1;
        }

        System.out.println(sum);
        return;
    }
}

//输入数组
class Main_4 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            String[] str = sc.nextLine().split(" ");
            int[] arr = new int[str.length];
            for (int i = 0; i < str.length; i++) {
                arr[i] = Integer.parseInt(str[i]);
            }
            for(int a:arr){
                System.out.println(a);
            }
        }
    }
}
