package 数据结构与算法.算法.KMP;

import java.util.Arrays;

/**
 * 程序员必备的算法之（4）KMP字符串匹配算法（方法不难理解，但代码有点难写）
 *
 */
public class KMPAlgorithm {

    //1.编写出kmp查找算法
    /**
     * @param str1 源字符串
     * @param str2 子串
     * @param next 部分匹配表, 是子串对应的部分匹配表
     * @return 如果是-1就是没有匹配到，否则返回第一个匹配的位置
     */
    public static int kmpSearch(String str1, String str2, int[] next) {

        //遍历
        for(int i = 0, j = 0; i < str1.length(); i++) {//i看成是str1的指针，j是str2的指针

            //需要处理 str1.charAt(i) ！= str2.charAt(j), 去调整j的大小
            //KMP算法核心点, 可以验证...
            //注解：charAt() 方法是用于返回指定索引处的字符，索引范围为从 0 到 length() - 1。
            while( j > 0 && str1.charAt(i) != str2.charAt(j)) {
                j = next[j-1];//核心代码，没懂
            }

            if(str1.charAt(i) == str2.charAt(j)) {
                j++;
            }
            if(j == str2.length()) {//找到了 //对于当str2=BBC时， j = 3 ，i=2
                return i - j + 1;
            }
        }
        return  -1;
    }

    //1.1获取到一个字符串(子串) 的部分匹配值表方法
    public static  int[] kmpNext(String dest) {//dest即子字符串str2
        //创建一个next 数组保存部分匹配值
        int[] next = new int[dest.length()];
        next[0] = 0; //如果字符串是长度为1 部分匹配值就是0
        for(int i = 1, j = 0; i < dest.length(); i++) {
            //当dest.charAt(i) != dest.charAt(j) ，我们需要从next[j-1]获取新的j
            //直到我们发现 有  dest.charAt(i) == dest.charAt(j)成立才退出
            //这是kmp算法的核心点
            while(j > 0 && dest.charAt(i) != dest.charAt(j)) {
                j = next[j-1];//核心代码，没懂
            }

            //当dest.charAt(i) == dest.charAt(j) 满足时，部分匹配值则 +1
            if(dest.charAt(i) == dest.charAt(j)) {
                j++;
            }
            next[i] = j;
        }
        return next;
    }

    //2.测试
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        String str1 = "BBC ABCDAB ABCDABCDABDE";//被查父串
        String str2 = "ABCDABD";//待查子串
        //String str2 = "BBC";

        int[] next = kmpNext("ABCDABD"); //求待查字串的“部分匹配表”
        System.out.println("next=" + Arrays.toString(next));

        int index = kmpSearch(str1, str2, next);
        System.out.println("index=" + index); // 15了


    }
}

