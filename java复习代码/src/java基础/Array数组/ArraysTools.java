package java基础.Array数组;

import java.util.Arrays;
import java.util.List;


//Arrays工具类：用于数组操作
public class ArraysTools {
    public static void main(String[] args) {

        String[] arr = {"d","c","b","a","f"};
        int[] arr02 = {1,9,3,7,6,8};

        //1.ToString：打印数组
        System.out.println("原数组arr为" + Arrays.toString(arr));//[d, c, b, a, f]

        //2.asList：数组变集合
        List<String> arrTolist = Arrays.asList(arr);
        System.out.println("转变过来的集合arrTolist为" + arrTolist.toString());//[d, c, b, a, f]

        //3.toArray：集合变数组
        //new String[arrTolist.size()]：创建一个字符数组，用于把转换过来的值存入其中
        String[] array = arrTolist.toArray(new String[arrTolist.size()]);
        System.out.println("再转为数组array为：" + Arrays.toString(array));//[d, c, b, a, f]

        //4.sort：排序
        Arrays.sort(array);
        System.out.println("排序之后的数组array为：" + Arrays.toString(array));//[a, b, c, d, f]

        //5.binarySearch：二分查找,必须先手动使用sort方法进行排序（因为它自己本身不排序），否则返回的结果是不确定的
        //进行二分查找之前必须先排序
        Arrays.sort(arr);
        Arrays.sort(arr02);
        System.out.println("排序之后的数组arr为：" + Arrays.toString(arr));//[a, b, c, d, f]
        System.out.println("排序之后的数组arr02为：" + Arrays.toString(arr02));//[1, 3, 6, 7, 8, 9]
        int index03 = Arrays.binarySearch(arr,"a");
        int index031 = Arrays.binarySearch(arr,"z");

        int index04 = Arrays.binarySearch(arr02,9);
        int index041 = Arrays.binarySearch(arr02,19);

        System.out.println("a所在的位置为（正确）：" + index03);//0，正确
        System.out.println("z所在的位置为（正确）：" + index031);//-6，正确（因为不存在）

        System.out.println("9所在的位置为（正确）：" + index04);//5，正确
        System.out.println("19所在的位置为（正确）：" + index041);//-7，正确（因为不存在）

    }

}
