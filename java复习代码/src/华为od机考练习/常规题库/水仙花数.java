package 华为od机考练习.常规题库;

/**
 * 打印从1到999999为例的水仙花数（0不为水仙花数）
 * 说明：水仙花数是指一个 3 位数，它的每个位上的数字的3次幂之和等于它本身，例如：1^3 + 5^3+ 3^3 = 153。
 * 严格来说3位数的3次幂数才称为水仙花数。可还存在着其它位数所对应的水仙花数。
 *
 * @author :zoutongkun
 * @date :2022/7/26 4:06 下午
 * @description :
 * @modyified By:
 */
class 水仙花数 {
    public static void main(String[] args) {
        for (int i = 1; i < 999999; i++) {
            int j = i, ret = 0, n = i, sum = 0;
            while (j != 0) {
                //判断当前数是几位数
                ret++;
                j /= 10;
            }
            while (n != 0) {
                //计算每一位数幂的和
                sum += Math.pow(n % 10, ret);
                n /= 10;
            }
            if (sum == i) {
                System.out.print(i + " ");
            }
        }
    }
}
//运行结果：
//1 2 3 4 5 6 7 8 9 153 370 371 407 1634 8208 9474 54748 92727 93084 548834


/**
 * 当只判断三位数时（这其实才是最本质的水仙花数）
 */
class Version02 {
    public static void main(String[] args) {
        for (int i = 100; i < 1000; i++) {
            //个位
            int j = i % 10;
            //十位
            int k = i / 10 % 10;
            //百位
            int l = i / 100;
            //判断
            if (i == (j * j * j) + (k * k * k) + (l * l * l)) {
                System.out.print(i + " ");
            }
        }
    }
}