package 数据结构与算法.第三遍;

import java.util.Arrays;

public class demo49_3贪心算法之任务调度所需的最短时间 {
    public int leastInterval(char[] tasks, int n) {
        int[] map = new int[26];
        for (int i = 0;i<tasks.length;i++){
            map[tasks[i] - 'A']++;
        }
        Arrays.sort(map);//排序，找最大任务，易知此时肯定即为最后一个元素
        int maxCount = map[25];
        int retCount = (maxCount-1) * (n+1) + 1;//先排最大任务
        //再看是否需要多消耗时间
        int i = 24;
        while (i >= 0 && map[i] == maxCount){
            retCount++;
            i--;
        }
        return Math.max(retCount,tasks.length);
    }
}
