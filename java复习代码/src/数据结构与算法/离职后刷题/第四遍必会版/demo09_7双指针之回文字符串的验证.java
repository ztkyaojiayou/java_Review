package 数据结构与算法.离职后刷题.第四遍必会版;

public class demo09_7双指针之回文字符串的验证 {
    /**
     * 1.普通版（简单）
     */
    public boolean isPalindrome01(String s) {
        //统一变成小写并转化为字符数组
        s = s.toLowerCase();
        //其实可以不用转，使用charAt方法即可
        char[] chars = s.toCharArray();
        //双指针，一前一后
        int left = 0;
        int right = chars.length - 1;
        //开始前后一一比对
        while (left < right) {
            //这种判断有必要吗？？？当然，要是还有别的特殊字符串另说......
            if (chars[left] >= '0' && chars[left] <= '9' || chars[left] >= 'a' && chars[left] <= 'z') {//左边
                if (chars[right] >= '0' && chars[right] <= '9' || chars[right] >= 'a' && chars[right] <= 'z') {//右边
                    if (chars[left] != chars[right]) {
                        return false;
                    } else {
                        left++;
                        right--;
                    }
                } else {
                    right--;
                }
            } else {
                left++;
            }
        }
        return true;
    }

    /**
     * 2.进阶版，可删除一个元素（也要掌握）
     * 使用递归
     */
    public boolean isPalindrome02(String s) {
        char[] chars = s.toCharArray();
        int left = 0;
        int right = chars.length - 1;
        while (left < right) {
            //不相等时不是直接返回false，而是开始考虑删除一个元素后再判断
            if (chars[left] != chars[right]) {
                //递归比较
                //删除一个元素，那具体删除哪个呢？考虑从前后删除即可，然后对剩下的字符串进行判断
                return method(chars, left + 1, right) || method(chars, left, right - 1);
            }
            left++;
            right--;
        }
        return true;
    }

    private boolean method(char[] chars, int left, int right) {
        while (left < right) {
            if (chars[left] != chars[right]) {
                return false;
            } else {
                left++;
                right--;
            }
        }
        return true;
    }

    //自写一遍
    public boolean isPalindrome03(String s) {
        int left = 0;
        int right = s.length() - 1;
        while (left < right) {
            if (s.charAt(left) != s.charAt(right)) {
                return method02(s, left + 1, right) || method02(s, left, right - 1);
            }
            left++;
            right--;
        }
        return true;
    }

    private boolean method02(String s, int left, int right) {
        while (left < right) {
            if (s.charAt(left) != s.charAt(right)) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }
}

/**
 * 3.回文数的判断（简单，主要是先要转化为字符串！！！）
 */
class 回文数的判断 {
    public boolean isPalindrome(int x) {
        if (x < 0) { // 如果为负数，直接返回 false
            return false;
        }
        //1.先利用valueOf将数字转化为字符串(常用）
        String strNum = String.valueOf(x);
        int len = strNum.length();
        //2.再再使用双指针比较即可
        for (int i = 0; i <= len / 2; i++) {//遍历前半段即可，尾指针可以由首指针表示
            int a = strNum.charAt(i);//首指针
            int b = strNum.charAt(len - 1 - i);//尾指针
            if (a != b) {//再看其对应的值是否相等
                return false;
            }
        }
        return true;

    }
}
