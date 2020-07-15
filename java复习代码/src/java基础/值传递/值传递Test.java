package java基础.值传递;

/**
 * 关于值传递和引用传递，一个例子讲的清清楚楚！
 * 结论：Java中的参数传递是值传递，而不是引用传递
 *
 * 那什么是值传递？
 * 值传递（pass by value）是指在调⽤函数时将实际参数复制⼀份传递到函数中，
 * 这样在函数中如果对参数进⾏修改，将不会影响到实际参数。
 * 注意：这里的重点是“在调⽤函数时将实际参数复制⼀份传递到函数中”，
 * 即传递的是实际参数的副本，这个副本可以是基本数据类型的数值，也可以是引用类型的地址值，反正都是值。
 *
 * 那既然传递的是引用类型的地址值，为什么不直接叫引用传递呢？？？
 * 原因：因为引用传递是一个专有名词，而不是说只要是涉及到引用的传递就叫引用传递，务必注意。
 * 它指的是：在调⽤函数时将实际参数的地址直接传递到方法中，那么在函数中对参数所进⾏的修改，将影响到实际参数。
 * 注意：这里的重点是把实际参数直接传递到方法中，而我们的引用类型其实并不是直接传递到方法中的，
 * 相反地，传递的也是原地址值的一个副本，因此就不能叫“引用传递”。
 *
 * 展开来讲：
 * （1）对于基本数据类型，其传的是该值的一个副本，这样在方法中如果对参数进⾏修改，将不会影响到原实际参数
 * （2）而对于引用数据类型（如对象的传递），传的则是它的地址值的一个副本，但是在对参数进行修改时会不会影响到原实际参数呢？
 * 答案是不确定，要分两种情况：
 *     1）当在方法中没有new新对象时，则因为修改的是同一块地址，则会影响到原实际参数的值
 *     2）若在方法中又重新new了一个该对象时，则由于又会新开辟一块空间，
 * 则会覆盖掉刚才传入的地址值，则之后的修改只会影响到这个新的对象而不会影响到原实际参数了。
 * 这里要特别注意一点，即对于String类型的参数，
 * 由于它的不可变性，则当重新赋一个字符串时，是会重新new一个对象的，覆盖掉原传入的对象地址，而不是单纯的赋值。
 *
 * 小总结：
 * 二者本质上是相同的，即都是值传递，只不过在引用数据类型中，这个值指的是地址值或引用，这一点务必要清楚。
 *
 * 以上所述，分别对应下面代码中的三种情况
 */

class User{
    private String name;
    private String sex;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", sex='" + sex + '\'' +
                '}';
    }
}

//User类在上面（这里就不截图了）
public class 值传递Test {
    public static void main(String[] args) {
        值传递Test test = new 值传递Test();
        //1.对于基本数据类型，传递的是其值的副本
        int i= 10;
        test.pass01(i);
        System.out.println("原main函数照中的i还是：" + i);//10

        //2.对于引用类型，传递的是其地址值的副本
        User user = new User();
        user.setName("Hollis");
        user.setSex("Male");
        //2.1普通情况，即在所调用的方法中没有new新对象，
        //此时会改变原对象中的值，因为操作的是同一块内存
        test.pass02(user);
        System.out.println("原main函数照中的user也变成了：" + user);
        //User{name='hollischuang', sex='Male'}

        //2.2特殊情况，即在所调用的方法中new了一个新对象，
        //此时则不会改变原对象中的值，因为操作的是这个新对象
        test.pass03(user);
        System.out.println("原main函数照中的user还是情况2.1中已经被修改之后的user：" + user);
        //User{name='hollischuang', sex='Male'}

        //2.2.1也是特殊情况，但是只对string类型的，即它可能隐形地创建一个新对象，务必注意。
        String name = "Hollis";
        test.pass04(name);
        System.out.println("原main函数照中的name还是：" + name);//Hollis
    }

    //相应的修改方法
    public void pass01(int i) {
        i = 20;
        System.out.println("传递完之后的i变成了（原i没变）：" + i);//20
    }

    public void pass02(User user) {
        user.setName("hollischuang");
        System.out.println("传递完之后的user变成了（原user也变了）：" + user);
        //User{name='hollischuang', sex='Male'}

    }

    public void pass03(User user) {
        user = new User();//此时相当于又创建了一个新的User对象，这一点非常重要
        //则下面的修改操作全部是针对该对象的，而与原传过来的对象无关了。
        //它会重新开辟⼀块新的内存，赋值给user，
        //则会覆盖掉原来传过来的地址（也是值，只不过叫地址值，这一点至关重要）。
        //后⾯对user的任何修改都不会改变原内存地址里面的内容了
        user.setName("hollischuang");
        System.out.println("传递完之后的user变成了（但原user不变）：" + user);
        //User{name='hollischuang', sex='null'}
    }

    public void pass04(String name) {
        name = "修改成Hollischuang";//由于string类型的不可变性，这里会创建一个新对象，这一点非常重要
        //即相当于name = new String("变成了成电")
        //因此会把传进来的原引用值覆盖，因此此时再去修改name时，就是修改的这个新对象的值了
        System.out.println("传递完之后的name为（但原user不变）：" + name);//Hollischuang
    }
}

