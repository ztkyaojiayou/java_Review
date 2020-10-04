package 数据结构与算法.第二遍.位运算;

public class 数组中只出现一次的两个数 {
   public void find2Once(int[] arr,int[] num1,int[] num2){
       int res = 0;
       for (int i=0;i<arr.length;i++){
           res = res ^ arr[i];
       }

       //开始分组
       //先找到分组的分界线,即res的二进制位中的倒数第几位为1
       int index = 1;
       while ((res & index) == 0){
           index = index << 1;
       }
       //再对两组分别进行亦或运算，其结果即为所求。
       for (int i = 0;i< arr.length;i++){
           if ((index & arr[i]) == 0){
               num1[0] = num1[0] ^ arr[i];
           }else {
               num2[0] = num2[0] ^ arr[i];
           }
       }
   }
}
