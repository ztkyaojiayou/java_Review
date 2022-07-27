package 华为od机考练习;

/**
 * @author :zoutongkun
 * @date :2022/7/24 10:40 下午
 * @description :
 * @modyified By:
 */
public class Test {
    public static void main(String[] args) {
//        int num = 18;
//        String str16 = Integer.toHexString(num);
//        System.out.println(str16);

        char c = '6';//对应的asc码为54
        char d = '2';//对应的asc码为50
        //这是char相加，也即对应的asc码相加
        int res = c + d;//104
        char res2 = (char) (c + d);//asc码为104对应的字符--h
        int res3 = (c - '0') + (d - '0');//8(这才是我们期待的结果！！！）
        System.out.println(res);
        System.out.println(res2);
        System.out.println(res3);
    }
}
