package 数据结构与算法.离职后刷题;

/**
 * @author :zoutongkun
 * @date :2022/4/6 12:45 上午
 * @description :
 * @modyified By:
 */
public class TestArr {
    public static void main(String[] args) {
        //二维数组
        int[] intA[] = {{1, 2}, {2, 3, 4}, {3, 4, 5, 6}};
        //(1)普通for循环
        for (int i = 0; i < intA.length; i++) { //0,1,2
            for (int j = 0; j < intA[i].length; j++) { //每一个一维数组的长度
                System.out.print(intA[i][j] + "\t");
            }
            System.out.println();
        }
        //(2)加强for循环
        System.out.println("\n=========================");
        for (int[] arr : intA) {  //int[]二维数组中元素的类型, arr迭代变量, intA二维组的名称
            for (int i : arr) { //int，一维数组中元素的类型，i,迭代变量,arr，一维数组的名称
                System.out.print(i + "\t");
            }
            System.out.println();
        }
        //(3)普通与加强for循环的混搭
        System.out.println("\n-------------------------");
        for (int[] arr : intA) {//加强for
            for (int i = 0; i < arr.length; i++) {
                System.out.print(arr[i] + "\t");
            }
            System.out.println();
        }
        System.out.println("\n--------------------------");
        for (int i = 0; i < intA.length; i++) {
            for (int j : intA[i]) {//加强for
                System.out.print(j + "\t");
            }
            System.out.println();

        }
    }

}
