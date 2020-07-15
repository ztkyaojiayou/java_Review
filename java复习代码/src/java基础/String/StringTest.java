package java基础.String;

public class StringTest {

    //关于String的详解
    public static void main(String[] args) {

        /**
         * 情景一：字符串池
         * JAVA虚拟机(JVM)中存在着一个字符串池，其中保存着很多String对象;
         * 并且可以被共享使用，因此它提高了效率。
         * 由于String类是final的，它的值一经创建就不可改变。
         * 字符串池由String类维护，我们可以调用intern()方法来访问字符串池。  
         */

        String s1 = "abc";//在字符串池创建了一个对象，该对象永久存入其中，且可以共享
        s1 = "java基础";//这样写是正确的，此时s1指向了一个新对象“ztk”，但原对象“abc”还存在，
        //这就是string类不可变的一个体现
        System.out.println("此时s1的值为：" + s1);//ztk
        //String s1 = "ztk";//error，这样定义当然是错误的，因为s1已经被定义了，不能重新被定义

        String s2 = "abc";//因为字符串池已经存在对象“abc”(共享),所以不再创建，累计创建一个对象
        String s123 = s2;//把s2对象的引用赋给s123，则s123也指向s2  

        System.out.println("s1 == s2 : " + (s1 == s2));//true 因为指向同一个对象
        System.out.println("s123的值为：" + s123);//abc

        //true 因为值相等（注意：string类中的equals方法已经被重写，比较的是值） 
        System.out.println("s1.equals(s2) : " + (s1.equals(s2)));

        /**
         * 情景二：关于new String("")
         *  
         */

        //创建了两个对象（若字符串常量池存在则也只是在堆中创建一个对象）
        //一个存放在字符串池中
        //另一个则存在与堆区中（主要）；指向堆中对象的引用s3则存放在栈中（即使用的是堆对象，而不是字符串池中的那个对象）  
        String s3 = new String("abc");

        String s4 = new String("abc");//同样会尝试创建两个对象，但会先检查字符串池中是否已经存在“abc”对象，
        // 现在由于已经存在了，所以只在堆中创建了一个对象（注意：和s3的地址并不相同）

        System.out.println("s3 == s4 : " + (s3 == s4));//false  因为s3和s4栈区的地址不同，指向堆区的不同地址；  

        System.out.println("s3.equals(s4) : " + (s3.equals(s4)));//true  s3和s4的值相同  

        System.out.println("s1 == s3 : " + (s1 == s3));//false 存放的地方都不同，一个在栈区，一个在堆区  

        System.out.println("s1.equals(s3) : " + (s1.equals(s3)));//true  因为值相同  

        /**
         * 情景三：  
         * 由于常量的值在编译的时候就被确定了。
         * 在这里，"ab"和"cd"都是常量，因此变量str1的值在编译时就可以确定。
         * 这行代码编译后的效果等同于： String str1 = "abcd";
         */

        String str1 = "ab" + "cd";//只有1个对象，即直接创建了“abcd” ，而不是单独创建再拼接（类比lin72和lin73）

        String str11 = "abcd";//和上述语句相同，易知此对象已经存在了

        System.out.println("str1 = str11 : " + (str1 == str11));//true，因为指向的是字符串常量池中的同一个对象 


        /**
         * 情景四：  
         * 局部变量str2,str3存储的是存储两个字符串常量对象(intern字符串对象)的地址。
         *                    
         * 第三行代码原理(str2+str3)：
         * 当使用“+”连接字符串中含有变量时，也是在运行期才能确定的。首先连接操作最开始时如果都是字符串常量，
         * 编译后将尽可能多的字符串常量连接在一起，形成新的字符串常量参与后续的连接（可通过反编译工具jd-gui进行查看）。
         * 接下来的字符串连接是从左向右依次进行，对于不同的字符串，首先以最左边的字符串为参数创建StringBuilder对象（可变字符串对象），
         * 然后依次对右边进行append操作，最后将StringBuilder对象通过toString()方法转换成String对象
         * （注意：中间的多个字符串常量不会自动拼接）。
         * 实际上的实现过程为：String s2=new StringBuilder(“13”).append(new String(“1”)).append(“4”).toString();
         * 当使用+进行多个字符串连接时，实际上是产生了一个StringBuilder对象和一个String对象。          
         * 内存中实际上有五个字符串对象：
         * 三个字符串常量对象、一个String对象和一个StringBuilder对象。
         */

        String str2 = "ab";//单独在字符串常量池中创建1个对象  
        String str3 = "cd";//同样地，也会单独在字符串常量池中创建1个对象                                        

        /**
         * 执行过程为：（重要），会用到StringBuilder缓存类
         * 先会在堆中创建一个StringBuilder类，用str1作为初始值，
         * 接着调用StringBuilder缓存类的append方法对str3进行拼接，
         * 再调用其toString()方法在堆中（而不是在常量池中）创建一个String对象，而这个对象就是我们所需要的
         * 最后将刚生成的String对象的堆地址存放在局部变量str4中
         * 易知，当使用+对多个字符串变量进行连接时，实际上是产生了连个对象，一个StringBuilder对象，
         * 另一个就是我们所需要的String对象。
         */
        String str4 = str2 + str3;//该对象在堆中，虽然值也是abcd

        String str5 = "abcd";//这是直接在字符串常量池中创建一个对象,值为abcd。易知，str11已经创建了，因此这里不再创建。

        System.out.println("str5 = str11:" + (str5 == str11));//  true  
        System.out.println("str4 = str5 : " + (str4 == str5));//  false
        System.out.println("str4 = str11:" + (str4 == str11));//  false 

        /**
         *  情景五：
         *  JAVA编译器对string + 基本类型/常量 是当成常量表达式直接求值来确定的。
         *  运行期的两个string相加，会产生新的对象的，存储在堆(heap)中
         */
        //情况1：
        String str6 = "b";//在字符串常量池中创建一个对象
        //因为str6是变量，则在执行时会使用到StringBuilder类，类比情景四，
        //即运行期的两个string相加时，会产生新的对象的，存储在堆(heap)中
        String str7 = "a" + str6;

        String str67 = "ab";//直接在字符串常量池中创建一个对象“ab”

        System.out.println("str7 = str67 : " + (str7 == str67));//false ，因为一个在堆中，一个在常量池中  

        //情况2：
        final String str8 = "b";//str8为常量，编译期就会被确定了

        String str9 = "a" + str8;//则此时相当于直接在字符串常量池中创建一个“ab”对象

        String str89 = "ab";//常量池中已经有该对象，不用再创建，直接返回地址即可

        System.out.println("str9 = str89 : " + (str9 == str89));//true，因为都在常量池中   

    }
}