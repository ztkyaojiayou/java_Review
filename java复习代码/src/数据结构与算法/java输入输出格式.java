package 数据结构与算法;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * 注意：优先使用Scanner，而非BufferedReader
 * 参考：https://baijiahao.baidu.com/s?id=1679000678665689763&wfr=spider&for=pc
 */
// 本题为考试单行输入输出规范示例，无需提交，不计分。
class Main_1 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        /**
         * 1）hasNext()用于判断此扫描器中是否还有字符输入（但数字也算），若有，则返回 true。
         * 强调的是要录入的是否是一个字符，而并不强调“是否有”！！！）
         * 2）hasNextInt(）则用于判断输入的是否为int型，是则继续，否则为false，退出程序。
         * 3）因为有while，则可以输入多个测试案例
         */
        while (in.hasNextInt()) {// 注意，如果输入是多个测试用例，请通过while循环处理多个测试用例
            //public int nextInt():用于获取下一个int类型的值，前面可以有空格，但不影响，
            // 但后面的空格则会导致它返回，此时即表示这个一行代码执行完毕，转而执行下一行代码。
            //易知，输入多行和
            int a = in.nextInt();//输入int型
            int b = in.nextInt();//再输入一个int型，但都属于同一批次，即同一个测试用例
            double k = in.nextDouble();//输入double型

            System.out.println(a + b);
        }
    }
}


// n阶矩阵的输入（多行输入输出）-只能输入一个测试用例
class Main_2 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        //由于无while，则只能输入一个测试用例
        int n = in.nextInt();//输第一个int型
        int ans = 0, x;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                x = in.nextInt();//输入n平方个int
                //累加
                ans += x;
            }
        }
        System.out.println(ans);
    }
}


// n阶矩阵的输入（多行输入输出）--可输入多个测试用例--使用while即可
class Main_21 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while (in.hasNext()){
            //由于无while，则只能输入一个测试用例
            int n = in.nextInt();//输第一个int型
            int ans = 0, x;
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    x = in.nextInt();//输入n平方个int
                    //累加
                    ans += x;
                }
            }
            System.out.println(ans);
        }
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
        /**
         * 1）in.hasNext():用于判断输入的行是否为字符串--可以有空格（若为in.hashNextInt则表示判断是否为int型）
         * 2）in.nextLine()：即获取到该行的值（易知可能有空格，因此可以使用split方法做切割，若为in.next，则只是获取第一个非空字符）
         * 3）while:表示可以输入组案例，即每输入一组案例，会执行while里面的代码，
         * 执行完后还可以继续输入下一组案例
         */
        //判断单个字符，但读取的却是一行，不冲突！！！
        while (in.hasNext()) {
            String[] str = in.nextLine().split(" ");
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

/**
 * 输入多组测试用例-- 因为使用了while (in.hasNextLine())
 */
class Main_412 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int cnt = 0;
        /**
         * 1）这一行的意义：
         * hasNextLine 是以Enter为结束准则，会读取敲击Enter之前所有输入的内容包括空格，
         * 判断的是否为整行（强调的是要录入一行，而并不强调“是否有”！！！），即以一个整行作为一个测试案例，
         * 每输入一行再按enter键后会先处理完这一行，
         * 但由于有while，因此若还继续输入一行，继续处理，以此类推。
         * 2）另外：这种写法也可以用于一个测试案例中有多行的情况
         * （其实这是情况才是常见的，我们一般不会把所有案例按行一次性输入的，而只是有些案例本身就有多行），
         * 此时可以先把一个测试用例中的多行先输入（好像还是有点没明白。。。。）
         * 要注意的是：判断字符或一行与读取单个字符还是读取一行并不冲突！！！
         */
        while (in.hasNextLine()) {
            //nextLine是读取整行，而next则只读一个字符（前面的空格不影响，但后面若有空格则会终止录入）
            //1)情况1：读整行--字符串前后的空格都会读取到
            String str1 = in.nextLine();
            cnt++;
            System.out.println(cnt);
            System.out.println(str1);
//            //2）情况2：读整行，且以空格切割该字符串
//            String[] str2 = in.nextLine().split(" ");
//            for(String a:str2){
//                System.out.println(a);
//            }
//            //可以使用stream流式输出--更优雅
//            Arrays.stream(str2).forEach(System.out::println);
//            // 情况3：只读第一个非空字符，但由于有while，
//            // 则一行中剩下的字符会当做多组测试案例处理，
//            // 且易知所有的空格都不会被读取到，因为会被忽略呀
//            String str3 = in.next();
//            System.out.println(str3);
        }
        System.out.println(cnt);
    }
}

class Main_413 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        /**
         * 这一行的意义：
         * 1）判断单个字符--即便输入的是一行
         * 2）while表示可以对多个字符进行多次处理，可理解为可处理多个测试案例
         * 要注意的是：判断字符或一行与读取单个字符还是读取一行并不冲突！！！
         */
        while (in.hasNext()) {
            //由上可知，hasNext是判断单个字符且字符前的空格会忽略，
            // 即不影响它的录入，但后面如有空格，则会终止录入，
            // 但此时由于有while循环，因此若输入的是一行字符串，则后面的字符会继续当做一次新的输入进行录入，
            // 可以理解为可以通过一行来输入多个测试用例
            //1）情况1：next只读第一个非空字符，后面的字符不会读取，但若有while，则会分多次读取
//            String str = in.next();
//            System.out.println(str);
            //2）情况2：
            // 即便hasNext判断的是第一个字符，但不影响nextLine读整行
            String strLine = in.nextLine();
            System.out.println(strLine);
//            int[] arr = new int[str.length];
//            for (int i = 0; i < str.length; i++) {
//                arr[i] = Integer.parseInt(str[i]);
//            }
//            for(String a:str){
//                System.out.println(a);
//            }


        }
    }
}


//直接输入数字（有空格时），然后变成数组的方法（切记）
class Main_8{
    public static void main(String[] args) {
        //直接输入数字，然后变成数组的方法（切记）
        Scanner sc = new Scanner(System.in);
        List<Integer> list = new ArrayList<>();
        String[] str = sc.nextLine().split(" ");
        for (int i = 0; i < str.length; i++) {
            list.add(Integer.valueOf(str[i]));
        }
        int[] nums = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            nums[i] = list.get(i);
        }
        Arrays.sort(nums);//默认为升序
        System.out.println(nums[nums.length/2]);
    }
}

//直接输入多组字符串（无空格时），把它存入list中再通过遍历来处理每一个字符串即可（常用）
class test02 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<String> list = new ArrayList<>();
        while (sc.hasNextLine()) {
            String str = sc.nextLine();
            list.add(str);
        }
        for (int i = 0; i < list.size(); i++) {
            String str1 = list.get(i);
            //然后就可以处理该字符串了
        }
    }
}

//直接输入多组字符，把它存入list中再通过遍历来处理每一个字符串即可（常用）
class Main_54 {
    public static void main(String[] args) {
        String str;
        Scanner sc = new Scanner(System.in);
        ArrayList<String> list = new ArrayList<>();
        while (sc.hasNextLine()){//只要下一行还有，就一直输入
            str = sc.nextLine();
            list.add(str);
        }
        for (int i = 0;i<list.size();i++){
            //再处理
            System.out.println(list.get(i));
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