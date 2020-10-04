package 秋招笔试.其他杂七杂八;

import java.util.Scanner;

public class test02 {
    public static void main(String[] args) {
        int n,m,temp,cur,mid;
        boolean flag;
        Scanner scanner = new Scanner(System.in);
        n=scanner.nextInt();
        m=scanner.nextInt();
        int left=1,right=m;
        while(left!=right) {
            flag=true;
            mid=(left+right+1)/2;
            temp=m;
            cur=mid;
            for(int j=0; j<n; j++) {
                if(temp<cur) {
                    flag=false;
                    break;
                }
                temp-=cur;
                cur=(cur+1)/2;
            }
            if(flag) left=mid;
            else right=mid-1;
        }
        System.out.println(left);
    }
}
