package java基础.Array数组;

public class test10array遍历 {
        //遍历数组的三种方法：
        public static void main(String[] args) {
            //1、方法一：使用for循环
            int[] arr = {12, 4, 1, 66, 54, 6, 74, -3};
            for (int i = 0; i < arr.length; i++) {
                System.out.print(arr[i] + ",");//输出为：12,4,1,66,54,6,74,-3,
            }
            System.out.println("\n----------换行------------");
            //2、方法二：使用foreach循环
            for (int i : arr) {
                System.out.print(i + ",");//输出为：12,4,1,66,54,6,74,-3,
            }
            System.out.println("\n----------换行------------");
            //3、方法三：使用Arrays类中的toString方法（当然已重写）
            //（注意，是Arrays不是Array，Arrays类位于java.util包下,类比Collection和Collections）
            System.out.println(java.util.Arrays.toString(arr));//输出为：[12, 4, 1, 66, 54, 6, 74, -3]

            //4、错误示范：直接打印arr，得到的是数组的首地址，而不能打印出数组的元素
            System.out.println(arr);//输出为：[I@610455d6
            System.out.print(arr);////输出也为：[I@610455d6
                }
        }


