package 秋招笔试.其他杂七杂八;

public class 字符串碎片 {
    public static void main(String[] args) {
        String s = null;
        System.out.println(s.length());//空指针
    }
    public int solution(String str){
        if (str == null){
            return 0;
        }
        int res = 0;
        char[] arr = str.toCharArray();
        int count = 1;
        int count1 = 1;
        for (int i = 0;i<arr.length-1;i++){
            if (arr[i] == arr[i+1]){
                count++;
            }else {
               count1++;
            }
        }
        res = count / count1;
        return res;
    }

}
