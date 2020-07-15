package 数据结构与算法.联发科极限编程大赛;

import java.util.Scanner;

public class 跑步 {
        public static void main(String[] args) {
            Scanner sc=new Scanner(System.in);
            跑步 Solution = new 跑步();
            int t=sc.nextInt();
            int ans;
            for(int i=0;i<t;i++){
                int n=sc.nextInt();
                // 调用方法1
                ans = Solution.solution_2(n);
                // 调用方法2
                System.out.println(ans);
            }
        }
        /**
         * 暴力法
         */
        private int solution_1(int n){

            int N = 4*n;
            boolean[] mark=new boolean[N];
            // 首先把起点标记一下
            mark[0] = true;
            int m=0,ans=1;
            // 循环查找下一个标记的点
            while (mark[(m+n+1)%N] != true){
                m += n+1;
                m = m%N;
                mark[m]=true;
                ans++;
            }
            // 加上最后一次重复标记的点
            return ans + 1;
        }

        /**
         * 最大公约数的解法
         */
        private int solution_2(int n){
            int d=gcd(4*n, n+1);
            int ans=(4*n)/d;//该值其实也就是4n和n+1的最小公倍数
            //（也即，求一对数（a,b)的最小公倍数一般可以先求出其最大公约数c，再用a/c即可求出其最小公倍数）
            return ans + 1;
        }

        //求最大公约数
        public int gcd(int a, int b){
            int r;
            while(b != 0){//除数不能为0
                r = a % b;//余数，用于做下一下的除数b，即b = r(line55)
                a = b;//原除数做下一次的被除数
                b = r;
            }
            //当除数为0时，就可以结束该算法了，所求值就是除数为0时的被除数a
            return a;
        }

}
