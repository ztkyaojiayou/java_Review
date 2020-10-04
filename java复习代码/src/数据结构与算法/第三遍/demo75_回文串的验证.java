package 数据结构与算法.第三遍;

public class demo75_回文串的验证 {
    //1.普通版：
    public boolean isPalindrome01(String s) {
        s = s.toLowerCase();
        char[] chars = s.toCharArray();
        int left = 0;
        int right = chars.length-1;
        while (left < right){
            if (chars[left] >= '0' && chars[left] <= '9' || chars[left] >= 'a' && chars[left] <= 'z'){
                if (chars[right] >= '0' && chars[right] <= '9' || chars[right] >= 'a' && chars[right] <= 'z'){
                    if (chars[left] != chars[right]){
                        return false;
                    }else {
                        left++;
                        right--;
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

    //2.进阶版，可删除一个元素
    public boolean isPalindrome02(String s) {
        char[] chars = s.toCharArray();
        int left = 0;
        int right = chars.length-1;
        while (left < right){
            if (chars[left] != chars[right]){
                return method(chars,left+1,right) ||method(chars,left,right-1);
            }
            left++;
            right--;
        }
        return true;
    }

    private boolean method(char[] chars, int left, int right) {
        while (left < right){
            if (chars[left] != chars[right]){
                return false;
            }else {
                left++;
                right--;
            }
        }
        return true;
    }
}
