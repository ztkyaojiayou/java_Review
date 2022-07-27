package 数据结构与算法.剑指offer第一版.纯数学问题;

/**
 * 题目描述
 * 求出1~13的整数中1出现的次数,并算出100~1300的整数中1出现的次数？
 * 为此他特别数了一下1~13中包含1的数字有1、10、11、12、13因此共出现6次,但是对于后面问题他就没辙了。
 * ACMer希望你们帮帮他,并把问题更加普遍化,可以很快的求出任意非负整数区间中1出现的次数（从1 到 n 中1出现的次数）。
 */

/**
 * 思路分析：数学方法找规律快速统计
 * 想要计算1到n这n个数字里面数字“1”出现的次数，我们不妨依次统计所有数位上面的出现次数。
 * 直观的看，在个位上，数字1每10个数出现一次，在十位上，平均每连续100个数里面出现10次（比如说1-100），
 * 在百位上，平均每1000个连续数出现100次（比如说1051-2050）……依次类推！
 * 那么我们在大的方向上需要对题目中传入的整数，即“int n”，进行一次次地除以10（取整或者取整加1，具体看其他条件）的操作，
 * 以表示，这个n里面包含了多少个数、多少个“连续10个数”、“多少个连续100个数”等等。
 * 比如说，n=2323的时候，我们知道在2323个数字里，在2320之前，出现了232个个位上的1，
 * 加上2321里面的那个个位上的1，一共是233个也就是2323/10+1个个位的数字1.进一步我们又可以得到，
 * 2323个数里面十位上面的1出现的次数出现了2323/100+1段，这个段数乘10就是十位出现1的次数，
 * 因为2323/100+1段数字当中，每一段里面有10个数字的十位数字是1……
 * 对于普通的数字，以此方法进行计算并求和
 * 但是却有特殊情况，当某一位是0或者1的时候：
 * 例如某一位遇到0，n=2320的时候，我们统计个位1出现的次数，
 * 直接2320/10就行，不用加1，因为2320个数里面不包含2321；
 * 其次，某一位遇到1，比方说，n=2313，我们统计十位的1的个数，
 * 需要统计1~2310-1（共计231个）以及2310 ~2313的1的个数(具体原因是，这里的十位1不是以整段整段出现的，
 * 需要手动把最后一段不完整的个数求出。)此方法代码在下面第二联，
 * 另外在判断除以10结果是否加1的那里，我对代码里面的情况进行了合并，
 * 运算优化了一些，代码得到了一些精简，上述代码可改为以下第三联：
 */
class 整数中1出现的次数_从1到n整数中1出现的次数46_1 {
    /*方法二，需要考虑n的每一位是0？1？还是大于1的数字？
     */
    public int NumberOf1Between1AndN_Solution(int n) {
            int ans=0;
            long pOf10=1;//注意不能int，会越界
            int m=n;//复制一个n，因为后面几轮循环后还会涉及到n的原始值
            while(m>0){
                //(关键）在个位上，数字1每10个数出现一次（如数字1-9的1只由数字1提供），
                //在十位上，平均每连续100个数里面出现10次（因为数字10-99的十位上的1是由10-19的十位上的1提供的（此时11只算十位上的1，即只算一次）），
                //在百位上，平均每1000个连续数出现100次（因为数字100-999的百位上的1是由100-199的百位上的1提供的（此时111也只算百位上的1，即只算一次））……依次类推！
                if(m%10==0){//个位上的数为0时，如70(%:取余)
                    ans+=m/10*pOf10;
                }
                else if(m%10>1){//个位上的数大于1时，如74，此时分为两段，即0-70和71-74 来计算，易知，后面那一小段也还有一个数71能提供一个1。
                    ans+=(m/10+1)*pOf10;
                }
                else{//此时m%10==1需要单独考虑，如71
                    ans+=m/10*pOf10+(n%(pOf10*10))-pOf10+1;
                }
                pOf10*=10;//多少个“连续10个数”,多少个“连续100个数”
                m/=10;//取商，相当于处理完个位后，再处理剩下的数的个位，如对于123，先处理个位3，再处理剩下的12(即从低位到高位一位一位处理）
            }
            return ans;
        }
    }

/**
 * 方法1：暴力
 */
class Solution46_2 {
    public int NumberOf1Between1AndN_Solution(int n) {
        int count=0;
        while(n>0){
            String str=String.valueOf(n);
            char[] chars=str.toCharArray();
            for(int i=0;i<chars.length;i++) {
                if(chars[i]=='1') {
                    count++;
                }
            }
            n--;
        }
        return count;
    }
}

class Solution461 {
    public int countDigitOne(int n) {
        int digit = 1, res = 0;
        int high = n / 10, cur = n % 10, low = 0;
        while(high != 0 || cur != 0) {
            if(cur == 0) {
                res += high * digit;
            } else if(cur == 1) {
                res += high * digit + low + 1;
            } else {
                res += (high + 1) * digit;
            }
            low += cur * digit;
            cur = high % 10;
            high /= 10;
            digit *= 10;
        }
        return res;
    }
}
