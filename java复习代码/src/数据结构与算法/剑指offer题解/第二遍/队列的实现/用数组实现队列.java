package 数据结构与算法.剑指offer题解.第二遍.队列的实现;

//先入先出
public class 用数组实现队列 {
        //定义成员变量
        private int[] nums;//用数组来存储元素，代表队列
        private int count;//当前数组中元素的个数（也即当前栈中元素的个数）
        private int n;//数组的大小（也即队列的大小）
        //构造器
        public 用数组实现队列(int n){
            this.nums = new int[n];
            this.count = 0;
            this.n= n;
        }

        //入队
        public boolean add(int num){
            if (count == n){
                return false;
            }
            nums[count] = num;
            count++;
            return true;
        }

        //出队
        public int poll(){
            int i=0;//用于出队
            if (count == 0){
                throw new RuntimeException("队列 is empty");
            }
            int res = nums[i];
            i++;
            return res;
        }
}
