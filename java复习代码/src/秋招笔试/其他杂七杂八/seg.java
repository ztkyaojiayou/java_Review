package 秋招笔试.其他杂七杂八;

import java.util.Scanner;

class Solution {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int d = sc.nextInt();
        int n = sc.nextInt();
        int[] arr = new int[n];
        for (int i = 0;i<arr.length;i++){
            int a = sc.nextInt();
            arr[i] = a;
        }
        int res = Solution.method(arr, d);
        System.out.println(res);
    }

    public static int method(int[] arr, int d) {
        if (arr == null || arr.length == 0 || d == 0){
            return 0;
        }
        int sum01 = 0;
        for (int i = 0;i<arr.length;i++){

            sum01 += arr[i];
        }
        if (sum01 == 0){
            return 0;
        }
        int len = arr.length, sum = 0, ans = 0;
        int[] map = new int[d];
        map[0] = 1;
        for (int i = 1; i <= len; i++) {
            sum = sum + arr[i-1];
            int key = (sum % d + d) % d;
            ans += map[key];
            map[key]++;
        }
        return ans;
    }

    //    public static int method(int[] A, int K) {
    //        HashMap<Integer, Integer> hash = new HashMap<Integer, Integer>();
    //        hash.put(0, 1);
    //        int res = 0;
    //        int s = 0;
    //        for(int i=0; i<A.length; i++){
    //            s += A[i];
    //            int mod = Math.floorMod(s, K);
    //            if(hash.containsKey(mod)){
    //                res += hash.get(mod);
    //                hash.put(mod, hash.get(mod)+1);
    //            }else{
    //                hash.put(mod, 1);
    //            }
    //        }
    //        return res;
    //    }
    //
    //    public static int method(int[] A, int K) {
    //        //键-->前缀和%K，值--> 计数
    //        Map<Integer,Integer> record = new HashMap<>();
    //        record.put(0,1);
    //        int sum = 0;//前缀和
    //        int res = 0;
    //        for(int elem:A){
    //            sum += elem;
    //            int rem = (sum % K + K) % K;
    //            int value = record.getOrDefault(rem,0);
    //            res += value; // 如果前面有一样的余数，那么就加到结果中
    //            record.put(rem,value+1);
    //        }
    //        return res;
    //    }
    }

