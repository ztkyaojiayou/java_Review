package 数据结构与算法.第三遍;

//先用暴力解法
public class demo72_暴力之从1到n整数中1出现的次数 {
    public int NumberOf1Between1AndN_Solution(int n) {
        int count = 0;
        for (int i = 0;i<n;i++){
            //把整数转化为字符串（常用，滴滴一面就用了）
            String s = String.valueOf(i);
            char[] chars = s.toCharArray();
            for (int j = 0;j<chars.length;j++){
                if (chars[j] == '1' ){
                    count++;
                }
            }
        }
        return count;
    }
}
