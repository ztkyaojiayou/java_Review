package 数据结构与算法.剑指offer题解.回溯与递归;

import java.util.*;

/**
 * 题目描述
 * 输入一个字符串,按字典序打印出该字符串中字符的所有排列。
 * 例如输入字符串abc,则打印出由字符a,b,c所能排列出来的所有字符串abc,acb,bac,bca,cab和cba。
 *
 * 输入描述:
 * 输入一个字符串,长度不超过9(可能有字符重复),字符只包括大小写字母。
 */

/**
 * 1、递归算法
 *
 * 解析：http://www.cnblogs.com/cxjchen/p/3932949.html  (感谢该文作者！)
 *
 * 对于无重复值的情况
 *
 * 固定第一个字符，递归取得首位后面的各种字符串组合；
 * 再把第一个字符与后面每一个字符交换，并同样递归获得首位后面的字符串组合；
 * *递归的出口，就是只剩一个字符的时候，递归的循环过程，就是从每个子串的第二个字符开始依次与第一个字符交换，然后继续处理子串。
 *
 * 假如有重复值呢？
 * *由于全排列就是从第一个数字起，每个数分别与它后面的数字交换，
 * 我们先尝试加个这样的判断——如果一个数与后面的数字相同那么这两个数就不交换了。
 * 例如abb，第一个数与后面两个数交换得bab，bba。然后abb中第二个数和第三个数相同，就不用交换了。
 * 但是对bab，第二个数和第三个数不 同，则需要交换，得到bba。
 * 由于这里的bba和开始第一个数与第三个数交换的结果相同了，因此这个方法不行。
 *
 * 换种思维，对abb，第一个数a与第二个数b交换得到bab，然后考虑第一个数与第三个数交换，此时由于第三个数等于第二个数，
 * 所以第一个数就不再用与第三个数交换了。再考虑bab，它的第二个数与第三个数交换可以解决bba。此时全排列生成完毕！
 *
 */

/**
 * 解析2：递归法，问题转换为先固定第一个字符，求剩余字符的排列；求剩余字符排列时跟原问题一样。
 * (1) 遍历出所有可能出现在第一个位置的字符（即：依次将第一个字符同后面所有字符交换）；
 * (2) 固定第一个字符，求后面字符的排列（即：在第1步的遍历过程中，插入递归进行实现）。
 * 需要注意的几点：
 * (1) 先确定递归结束的条件，例如本题中可设begin == str.size() - 1;
 * (2) 形如 aba 或 aa 等特殊测试用例的情况，vector在进行push_back时是不考虑重复情况的，需要自行控制；
 * (3) 输出的排列可能不是按字典顺序排列的，可能导致无法完全通过测试用例，考虑输出前排序，或者递归之后取消复位操作。
 */
class 字符串的全排列42 {
    public ArrayList<String> Permutation(String str) {
        ArrayList<String> res=new ArrayList<String>();
        if(str.length()==0||str==null)return res;
        //int n= str.length();
        helper(res,0,str.toCharArray());
        Collections.sort(res);
        return res;

    }
    public void helper( ArrayList<String> res,int index,char[] s) {
        if(index==s.length-1)
            res.add(new String(s));

        for(int i=index;i<s.length;i++) {
            // 第一次循环i与index相等，相当于第一个位置自身交换，关键在于之后的循环，
            // 之后i != index，则会交换两个不同位置上的字符，直到index==str.size()-1，进行输出；
            if(i==index||s[index]!=s[i])
            {
                swap(s,index,i);
                helper(res,index+1,s);
                swap(s,index,i);//复位，用以恢复之前字符串顺序，达到第一位依次跟其他位交换的目的
            }
        }
    }

    public void swap(char[]t,int i,int j) {
        char c=t[i];
        t[i]=t[j];
        t[j]=c;
    }
}

/**
 * 改进版：使用hashSet解决重复的问题，提升效率
 */
class Solution42_2 {
    public ArrayList<String> Permutation02(String str){
//结果集
        ArrayList<String> list = new ArrayList<String>();
        //开始递归
        if(str!=null && str.length()>0){
            PermutationHelper(str.toCharArray(),0,list);
            Collections.sort(list);//对最终的结果排序
        }
        return list;
    }

    private void PermutationHelper(char[] chars,int i,ArrayList<String> list){
        if(i == chars.length-1){
            //list.add(String.valueOf(chars));
            list.add(new String(chars));
        }else{
            Set<Character> charSet = new HashSet<Character>();
            for(int j=i;j<chars.length;++j){
                if(j==i || !charSet.contains(chars[j])){//自己先和自己也交换，对于其他情况，先看hashSet
                    // 是否包含该字符，只有当不包含时才进行交换，同时把该字符添加进hashSet中
                    charSet.add(chars[j]);
                    swap(chars,i,j);
                    PermutationHelper(chars,i+1,list);//再固定第i+1个位置，让其与其他字符全部交换，以此类推，直到最后一个位置交换完毕
                    swap(chars,j,i);//再撤销/复位，即用以恢复之前字符串顺序，达到第一位依次跟其他位交换的目的。
                }
            }
        }
    }

    //交换两个字符的位置
    private void swap(char[] cs,int i,int j){
        char temp = cs[i];
        cs[i] = cs[j];
        cs[j] = temp;
    }
}
