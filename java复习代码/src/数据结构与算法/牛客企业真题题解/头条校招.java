package 数据结构与算法.牛客企业真题题解;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class 头条校招 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        if(n % 3 == 0){
            System.out.println(0);
        }else {
            System.out.println(3-n%3);
        }
    }
}
