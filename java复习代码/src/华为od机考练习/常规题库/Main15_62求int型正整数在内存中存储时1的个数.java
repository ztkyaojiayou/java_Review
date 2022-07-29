package 华为od机考练习.常规题库;

/**
 * 15）求int型正整数在内存中存储时1的个数--位运算
 * 方法：通过31次无符号右移，逐位与1进行与运算，结果为1则计数
 *
 * @author :zoutongkun
 * @date :2022/7/27 12:58 下午
 * @description :
 * @modyified By:
 */

import java.util.Scanner;

class Main15求int型正整数在内存中存储时1的个数 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int num = sc.nextInt();    //读取数字
        int cnt = 0;    //计数变量
        for (int i = 0; i < 32; i++) {
//            //也可以这样写
//            while (num != 0){
            //即二进制的与运算，也即它会先将num和1先转为32位的二进制数，再进行与运算，
            //易知，由于相当于是num的最后一位和1做与运算，
            //然后将num的二进制数不断右移并继续将最后一位和1做与运算，
            //易知，若结果为1，则表示num的这一位就是1，以此类推即可统计出num的二进制中1的个数
            if ((num & 1) == 1)     //如果末位为1则计数
                cnt++;
            //注意java的>>是有符号右移，也就是说，负数用>>右移的话，
            // 会在左侧补1而不是0，这就会影响最终对1的计数。
            // 所以若题目没说一定是正整数是时，我们最好使用无符号右移>>>。
            num = num >> 1;    //右移一位
        }
        System.out.println(cnt);
    }
}

/**
 * 方法2：更推荐
 * 思路：n 每和 (n-1) 相与(&)一次，1就减一，因此可以每与一次，就加1，直到为0
 */
class Main150 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int num = sc.nextInt();
        int cnt = 0;
        //一直和n-1做与运算，每做一次，num对应的二进制中的1就减1（可自行验证）
        while (num != 0) {
            num = num & (num - 1);
            cnt++;
        }
        System.out.println(cnt);
    }
}


/**
 * 62)查找输入整数二进制中1的个数--好像重复了
 * 方法：位运算
 *
 * @author :zoutongkun
 * @date :2022/7/27 10:06 下午
 * @description :
 * @modyified By:
 */
class Main62查找输入整数二进制中1的个数 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            int n = sc.nextInt();
            int count = 0;
            while (n != 0) {
                count += n & 1;
                n >>= 1;
            }
            System.out.println(count);
        }
    }
}


