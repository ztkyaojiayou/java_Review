package 秋招笔试.其他杂七杂八;

import java.math.BigDecimal;

public class 搜狗test02 {
   public int getHouse(int t,int[] xa){
       if (xa == null ||xa.length == 0){
           return 0;
       }
       double t1 = t;
       int count = 2;
       double right_index1 = 0,left_index2 = 0;
       for (int i = 0;i<xa.length-4;i+=2){
               right_index1 = xa[i] + xa[i+1]/2;
               left_index2 = xa[i+2] - xa[i+3]/2;
         double dis = sub(left_index2,right_index1);
           if (dis > t1){
               count+= 2;
           }else if (dis == t1){
               count+=1;
           }
       }
       return count;
   }

    public static double sub(double v1, double v2){
        BigDecimal b1 = BigDecimal.valueOf(v1);
        BigDecimal b2 = BigDecimal.valueOf(v2);
        return b1.subtract(b2).doubleValue();
    }
}
