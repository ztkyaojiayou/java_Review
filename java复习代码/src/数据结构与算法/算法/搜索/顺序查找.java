package 数据结构与算法.算法.搜索;

/**
 * 四大经典查找算法之（1）顺序查找（也叫线性查找，最简单最直接，一般不考）
 * 方法：逐一比对，发现有相同值，就返回下标
 */
public class 顺序查找 {
    /**
     * 这里我们实现的线性查找是找到一个满足条件的值，就返回
     * @param arr：目标数组（不需要有序）
     * @param value：要找的目标值
     * @return
     */
    //1.代码实现
    public static int seqSearch(int[] arr, int value) {
        // 线性查找是逐一比对，发现有相同值，就返回下标
        for (int i = 0; i < arr.length; i++) {
            if(arr[i] == value) {
                return i;
            }
        }
        return -1;
    }

    //2.测试
    public static void main(String[] args) {
        int arr[] = { 1, 9, 11, -1, 34, 89 };// 没有顺序的数组
        int index = seqSearch(arr, -1);
        if(index == -1) {
            System.out.println("没有找到");
        } else {
            System.out.println("找到，下标为=" + index);
        }
    }

}

