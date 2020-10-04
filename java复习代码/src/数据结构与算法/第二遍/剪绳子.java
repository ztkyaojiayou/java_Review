package 数据结构与算法.第二遍;

//使用贪心算法即可
public class 剪绳子 {
    public int cutRope(int num){
        //特判
        if(num < 2){
            return 0;
        }
        if (num ==2){
            return 1;
        }
        if (num == 3){
            return 2;
        }

        //使用贪心算法，尽量切成3的倍数，若不行，就将多出的绳子切成2的倍数即可
        int timesOf3 = num/3;
        if (num - timesOf3 == 1){
            timesOf3--;
        }
        int timesOf2 = (num - timesOf3 * 3)/2;
        //返回结果即可
        return (int) (Math.pow(3,timesOf3)) * (int)(Math.pow(2,timesOf2));
    }
}
