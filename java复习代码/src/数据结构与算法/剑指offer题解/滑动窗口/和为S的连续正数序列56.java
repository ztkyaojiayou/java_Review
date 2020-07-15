package 数据结构与算法.剑指offer题解.滑动窗口;

import java.util.ArrayList;

/**
 * 题目描述
 * 小明很喜欢数学,有一天他在做数学作业时,要求计算出9~16的和,他马上就写出了正确答案是100。
 * 但是他并不满足于此,他在想究竟有多少种连续的正数序列的和为100(至少包括两个数)。
 * 没多久,他就得到另一组连续正数和为100的序列:18,19,20,21,22。
 * 现在把问题交给你,你能不能也很快的找出所有和为S的连续正数序列? Good Luck!
 *
 * 输出描述:
 * 输出所有和为S的连续正数序列。
 * 序列内按照从小至大的顺序，序列间按照开始数字从小到大的顺序
 */

/**
 * 思路解析：滑动窗口
 *
 * 滑动窗口的操作
 * 扩大窗口，end += 1
 * 缩小窗口，start += 1
 * 算法步骤：
 * 初始化，i=1,j=2, 表示窗口大小为0
 * 如果窗口中值的和小于目标值sum， 表示需要扩大窗口，j += 1
 * 否则，如果狂口值和大于目标值sum，表示需要缩小窗口，i += 1
 * 否则，等于目标值，存结果，缩小窗口，继续进行步骤2,3,4
 */
class 和为S的连续正数序列56_1 {
    public ArrayList<ArrayList<Integer>> FindContinuousSequence(int sum) {
        //1.结果集和存储每一个结果的list
        ArrayList<ArrayList<Integer>> result = new ArrayList<>();
        ArrayList<Integer> list = new ArrayList<>();
        //2.特判
        if(sum<2) {
            return result;
        }
        //3.定义指针
        int start = 1, end = 2;//并不是一头一尾
        int curSum = 3;//当前和
        //4.开始滑动窗口，判断当前和与目标和的关系
        while (end < sum) {
            if (curSum > sum) {//4.1若当前和curSum > sum 时,则说明要缩小窗口，即start++，但在此之前要把原start值减去，而用新的start值代替。
                curSum -= start;
                start++;
            } else if (curSum < sum) {//4.2若当前和curSum < sum 时,则说明要扩大窗口，即end++，之后再把该新的end值加进去。
                end++;
                curSum += end;
            } else {//4.3而若当curSum == sum 时，说明找到了一组符合要求的解，此时只需把这个序列的所有元素添加进list，再把其加入到结果集中即可。
                for (int i = start; i <= end; i++) {//4.3.1先把该序列的元素加入list（指针是左闭右闭）
                    list.add(i);
                }
                result.add(list);//4.3.2再把该符合要求的list加入到ret中的（注意：是可以往list中添加list的，不要惊讶~）

                //4.4再开始寻找下一组
                //4.4.1即再把当前的start值减去，同时更新start
                curSum -= start;
                start++;
                //4.4.2再更新end，同时把end累加到当前和cur_Sum。
                end++;
                curSum += end;
            }
        }
        //5.最后，返回最终的结果即可
        return result;
    }
}

/**
 * 方法2：暴力解法
 * 算法步骤：
 * 用指针i枚举目标序列的左边界（可以从1开始，别看成数组就行），
 * 再固定左边界，用另一个指针j也从左边界开始遍历，一直累加，直到当前和等于目标值就终止，
 * 接着再往右移动一下左边界，继续重复上述过程，直到直到下一组符合要求的序列，以此类推即可。
 *
 * 注意：这里的i只需移动到sum/2即可，因为题目要求至少包含2个数，
 * 而当i = sum/2 时，则j也从该处开始，则若此时j再往右移的话，其和就已经大于sum了，不符合题意。
 */
class Solution56_2 {
    public ArrayList<ArrayList<Integer> > FindContinuousSequence(int sum) {
        //1.结果集和存储每一个结果的list
        ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();
        ArrayList<Integer> list = new ArrayList<Integer>();
        //2.特判
        if(sum<2) {
            return result;
        }
        //3.开始循环遍历
        for(int i=1;i<=sum/2;i++){//3.1固定i指针，只需移动到sum/2即可
            int cur_sum=0;
            for(int j=i;j<sum;j++){//3.2j指针往右移动，累加当前和，同时把当前元素加入到list中
                cur_sum+=j;
                list.add(j);
                if(cur_sum>sum)//3.2.1若大于目标和，则直接终止循环
                    break;
                else if(cur_sum==sum){//3.2.2若等于目标和，则说明该list符合要求，将其加入结果集，
                    //同时也要终止循环，开始下一组上的寻找
                    result.add(list);
                    break;
                }
            }
        }

        //4.最后，返回最终的结果即可
        return result;
    }
}