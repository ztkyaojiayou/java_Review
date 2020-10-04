package 秋招笔试.欧克云链;

public class test02 {
    public boolean isPowerOfTwo (int n) {
        if (n < 0){
            n = Math.abs(n);
        }
        if (n == 0){
            return true;
        }
        if (0 == (n & (n - 1))) {
            return true;
        }else {
            return false;
        }
        //return (n > 0 && (0 == (n & (n - 1))));
    }
}

