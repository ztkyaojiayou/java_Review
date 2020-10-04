package 数据结构与算法.第三遍;

import com.sun.org.apache.bcel.internal.generic.IF_ACMPEQ;

public class demo49_贪心算法之剪绳子 {
    public int cutRope(int target) {
if (target < 2){
    return 0;
}
if (target == 2){
    return 1;
}
if (target == 3){
    return 2;
}

//尽量分为三段
        int three = target / 3;
//若还有剩余，且只剩余1时，就不要出现1的段，而把它分成2的端，即要从3的段中匀出一段来
        if (target - three * 3 == 1){
            three--;
        }
        //把剩下的数字变成两段
        int two = (target - 3 * three) / 2;
        return (int)Math.pow(3,three) * (int)Math.pow(2,two);
    }
}
