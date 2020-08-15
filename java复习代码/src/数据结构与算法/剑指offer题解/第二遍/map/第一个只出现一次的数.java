package 数据结构与算法.剑指offer题解.第二遍.map;

public class 第一个只出现一次的数 {
public int firstOf1(String str){
    int[] arr_count = new int[256];//使用一个数组计数，类似于map
    for (int i =0;i< str.length();i++){
        arr_count[str.charAt(i)]++;//即把i处的字符的ASC码的值作为下标存入数组中，其值设为1.
    }
    for (int i = 0;i< str.length();i++){
        if (arr_count[str.charAt(i) ] == 1){
            return i;
        }
    }
    return -1;
}
}
