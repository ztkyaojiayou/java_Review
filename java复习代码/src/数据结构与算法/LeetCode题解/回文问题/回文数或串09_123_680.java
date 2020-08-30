package 数据结构与算法.LeetCode题解.回文问题;

/**
 * 9. 回文数
 * 判断一个整数是否是回文数。回文数是指正序（从左向右）和倒序（从右向左）读都是一样的整数。
 *
 * 示例 1:
 * 输入: 121
 * 输出: true
 *
 * 示例 2:
 * 输入: -121
 * 输出: false
 * 解释: 从左向右读, 为 -121 。 从右向左读, 为 121- 。因此它不是一个回文数。
 *
 * 示例 3:
 * 输入: 10
 * 输出: false
 * 解释: 从右向左读, 为 01 。因此它不是一个回文数。
 *
 * 进阶:
 * 你能不将整数转为字符串来解决这个问题吗？
 */

/**
 * 思路解析：如果除开进阶里面的限制的话，可以先将其转化为字符串，然后就可以有很多种方法了，
 * 比如，再反转一下字符串并比较，或者使用首尾双指针逐一比对等。
 *
 * 但如不能将其转化为字符串的话，就可以使用数学的思维~
 *
 * 如果是负数则一定不是回文数，直接返回 false
 * 如果是正数，则将其倒序数值计算出来，然后比较和原数值是否相等
 * 如果是回文数则相等返回 true，如果不是则不相等 false
 * 比如 123 的倒序 321，不相等；121 的倒序 121，相等。
 *
 * 对于数字的末位，直接取余就可以了，对于数字的首位，我们可以这么算。
 * 首先用一个变量记录数字的最高位，
 * 比如 123211232112321，可以标记 help 为 100001000010000，
 * 第一个末位为 111，第一个首位为 12321/10000=1，
 * 接下来我们需要计算 232232232 是否为回文，怎么计算呢？
 * 我们需要去掉首位和末位。
 * 可以采用 x % help / 10 的方式
 * 12321%10000==2321 可以将最高位去掉，然后 2321/10==232 可以将最低为去掉。
 * 最后不要忘记将 help/100。
 */
class 回文数09 {
    //方法：把传入的值分解
        public boolean isPalindrome(int x) {
            if(x < 0)
                return false;
            int num = x;//题目所给值
            int cur = 0;//当前值（从低位开始求）

            while(num != 0) {
                //%:取余，/:取商
                //如121可以由：（（0 * 10 + 1）* 10 + 2）* 10 + 1 求出
                //先从低位开始算，对于数字的末位，直接取余就可以得到（即num % 10）
                cur = cur * 10 + num % 10;
                num /= 10;//取商，即每处理一位，就降一位，如121，
                // 先用121取余得到1，将其作为个位的末位；
                // 再用121取商得到12，再用该数取余得到2，将其作为十位的末位；
                // 同样地，最后再用12取商得到1，再用该数取余得到1，将其作为百位的末位；
                //(每上升一位，就是乘以10嘛，即这里写的：cur * 10）
            }
            //最后，比较二者是否相等即可
            return cur == x;
        }
    }

/**
 * 对于其他不太符合题目要求的方法也在此列举出来，
 * 因为重要的从来不是求出最优解，而是开拓思维，锻炼从不同角度解决问题的能力
 *
 * 方法2：将其转化为字符串，再反转，最后与原字符串比较即可，简单粗暴~
 *
 * 补充：
 * （1）java字符，字符串，数字之间的转换
 *     1）string 和int之间的转换
 *       string转换成int  :Integer.valueOf("12")
 *       int转换成string :
 *       方法1：String.valueOf(12)
 *       方法2：String s = Integer.toString(12);
 *       方法3：int a = 555;
 *          String b = a + "";
 * 　　   注: Double, Float, Long 转成字串的方法大同小异.
 *
 *     2）char和int之间的转换
 *     首先将char转换成string，即：String str=String.valueOf('2')
 *     然后使用Integer.paseInt(str)就可以转化为int型啦
 *     注意：但Integer.valueOf返回的是Integer对象
 *
 * （2）StringBuffer与String的转换：（即StringBuffer不等于String，需要转换）
 * 1）虽然StringBuffer和String都是操作字符串，但两者属于不同的类，不能直接兼容，相互转换的方法为：
 * String s = “abc”;
 * StringBuffer sBuff2 = new StringBuffer(s);   //String转换为StringBuffer
 * String s1 =sBuff2.toString();                 //StringBuffer转换为String
 *
 * 2）另外，new StringBuilder(123 + "")是把数字123拼接成 StringBuilder对象
 *
 */
