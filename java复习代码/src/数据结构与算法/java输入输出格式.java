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
            int a = in.nextInt();//输入int型
            int b = in.nextInt();
            double k = in.nextDouble();//输入double型

            System.out.println(a + b);
        }
    }
}

// n阶矩阵的输入（多行输入输出）
class Main_2 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int ans = 0, x;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                x = in.nextInt();
                ans += x;
            }
        }
        System.out.println(ans);
    }
}


//输入矩阵
class Main_7{
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str;
        while ((str = br.readLine())!= null){
            int x = Integer.parseInt(str);//输入第一行
            int y = Integer.parseInt(br.readLine());//输入第二行
            int z = Integer.parseInt(br.readLine());//输入第三行

            int[][] arr1 = new int[x][y];
            int[][] arr2 = new int[y][z];
            int[][] res = new int[x][z];

            //矩阵a
            for (int i =0;i<x;i++){//一行一行地填充，输入x行
                String s = br.readLine();
                String[] sa = s.split(" ");//把字符串按照空格分割成字符串数组
                for (int j =0;j<y;j++){
                    arr1[i][j] = Integer.parseInt(sa[j]);//固定某一行，将值填入该行
                }
            }
            //矩阵b
            for (int i = 0;i<y;i++){//输入y行
                String s = br.readLine();
                String[] sb = s.split(" ");
                for (int j = 0;j<z;j++){
                    arr2[i][j] = Integer.parseInt(sb[j]);
                }
            }

            //结果矩阵，此时是构造一个矩阵，而不是通过键盘输入了，只需两个循环即可
            for (int i = 0;i< x;i++){
                for (int j = 0;j<z;j++){
                    //再往该矩阵中填充值即可
                    for (int k = 0;k<y;k++){
                        res[i][j] += arr1[i][k] * arr2[k][j];
                    }
                }
            }

            StringBuilder sb = new StringBuilder();
            for (int i = 0;i<x;i++){
                sb.setLength(0);
                for (int j = 0;j<z;j++){
                    sb.append(res[i][j]).append(" ");
                }
                System.out.println(sb.toString().trim());
            }
        }
    }
}


//输入一个数组
class Main_4 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while (in.hasNext()) {
            String[] str = in.nextLine().split(" ");
            int[] arr = new int[str.length];
            for (int i = 0; i < str.length; i++) {
                arr[i] = Integer.parseInt(str[i]);
            }
            //for(int a:arr){
            //    System.out.println(a);
            //}
        }
    }
}


//输入一个字符串（使用标准输入流）
//nextLine就是输入字符串，务必记住
class Main_5{
    public static void main(String[] args) throws IOException {
        //输入
        Scanner sc = new Scanner(System.in);
           String str = sc.nextLine();//输入字符串
        //处理
           StringBuffer res = new StringBuffer();//用于操作字符串，最后还要转为字符串
           int len = str.length();
            for (int i = len - 1 ;i>=0;i--){
                res.append(str.charAt(i));
            }
            //输出
            System.out.println(res.toString());
    }
}

//若为多行字符串输入，即多个案例时：
//功能：倒置字符串
class Main_53{
    public static void main(String[] args) throws IOException {
        //输入
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()){//1.先判断
            //处理
            String str = sc.nextLine();//2.再输入字符串
            StringBuffer res = new StringBuffer();//用于操作字符串，最后还要转为字符串
            int len = str.length();
            for (int i = len - 1 ;i>=0;i--){
                res.append(str.charAt(i));
            }
            //输出
            System.out.println(res.toString());
        }

    }
}

//关于字符串的常见转换（用的是标准输入流）
class Main_6{
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);//输入
        String input;
        String str = sc.nextLine();//读取字符串
        int count = Integer.parseInt(str);//将字符串转为int型，表示次数m
        char[] chs = str.toCharArray();//将字符串转化为字符数组
        StringBuffer res = new StringBuffer();//该对象用于操作字符串
        for (int i =0;i < count;i ++){
            input = sc.nextLine();
            res.append(input.charAt(i));
            System.out.println(res.toString());
        }
    }
}

//使用缓冲流输入输出（不常用）
class Main_3 {
    public static void main(String[] args) throws IOException {
        //通过将输入流 InputStreamReader传入到BufferedReader中，再用BufferedReader来读取数据
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());//再一行一行地开始读，此时读进来的是string类型，
        // 而并不是int型，因此还要将其转化为int类型，这是与标准输入流所不同的地方，标准输入流要更方便一些，毕竟一步到位
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


//使用缓冲流输入一个字符串(此时可以使用缓冲流，因为它输入字符串时是一步到位的）
class Main_51{
    public static void main(String[] args) throws IOException {
        //输入
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();
        //处理
        StringBuffer res = new StringBuffer();//务必注意：stringBuffer不等于string，还需要进行转换，使用toString方法即可
        int len = str.length();
        for (int i = len - 1;i>=0;i--){
            res.append(str.charAt(i));
        }
        //输出
        System.out.println(res.toString());
    }
}

//使用缓冲流输入多行字符串时，即多个案例时：
//功能：倒转字符串
class Main_52{
    public static void main(String[] args) throws IOException {
        //输入
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input;//1.先再外面定义一个成员变量
        while ((input = br.readLine()) != null){//2.再判断，用于多行输入
            //处理
            StringBuffer res = new StringBuffer();//务必注意：stringBuffer不等于string，还需要进行转换，使用toString方法即可
            int len = input.length();
            for (int i = len - 1;i>=0;i--){
                res.append(input.charAt(i));
            }
            //输出
            System.out.println(res.toString());
        }

    }
}