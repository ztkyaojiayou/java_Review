package 数据结构与算法.od机考.华为od机考练习;

/**
 * 关于String中的lastIndexOf和indexOf方法
 *
 * @author :zoutongkun
 * @date :2022/7/30 1:08 下午
 * @description :
 * @modyified By:
 */
public class Test02 {
    public static void main(String[] args) {
        String str = "1000011100001";
        int i = str.indexOf("0");
        int j = str.lastIndexOf("1");
        System.out.println(i + " " + j);
    }
}
