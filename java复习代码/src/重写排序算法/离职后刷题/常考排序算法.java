package 重写排序算法.离职后刷题;

public class 常考排序算法 {
    //优化版本：
    //1）只比较无序部分，有序部分不用再比较
    //2）使用标志位判断是否已有序，若有序直接跳出该循环，减少了不必要的比较次数
    public int[] 冒泡排序(int[] arr) {
        //特判
        if (arr == null || arr.length == 0) {
            return arr;
        }
        int lastChangeIndex = 0;//最后交换的位置
        int sortBorder = arr.length - 1;//无序数列的边界，每次比较只需要比较到这里即可
        boolean flag = false;//本趟排序是否发生交换的标识
        for (int i = 0; i < arr.length; i++) {//表示要比较多少次，即（长度）次
            for (int j = 0; j < sortBorder; j++) {//每次只比较无序序列
                if (arr[j + 1] < arr[j]) {//真正比较是用j来比较，若后一个数比前一个数还小，则交换
                    int temp = arr[j + 1];
                    arr[j + 1] = arr[i];
                    arr[j] = temp;
                    //更新最后交换的位置，作为下一趟比较的终点（因为之后为有序序列，无需比较）
                    lastChangeIndex = j;
                    flag = true;//若交换了，则将该标识设为true
                }
            }
            //第二趟比较之前
            sortBorder = lastChangeIndex;//更新最后一次交换的位置
            //同样地，若这一趟比较下下来都没有进行交换，就说明已经有序，
            //则可直接跳出该循环，减少了不必要的比较次数
            if (flag == false) {
                break;
            }
        }
        //最终返回比较完毕的数组
        return arr;
    }

    public int[] 冒泡排序2(int[] arr) {
        //特判
        if (arr == null || arr.length == 0) {
            return arr;
        }
        int lastIndex = 0;
        int wuXuIndex = arr.length - 1;
        boolean flag = false;
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < wuXuIndex; j++) {
                if (arr[j + 1] < arr[j]) {
                    int temp = arr[j + 1];
                    arr[j + 1] = arr[j];
                    arr[j] = temp;
                    lastIndex = j;
                    flag = true;
                }
                //两件事
                wuXuIndex = lastIndex;
                if (!flag) {
                    break;
                }
            }
        }
        return arr;
    }



}
