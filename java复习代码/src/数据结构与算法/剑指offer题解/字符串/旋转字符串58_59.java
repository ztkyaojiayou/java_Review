package 数据结构与算法.剑指offer题解.字符串;

/**
 * （题58）题目描述
 * 汇编语言中有一种移位指令叫做循环左移（ROL），
 * 现在有个简单的任务，就是用字符串模拟这个指令的运算结果。
 * 对于一个给定的字符序列S，请你把其循环左移K位后的序列输出。
 * 例如，字符序列S=”abcXYZdef”,要求输出循环左移3位后的结果，即“XYZdefabc”。
 * 是不是很简单？OK，搞定它！
 */

/**
 * 思路解析：
 * 方法1：使用StringBuffer的截取字符的方法substring即可（似乎无任何技术含量，不推荐）
 * 方法2：先将 "abc" 和 "XYZdef" 分别翻转，得到 "cbafedZYX"，然后再把整个字符串翻转得到 "XYZdefabc"。（推荐）
 */

/**
 * 方法2：先将 "abc" 和 "XYZdef" 分别翻转，得到 "cbafedZYX"，然后再把整个字符串翻转得到 "XYZdefabc"。（推荐）
 */
class 左旋转字符串58_2 {
    public String LeftRotateString(String str, int n) {
        //0.特判
        if (n >= str.length())
            return str;
        //1.将字符串转化为字符数组，便于处理
        char[] chars = str.toCharArray();
        //2.先将前n个字符翻转
        reverse(chars, 0, n - 1);
        //3.再将后面的所有字符翻转
        reverse(chars, n, chars.length - 1);
        //4.最后，再将通过上述两步翻转之后的字符数组整体再反转一次即为所求
        reverse(chars, 0, chars.length - 1);
        //5.再将其转回字符串并返回即可
        return new String(chars);
    }

    //用于反转字符数组中i到j的字符的方法
    private void reverse(char[] chars, int i, int j) {
        while (i < j){
            swap(chars, i, j);//即从两头开始一一交换即可
            i++;
            j--;
        }
        // 简介写法
        // while (i < j)
        //swap(chars, i++, j--);
    }

    //用于交换字符数组中i和j位置处的字符的方法
    private void swap(char[] chars, int i, int j) {
        char t = chars[i];
        chars[i] = chars[j];
        chars[j] = t;
    }
}


/**
 * 方法1：使用StringBuffer的截取字符的方法substring即可（似乎无任何技术含量，不推荐）
 */
class Solution58_1 {
    public String LeftRotateString(String str,int n) {
        //0.特判
        if(str.length()==0||n<0){
            return str;
        }
        //1.利用StringBuffer的截取字符的方法substring即可
        StringBuffer sb=new StringBuffer(str.substring(0,n));//1.1先截取原字符串str的前n个字符
        StringBuffer sb1=new StringBuffer(str.substring(n,str.length()));//2.再截取原字符串str后面的字符
        sb1.append(sb);//3.接着再把前n个字符拼接在剩余的字符串后面即可
        //4.最后将其转化为字符串，再作为结果返回即可
        return sb1.toString();
    }
}


/**
 * （题59）题目描述
 * 牛客最近来了一个新员工Fish，每天早晨总是会拿着一本英文杂志，写些句子在本子上。
 * 同事Cat对Fish写的内容颇感兴趣，有一天他向Fish借来翻看，但却读不懂它的意思。
 * 例如，“student. a am I”。后来才意识到，这家伙原来把句子单词的顺序翻转了，
 * 正确的句子应该是“I am a student.”。Cat对一一的翻转这些单词顺序可不在行，你能帮助他么？
 */

/**
 * 思路解析：整体和上一题的思路一模一样，对于本题，只需先旋转每个单词，再旋转整个字符串即可。
 * 题目应该有一个隐含条件，就是不能用额外的空间。
 * 虽然 Java 的题目输入参数为 String 类型，需要先创建一个字符数组使得空间复杂度为 O(N)，
 * 但是正确的参数类型应该和原书一样，为字符数组，并且只能使用该字符数组的空间。
 * 任何使用了额外空间的解法在面试时都会大打折扣，包括递归解法。
 *
 * 正确的解法应该是和书上一样，先旋转每个单词，再旋转整个字符串。
 */
class 翻转单词顺序列59 {
    public String ReverseSentence(String str) {
        //1.将字符串转化为字符数组
        int n = str.length();//字符串的长度
        char[] chars = str.toCharArray();
        int i = 0, j = 0;
        //2.先旋转每个单词
        while (j <= n) {
            if (j == n || chars[j] == ' ') {//当该字符串没空格或遇到空格时，就说明这个一个单独的字符串，就反转
                reverse(chars, i, j - 1);
                i = j + 1;
            }
            j++;
        }
        //3.再反转整个字符串
        reverse(chars, 0, n - 1);
        //4.最后，返回该反转后的整个字符串即可
        return new String(chars);
    }

    //同题58，即反转字符数组中i到j的字符
    private void reverse(char[] c, int i, int j) {
        while (i < j)
            swap(c, i++, j--);
    }

    private void swap(char[] c, int i, int j) {
        char t = c[i];
        c[i] = c[j];
        c[j] = t;
    }
}