package 秋招笔试.其他杂七杂八;

import java.util.HashMap;
import java.util.Scanner;

public class 爬楼梯 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int res = 爬楼梯.method(n);
        System.out.println(res);
    }
public static int method(int n){

    if (n > 36 || n < 0){
        return 0;
    }
    if (n == 1 || n == 2){
        return n;
    }
    int res = method(n - 1) + method(n - 2);
    return  res;
}

    public static int method(int[] person) {
        if(person==null) {
            return 0;
        }
        if(person.length==1) {
            return 1;
        }
        HashMap<Integer,Integer> map = new HashMap<Integer,Integer>();
        int sum = 0;
        int minIndex = 0;
        for(int i=1;i<person.length;i++) {
            if(person[minIndex]>person[i]) {
                minIndex = i;
            }
        }
        map.put(minIndex, 1);
        int low = minIndex-1;
        int high = minIndex+1;
        while(low>=0) {
            if(person[low]>person[low+1]) {
                map.put(low, map.get(low+1)+1);
            }else if(person[low]==person[low+1]) {
//    			map.put(low, map.get(low+1));
                map.put(low, 1);
            }else {
                map.put(low, 1);
            }
            low--;
        }
        while(high<person.length) {
            if(person[high]>person[high-1]) {
                map.put(high, map.get(high-1)+1);
            }else {
                map.put(high, 1);
            }
            high++;
        }
        for(int i=0;i<person.length;i++) {
            sum = sum + map.get(i);
        }
        return sum;
    }
    }


