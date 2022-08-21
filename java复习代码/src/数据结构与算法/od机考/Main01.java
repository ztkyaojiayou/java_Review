package 数据结构与算法.od机考;

import java.util.Scanner;

/**
 * @author :zoutongkun
 * @date :2022/7/31 9:46 上午
 * @description :
 * @modyified By:
 */
public class Main01 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        for (int i = 0;i<n;i++){
            String str = sc.next();
            int strLen = str.length();
            int[] arr = new int[Math.max(3,strLen)];
            arr[0] = 1;
            arr[1] = 2;
            arr[2] = 4;
            if(strLen > 3){
                for (int k = 3; k < strLen; k++) {
                    arr[k] = arr[k - 1] + arr[k - 2] + arr[k - 3];
                }
            }

            String newStr = "";
            char[] chars = str.toCharArray();
            for (int j = 0; j < strLen; j++) {
                int temp = (int) (arr[j] + chars[j]);
                char offsetChar = 'a';
                if (temp >'z'){
                    int offset = temp - 'z';//
                    offsetChar = (char) ('a' + offset % 26 - 1);
                }else {
                    offsetChar = (char) temp;
                }
                newStr += offsetChar;
            }
            System.out.println(newStr);
        }
    }
}
