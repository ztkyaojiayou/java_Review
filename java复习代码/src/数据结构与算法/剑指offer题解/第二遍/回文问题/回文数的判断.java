package 数据结构与算法.剑指offer题解.第二遍.回文问题;

public class 回文数的判断 {
    public boolean isHuiWenShu(int x){
        if (x < 0){
            return false;
        }
        int num = x;
        int cur = 0;
        while (num != 0){
            cur = cur * 10 + num % 10;
            num = num/10;
        }
        return num == x;
    }

}

/**
 * 方法3：首尾双指针（其实是很好的方法）
 *     解题思路:
 *         数值转成字符串，遍历元素
 *         首尾各一个指针，向中间靠拢，每次元素是否相等
 *     复杂度分析:
 *         时间复杂度 O(n/2) ，N 表示数值包含元素长度 ，如果数值是回文数 最多需要遍历 O(n/2) 次
 *         时间复杂度 O(1)
 */
class Solution09_2 {
    public boolean isPalindrome(int x) {
        if(x<0){ // 如果为负数，直接返回 false
            return false;
        }
        //1.先利用valueOf将数字转化为字符串（并不是数组）
        String strNum = String.valueOf(x);

        //2.再再使用双指针比较即可
        for(int i = 0;i<=strNum.length()/2;i++) {//遍历前半段即可，尾指针可以由首指针表示
            int a = strNum.charAt(i);//首指针
            int b = strNum.charAt(strNum.length()-1-i);//尾指针
            if(a != b) {//再看其对应的值是否相等
                return false;
            }
        }
        return true;

    }
}
