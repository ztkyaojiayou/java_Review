package 华为od机考练习.常规题库;

/**
 * 80）整型数组合并--使用treeSet
 *
 * @author :zoutongkun
 * @date :2022/7/27 10:34 下午
 * @description :
 * @modyified By:
 **/
import java.util.*;

/**
 审题：数组升序合并、过滤重复元素 (很明显这是考察 TreeSet 的用法)
 TreeSet 的性质：有序、不重复
 **/
public class Main80 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            Set<Long> set = new TreeSet<>();
            //接收第一个整形数组大小
            int size1 = sc.nextInt();
            for (int i = 0; i < size1; i++) {
                set.add(sc.nextLong()); //将该组的整数按数组大小循环添加进 set
            }
            //接收第一个整形数组大小
            int size2 = sc.nextInt();
            for (int i = 0; i < size2; i++) {
                set.add(sc.nextLong());
            }
            //遍历输出
            for (long n : set) {
                System.out.print(n);
            }
          /*
          注意：测试案例会以 两个整形数组 为一组测试用例， 并可能输入多组
          要记得组与组的结果之间换行
          */
            System.out.println();
        }

    }
}

