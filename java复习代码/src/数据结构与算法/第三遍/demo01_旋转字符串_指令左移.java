package 数据结构与算法.第三遍;

public class demo01_旋转字符串_指令左移 {
    public String LeftRotateString(String str, int n) {
        char[] arr = str.toCharArray();
        int len = arr.length;
        //先分别旋转每一段
        reverse(arr,0,n-1);
        reverse(arr,n,len-1);
        //再整体旋转
        reverse(arr,0,len-1);
        String res_str = new String(arr);
        return res_str;
    }
//旋转一段
    private void reverse(char[] arr, int i, int j) {
        while (i<j){
            //一前一后交换
            char temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
            //处理下一个
            i++;
            j--;
        }
    }

}