class solution09_1{
        ///简单粗暴，看看就行
            public boolean isPalindrome(int x) {
                String strNum = String.valueOf(x);
                //把数字转化为字符串的方法：
                //先把数字转化为StringBuilder的字符串对象（注意：该字符串对象不等于String字符串对象），
                // 再使用其方法对该字符串对象进行反转（reverse），再把反转之后的StringBuilder的字符串对象转化为String字符串对象（toString）
                //（为什么要这样才能转换？因为String类没有直接反转字符串的方法，且对字符串的操作都会生成新的字符串对象，而StringBuffer则不会)
                String reversedStr = (new StringBuilder(x + "")).reverse().toString();
                return (x + "").equals(reversedStr);
            }
        }
/**
 * 方法3：首尾双指针
 *     解题思路:
 *         数值转成字符串，遍历元素
 *         首尾各一个指针，向中间靠拢，每次元素是否相等
 *     复杂度分析:
 *         时间复杂度 O(n/2) ，N 表示数值包含元素长度 ，如果数值是回文数 最多需要遍历 O(n/2) 次
 *         时间复杂度 O(1)
 */
class Solution09_2 {
    public boolean isPalindrome(int x) {
        if(x<0){ // 如果为负数，直接返回 false
            return false;
        }
        //1.先利用valueOf将数字转化为字符串
        String strNum = String.valueOf(x);

        //2.再再使用双指针比较即可
        for(int i = 0;i<=strNum.length()/2;i++) {//遍历前半段即可，尾指针可以由首指针表示
            int a = strNum.charAt(i);//首指针
            int b = strNum.charAt(strNum.length()-1-i);//尾指针
            if(a != b) {//再看其对应的值是否相等
                return false;
            }
        }
        return true;

    }
}

/**
 * 125. 验证回文串（入门级）
 * 给定一个字符串，验证它是否是回文串，只考虑字母和数字字符，可以忽略字母的大小写。
 * 说明：本题中，我们将空字符串定义为有效的回文串。
 *
 * 示例 1:
 * 输入: "A man, a plan, a canal: Panama"
 * 输出: true
 *
 * 示例 2:
 * 输入: "race a car"
 * 输出: false
 */

/**
 * 思路解析：使用前后双指针一一判断即可，很简单，
 * 只是要写一下如何判断指定字符是否为字符或数字（写法2中将直接使用库函数）
 */
class Solution125_1 {
    public boolean isPalindrome(String s) {
        //1.先把字符串转化为小写的形式
        s = s.toLowerCase();
        //2.再把其转化为字符数组
        char[] chars = s.toCharArray();
        //3.定义双指针,一左一右
        int left = 0, right = chars.length - 1;
        //4.开始遍历并判断
        while (right > left) {
            //4.1只有当左右均为字符或数字时，才进行比较
            if ((chars[left] >= '0' && chars[left] <= '9') || (chars[left] >= 'a' && chars[left] <= 'z')) {//左边为字符或数字时
                if ((chars[right] >= '0' && chars[right] <= '9') || (chars[right] >= 'a' && chars[right] <= 'z')) {//右边为字符或数字时
                    //1)此时，若有一处不相等，就可以说明肯定不是回文串，则直接返回false
                    if (chars[left] != chars[right]) {
                        return false;
                    } else {//2)否则，再进行下一组的判断
                        left++;
                        right--;
                    }
                } else {//4.2若右边不是字符或数字，则跳过该字符，进行下一个字符的判断
                    right--;
                }
            } else {//4.3同样地，若左边不是字符或数字，则也跳过该字符，进行下一个字符的判断
                left++;
            }
        }
        //5.若当全部比对完毕之后都没有返回false时，则说明其对应的字符全部相等，因此即为回文字符串，返回true即可
        return true;
    }
}

