package java基础;

import java.util.List;

/**
 * 关于自动拆箱与装箱再探讨：
 * 首先注意的是：当 "=="运算符的两个操作数都是 包装器类型的引用，则是比较指向的是否是同一个对象，
 * 而如果其中有一个操作数是表达式（即包含算术运算）则比较的是数值（即会触发自动拆箱的过程）。
 * 另外，对于包装器类型，equals方法并不会进行类型转换。明白了这2点之后，下面程序的输出结果便一目了然：
 * 第一个和第二个输出结果没有什么疑问。
 * 第三句由于  a+b包含了算术运算，因此会触发自动拆箱过程（会调用intValue方法），因此它们比较的是数值是否相等。
 * 而对于c.equals(a+b)会先触发自动拆箱过程，再触发自动装箱过程，也就是说a+b，
 * 会先各自调用intValue方法，得到了加法运算后的数值之后，便调用Integer.valueOf方法，再进行equals比较。
 * 同理对于后面的也是这样，不过要注意倒数第二个和最后一个输出的结果（如果数值是int类型的，装箱过程调用的是Integer.valueOf；
 * 如果是long类型的，装箱调用的Long.valueOf方法）。
 */
public class 自动装箱与拆箱 {
    public static void main(String[] args) {

        Integer a = 1;
        Integer b = 2;
        Integer c = 3;
        Integer d = 3;
        Integer e = 321;
        Integer f = 321;
        Long g = 3L;
        Long h = 2L;

        //int we= 2;
        //long wer = 3;
        //System.out.println(we+wer);//结果为long型的5

        //1）true，比较的是对象是否相等，由于小于127，因此二者指向的是同一个对象。
        System.out.println(c==d);

        //2）false，值相同，但并非同一对象，因为已经超过了[-128,127]的缓存范围。
        System.out.println(e==f);

        //3）true,对于==，只要有一个是值，那么就是比较值是否相等；
        //而对于这里的a+b，由于有加法操作，因此JVM会对Integer类型的a和b触发自动拆箱操作，
        //把其变为int型再相加，其结果就为int型，
        //再去和Integer类型的c比较时，系统会对c也进行自动拆箱操作，变成int型，再比较二者的值是否相等。
        System.out.println(c==(a+b));

        //4）true,因为对于equals，它会先触发自动拆箱过程，再触发自动装箱过程，也即最终比较的还是对象是否相等。
        //比如，对于这里的a+b，JVM会先各自调用intValue方法将其拆箱为int型再相加，
        //得到了加法运算后的数值之后，会再调用Integer.valueOf方法使其自动装箱为Integer类型，
        //再进行equals比较，即此时比较的是两个对象是否相等，此时若数值相等，
        //则只需要看其值是否都在缓存区即可，若在，则为true，否则，也不相等，即为false。
        System.out.println(c.equals(a+b));

        //5）true,前面已经进过，对于==，只要有一个是值，那么就是比较值是否相等。
        //此时，对于int型或Long型，都只看其数值意义上的值是否相等，其他的则不管。
        System.out.println(g==(a+b));

        //6）false，同理，有equals时，最终比较的是对象是否相等。
        //但要注意的是，如果数值是int类型的，装箱过程调用的是Integer.valueOf，即最终为Integer型对象；
        //而如果是long类型的，装箱调用的Long.valueOf方法，即最终为Long型对象,
        //而Integer型和Long型并不是同一个对象，因此返回false。
        System.out.println(g.equals(a+b));

        //7）true,同理，但要注意，对于int型+long型，JVM会自动把int转换成long后再相加，最后得到的是long型；
        //因此，这里的a+h的结果为long型的数值3，再会将其转为Long型的对象，与对象g比较，
        //易知，由于其值相等，且小于127，因此其指向的是同一个对象，于是就返回true
        System.out.println(g.equals(a+h));
    }
}
