package 数据结构与算法.第三遍;

public class demo01_1旋转字符串_指令左移 {
    public String LeftRotateString(String str, int n) {
        char[] arr = str.toCharArray();
        int len = arr.length;
        //先分别旋转每一段
        reverse(arr, 0, n - 1);
        reverse(arr, n, len - 1);
        //再整体旋转
        reverse(arr, 0, len - 1);
        String res_str = new String(arr);
        return res_str;
    }

    //旋转一段（就是在原字符数组上旋转）
    private void reverse(char[] arr, int i, int j) {
        while (i < j) {
            //一前一后交换
            char temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
            //处理下一个
            i++;
            j--;
        }
    }

    /**
     * 第四遍
     * @param str
     * @param n
     * @return
     */
    public String LeftRotateString02(String str, int n) {
        char[] chars = str.toCharArray();
        int length = chars.length;
        reverse02(chars,0,n-1);
        reverse02(chars,n,length-1);
        reverse02(chars,0,length-1);
        String res = new String(chars);
        return res;
    }

    private void reverse02(char[] arr, int i, int j) {
        while (i<j){
             char temp = arr[i];
             arr[i] = arr[j];
             arr[j] = temp;
        }
        i++;
        j--;
    }
}