/**
 * 写法2：也是使用双指针，不过使用了一个库函数isLetterOrDigit来判断指定字符是否为字母或数字
 * 设置左、右双指针，向中间判断；
 * 跳过非数字字母的字符；
 * 将字母全部转化为小写体，之后判断。
 */
class Solution125_2 {
    public boolean isPalindrome(String s) {
        //1.定义双指针，一左一右
        int i = 0, j = s.length() - 1;
        while(i < j){//2.开始遍历并判断
            //boolean isLetterOrDigit(char ch)：用于判断指定字符是否为字母或数字，若是（是字母或字符都行），则返回true。
            //2.1若该处字符不为数字或字母，则直接跳过
            while(i < j && !Character.isLetterOrDigit(s.charAt(i))) i++;
            while(i < j && !Character.isLetterOrDigit(s.charAt(j))) j--;
            //2.2将字母全部转化为小写体，之后再判断其是否相等，只要有一个不相等，就立马返回false。
            if(Character.toLowerCase(s.charAt(i)) != Character.toLowerCase(s.charAt(j))) return false;
            i++; j--;
        }
        //3.否则，若当全部比对完毕之后都没有返回false时，则说明其对应的字符全部相等，因此即为回文字符串，返回true即可
        return true;
    }
}

/**
 * 680. 验证回文字符串 Ⅱ(进阶版）
 * 给定一个非空字符串 s，最多删除一个字符。
 * 判断是否能成为回文字符串。
 *
 * 示例 1:
 * 输入: "aba"
 * 输出: True
 *
 * 示例 2:
 * 输入: "abca"
 * 输出: True
 * 解释: 你可以删除c字符。
 *
 * 注意:
 *字符串只包含从 a-z 的小写字母。字符串的最大长度是50000。
 */

/**
 * 思路解析：首先，此题与上一题不同之处在于：其没有空格，且只有字符而没有数字，
 * 另外它给一次机会删除某个字符，其实就相当于换一个字符再比较一次。
 * 其仍然是变着法儿的考双指针，只需要一前一后两个指针向中间走，
 * 遇到字符不同就考虑两种情况，要么动前面，要么动后面，再比较即可，
 * 即只需要当发现chas[left]!=chas[right]时，跳过left 或者right，就酱~
 */
class solution680{
    public boolean validPalindrome(String s) {
        //0.特判
    if (s == null || s.length() == 0) return false;
    //1.先把字符串转化为字符数组
    char[] chas = s.toCharArray();
    //2.再定义左右指针
    int left = 0, right = chas.length-1;
    while (left < right) {//3.开始遍历并比对
        if (chas[left] != chas[right]) {//3.1若该处的字符不相等，就考虑两种情况，要么动前面，要么动后面，再去比较即可(注意，此时只动一边，而另一端是固定的）
            return validate(chas, left + 1, right) || validate(chas, left, right - 1);
        }
        //3.2接着再判断下一组，即此时左右指针都移动到下一个位置，开始比较，以此类推，直到遍历到左右指针重合。
        left++;
        right--;
    }
    //4.若到最后，两指针都重合了都没有返回false，则说明是回文串，返回true即可
    return true;
}
//判断一个char[]是否是回文的方法，也是核心方法：
//方法：利用左右指针，分别从左到右扫，不相等，返回false,都扫完后，返回true
    private boolean validate(char[] chas, int left, int right) {
        while (left < right) {
            if (chas[left] != chas[right]){
                return false;
            }else{
                left++;
                right--;
            }
            //简单写法：if (chas[left++] != chas[right--]) return false;
        }
        return true;
    }
}
