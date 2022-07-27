package 数据结构与算法.第三遍;

//要求：绳子长度大于1，至少切两段
public class demo49_1贪心算法之剪绳子 {
    public int cutRope(int target) {
        //特判
        if (target < 4) {//即2，3
            return target - 1;//浓缩之后的结论Hhhh
        }

        //尽量分为三段
        int three = target / 3;

        //若还有剩余，且只剩余1时(剩余2没关系），就不要出现1的段，
        //而把它分成2的端，即要从3的段中匀出一段来
        if (target - three * 3 == 1) {
            three--;
        }

        //把剩下的数字变成两段
        int two = (target - 3 * three) / 2;

        //再求结果
        return (int) Math.pow(3, three) * (int) Math.pow(2, two);
    }

    //自写一遍
    public int cutRope02(int target) {
        if (target < 4) {
            return target - 1;
        }

        //尽量分三段
        int threeTimes = target / 3;
        if (target - threeTimes * 3 == 1) {
            threeTimes--;
        }
        int twoTimes = (target - threeTimes * 3) / 2;
        return (int) (Math.pow(3, threeTimes) * Math.pow(2, twoTimes));

    }
}
