package 重写排序算法.第二遍;

import java.util.ArrayList;
import java.util.List;

public class 桶排序test {
        public static void main(String[] args) {
            int a[]= {1,8,7,44,42,46,38,34,33,17,15,16,27,28,24};
            List[] buckets=new ArrayList[5];
            for(int i=0;i<buckets.length;i++)//初始化
            {
                buckets[i]=new ArrayList<Integer>();
            }
            for(int i=0;i<a.length;i++)//将待排序序列放入对应桶中
            {
                int index=a[i]/10;//对应的桶号
                buckets[index].add(a[i]);
            }
            for(int i=0;i<buckets.length;i++)//每个桶内进行排序(使用系统自带快排)
            {
                buckets[i].sort(null);
                for(int j=0;j<buckets[i].size();j++)//顺便打印输出
                {
                    System.out.print(buckets[i].get(j)+" ");
                }
            }
        }
    }
