package 秋招笔试.去哪儿;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class test01 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int a = sc.nextInt();
        int b = sc.nextInt();
        System.out.println(comb(a,b));
    }

    static Map<String,Long> map= new HashMap<String, Long>();
    private static long comb(int m,int n){
        String key= m+","+n;
        if(n==0)
            return 1;
        if (n==1)
            return m;
        if(n>m/2)
            return comb(m,m-n);
        if(n>1){
            if(!map.containsKey(key))
                map.put(key, comb(m-1,n-1)+comb(m-1,n));
            return map.get(key);
        }
        return -1;
    }
}
