package 数据结构与算法.第二遍.回文问题;

public class 回文串的判断_入门版 {
    public boolean isHuiWenString(String s){
        String str = s.toLowerCase();
        char[] arr = str.toCharArray();
        //双指针
        int left = 0;
        int right = arr.length-1;
        while (left < right){
            //当为数字和字母时才比较（因为要跳过空格)
            if ((arr[left] >= '0' && arr[left] <= '9') || arr[left] >= 'a' && arr[left] <= 'z'){
                if ((arr[right] >= '0' && arr[right] <= '9') || arr[right] >= 'a' && arr[right] <= 'z'){
                    if (arr[left] != arr[right]){
                        return false;
                    }else {
                        left++;
                        right++;
                    }
                }else {
                    right--;
                }
            }else {
                left++;
            }
        }
        return true;
    }
}

class 进阶版_可删一个字符{
    public boolean isHuiWenString02(String s){
        char[] arr = s.toCharArray();
        int left = 0;
        int right = arr.length-1;
        while (left < right){
            if (arr[left] != arr[right]){//遇到字符不同就考虑两种情况，要么动前面，要么动后面，再比较即可
                return validate(arr,left+1,right) || validate(arr,left,right-1);
            }else {
                left++;
                right++;
            }
        }
        return true;
    }

    //判断当删除左边元素或右边元素时，剩下的字符串是否还是回文串
    private boolean validate(char[] arr, int left, int right) {
        while (left < right){
            if (arr[left] != arr[right]){
                return false;
            }else {
                left++;
                right--;
            }
        }
        return true;
    }
}
