package 数据结构与算法.离职后刷题.第四遍必会版;


    /**
     * （1）饿汉式(静态变量)，线程是安全的
     * 因为在类加载时就完成实例化。
     *
     * ➢优缺点说明:
     * 1)优点: 这种写法比较简单,就是在类装载的时候就完成实例化，避兔了线程同步问题。
     * 2)缺点: 在类装载的时候就完成实例化，没有达到Lazy Loading的效果。
     * 如果从始至终从未使用过这个实例，则会造成内存的浪费
     * 3)这种方式基于classloder机制避免了多线程的同步问题，
     * 不过，instance在类装载时就实例化，在单例模式中大
     * 多数都是调用getnstance方法，但是导致类装载的原因有很多种，
     * 因此不能确定有其他的方式(或者其他的静
     * 态方法)导致类装载，这时候初始化instance就没有达到lazy loading的效果
     * 4)结论: 这种单例模式可用，可能造成内存浪费。
     *
     * 注意：这里所说的线程不安全是指多个线程来创建了多个对象，因为这样就不叫单例模式了。
     */

    class Singleton011 {
        //1. 构造器私有化, 外部能new
        private Singleton011() {}
        //2.本类内部创建对象实例
        private final static Singleton011 instance = new Singleton011();

        //3. 提供一个公有的静态方法，返回实例对象
        public static Singleton011 getInstance() {
            return instance;
        }

    }


/**
 * （2）饿汉式(静态代码块)，线程是安全的
 */
class Singleton021 {
    //1. 构造器私有化, 外部能new
    private Singleton021() {}

    //2.本类内部创建对象实例
    private static Singleton021 instance;

    static { // 在静态代码块中，创建单例对象
        instance = new Singleton021();
    }

    //3. 提供一个公有的静态方法，返回实例对象
    public static Singleton021 getInstance() {
        return instance;
    }
}


/**
 * （3）懒汉式(普通单线程版/线程不安全版）
 *    * 优缺点说明:
 *      * 1) 起到了Lazy Loading的效果，但是只能在单线程下使用。
 *      * 2)如果在多线程下，一个线程进入了if (singleton == null)判断语句块，还未来得及往下执行，另一个线程也通过
 *      * 了这个判断语句，这时便会产生多个实例。所以在多线程环境下不可使用这种方式
 *      * 3)结论: 在实际开发中，不要使用这种方式.
 */

class Singleton031 {
    private static Singleton031 instance;

    private Singleton031() {}

    //提供一个静态的公有方法，当使用到该方法时，才去创建 instance
    //即懒汉式
    public static Singleton031 getInstance() {
        if(instance == null) {//易知这一步就有可能产生线程不安全，
            // 因为可能有多个线程都通过了这一重检测，然后都去创建对象，因此就有可能创建多个对象。
            instance = new Singleton031();
        }
        return instance;
    }
}


/**
 * （4）懒汉式(线程安全，同步方法)
 *
 * 优缺点说明:
 * 1) 解决了线程安全问题
 * 2)|效率太低了，每个线程在想获得类的实例时候，
 * 执行getInstance0方法都要进行同步。而其实这个方法只执行
 * 一次实例化代码就够了，后面的想获得该类实例，
 * 直接return就行了。方法进行同步效率太低
 * 3)结论: 在实际开发中，不推荐使用这种方式
 */

class Singleton041 {
    private static Singleton041 instance;

    private Singleton041() {}

    //提供一个静态的公有方法，加入同步处理的代码，解决线程安全问题
    //也可以使用同步代码块的形式，但这只是形式上的问题，本质是一样的，没啥意思~
    //即懒汉式
    public static synchronized Singleton041 getInstance() {
        if(instance == null) {
            instance = new Singleton041();
        }
        return instance;
    }
}


/**
 * （5）双重检测版（推荐）
 *
 * 优缺点说明:
 * 1) Double-Check 概念是多线程开发中常使用到的，如代码中所示，
 * 我们进行了两次if (singleton == null)检查，这样就可以保证线程安全了。
 * 2)这样，实例化代码只用执行一次，后面再次访问时，
 * 判断if (singleton== null)，直 接return实例化对象，也避免的反复进行方法同步.
 * 3)线程安全;延迟加载;效率较高
 * 4)结论: 在实际开发中，推荐使用这种单例设计模式
 *
 * “双重检测版”的注意事项（面试常问）：
 * 问题1：为什么需要两次判断if(singleTon==null)?
 * 分析：
 * (1)第一次校验（考虑的是效率，但依旧有安全问题）：由于单例模式只需要创建一次实例，
 * 如果后面再次调用getInstance方法时，则直接返回之前创建的实例，
 * 因此这样的话，大部分时间不需要执行同步方法里面的代码，大大提高了性能。
 * 如果不加第一次校验的话，那跟上面的懒汉模式没什么区别，每次都要去竞争锁。
 *
 * (2)第二次校验（考虑的是安全性）：如果没有第二次校验，假设线程t1执行了第一次校验后，判断为null，
 * 这时t2突然获取了CPU执行权（因为这里没有加锁，因此这种现象是完全有可能的），也执行了第一次校验，判断也为null，
 * 且若t2先获得了锁，于是就会先创建该单例对象的实例，创建完之后，释放锁。
 * 这时t1又获得CPU执行权，由于之前已经进行了第一次校验，结果为null（不会再次判断），
 * 于是在获得锁后，就会直接又会创建一个该单例对象的实例。
 * 结果就会导致创建2个（多个）实例。
 * 所以需要在同步代码里面进行第二次校验，只有实例为空时才进行创建
 * （因为只要有线程在同步代码块中已经创建了对象，那么此时单例对象就不再为null了，
 * 于是下一个线程再来创建时就会直接返回该单例对象了）。
 *
 *  小结：也即由于第一次null校验是有可能多个线程同时执行的，即都有可能校验成null，
 *  然后又有可能分别去获取锁（此时是不会再进行第一个null判断了的），
 *  分别创建对象，于是就创建了多个实例。
 *  因此在一个线程获得了锁后还要进行一次null判断，
 *  只有此时还为null时才创建，即表示之前确实没有线程创建过。
 *
 * 问题2：为什么一定要加volatile关键字？
 * 答：因为，不然的话，会存在多线程并发时候可能返回半初始化对象，
 * 由于JVM指令重排优化的存在，在某个线程创建单例对象时，在构造方法被调用之前，
 * 就为该对象分配了内存空间并将对象的字段设置为默认值。
 * 此时就可以将分配的内存地址赋值给instance字段了，
 * 然而该对象可能还没有初始化。若紧接着另外一个线程来调用getInstance，
 * 取到的就是状态不正确的对象，程序就会出错。
 *
 * 具体解释如下：
 *        if(instance == null) {//第一次检测（a）
 *                 synchronized (Singleton05.class) {//加锁（b）
 *                     if(instance == null) {//第二次检测（c）
 *                         instance = new Singleton05();//开始真正创建对象（d）
 *
 * 2.1 在回答这个问题之前需要知道的知识点：
 * instance = new Singleton05();这个语句不是一个原子操作,编译后会多条字节码指令：
 * 步骤1.为new出来的Singleton05对象开辟内存空间
 * 步骤2.初始化，即调用 Singleton 的构造函数来初始化成员变量，形成实例（此时该对象就是一个完整的对象了）
 * 步骤3.完成instance引用的赋值操作，即把instance指向刚刚开辟的内存地址
 * （只有/只要执行完这步 singleton才/就是非 null的了，
 * 而若先执行这一步而还没有执行第二步时，
 * 则此时该对象为半对象状态，即为非null但又还没有初始化，该对象还不是一个完整的对象，并不是我们想要的，
 * 这一点很重要，这也是为什么要加volatile关键字的核心原因）
 *
 * 2.2 下面从两种场景来详细分析为什么需要volatile关键字：
 * 1）可能场景-线程t1,t2均到达 代码b处：
 * 这个时候假若线程t1获得锁，t2处于阻塞状态，直到t1 依次执行代码a,b,c,d，
 * 并且在释放锁之前会将对变量instance的修改刷新到主存当中，
 * 保证当其他线程再进入的时候，在主存中读取到的就是最新的变量内容了。
 * t1释放锁之后，t2获得锁，根据重新从主内存拿到的变量instance值判断不为null，
 * 则直接跳过代码d的执行，即线程2只执行了代码a,b,c就释放掉了锁。
 * 结论：这个场景下线程t1,t2会拿到了一个完整的instance所以是不存在问题的。
 *
 * 2）真正的问题场景-线程t1执行到代码d处，线程t2执行到代码a处：
 * 线程t1执行到代码d处时，在没有加volatile关键字修饰instance时是存在指令重排序的问题的，
 * 假若代码d的执行顺序是步骤1、步骤3、步骤2。
 * 在线程t1执行完成步骤3，还没有执行步骤2时，线程t2执行到代码a处，对instance进行判断是否为null，
 * 发现不为null则直接返回使用（但此时instance是不一个不为null的但是没有初始化完成的对象）
 *
 * 2.3 结论：这个场景下线程t1是没有问题的会得到一个完整的instance,
 * 但是t2会提前拿到了一个不完整的instance是存在问题的，
 * 所以需要加上volatile来禁止这个语句instance=new MyManger3();进行指令重排序。
 *
 * volatile关键字的一个作用是禁止指令重排，
 * 把instance声明为volatile之后，对它的写操作就会有一个内存屏障，
 * 这样，在它的赋值完成之前，就不用调用读操作。

 * 注意：volatile阻止的不是singleton = new Singleton()这句话内部[1-2-3]的指令重排，
 * 而是保证了在一个写操作（[1-2-3]）完成之前，不会调用读操作（if (instance == null)）。
 */
// 懒汉式(线程安全，同步方法，双重检测)
class Singleton05 {
    //1.将构造函数私有化，防止直接new对象。
    private Singleton05() {}

    //2.1 加private是为了使其成为该类的私有变量，不让外部类直接获得/访问，而只能通过get方法获得，
    // 这也是为什么下面的getInstance方法设置为public的原因
    // 2.2 加static是为了使得该类在类加载时就创建该单例对象，
    // 因为static变量是类变量，会随着类的加载而加载，且只会加载一次。
    // （注意：这里并不是说强调类可以直接调用或全局共享变量）
    // 2.3 （重要）加volatile关键字则是通过其“禁止指令重排”的特性来保证一个线程在创建该对象时能完整地把这个对象创建出来，
    // 防止“在该线程还没有完全将该对象创建完毕（即半对象从状态，即非null但又没有初始化）时，
    // 另一个线程在判断第一重检测时发现已经不为null了，于是就直接返回了该半初始化的对象”的现象发生。
    private static volatile Singleton05 instance = null;//首先是为null

    //3.提供一个静态的公有方法，加入双重检查代码，
    // 解决线程安全问题, 同时解决懒加载问题，同时保证了效率, 推荐使用。
    //3.1 加上public的原因上面已经说了，即使得外部类可以通过访问该方法来获取该单例对象。
    //3.2 加上static的原因和上面一样，也是为了使得该方法能随着类的加载而加载，且只会加载一次。
    //3.3 不要在该方法上加synchronized关键字，而是使用同步代码块的形式在真正需要加锁的地方加锁即可
    public static Singleton05 getInstance() {
        //3.1 第一次检测，目的是为了提高效率和性能，即若已经有线程创建了该对象（相当于已经有线程创建了该单例对象了），
        // 则之后来的线程就可以直接获取到该对象了，而不需要再次创建了。
        if(instance == null) {
            //3.2 加锁，目的是为了保证多线程下的安全性问题，易知，若不加锁，则多个线程都有可能先判断为null，
            //然后又分别去创建对象（因为只要判断完null后，后面就不会再次判断了），从而导致创建了多个对象。
            synchronized (Singleton05.class) {//锁的是该单例对象
                //3.3 第二次检测，目的是为了防止“多个线程通过了第一重null检测后（相当于最开始），
                // 再分别加锁，分别创建对象，从而又创建了多个对象”的现象。
                if(instance == null) {
                    //3.4 开始真正创建对象并赋给instance。
                    instance = new Singleton05();
                }
            }
        }
        //4.最后，返回该实例即可。
        return instance;
    }
}

//在另一个类中进行测试
//（实际情况中，单例模式肯定是被封装好了的，因此我们也只能在其他类中创建该类的对象来获取该单例对象的）
class Test{
    public static void main(String[] args) {
        //1.先使用getInstance方法获取两个对象
        Singleton05 singleton05 = Singleton05.getInstance();
        Singleton05 singleton1 = Singleton05.getInstance();
        //2.1再看他们是否为同一个对象
        System.out.println(singleton05 == singleton1);//true

        //2.2也还可以查看这两个对象的hashcode是否相同
        System.out.println(singleton05.hashCode());
        System.out.println(singleton1.hashCode());
    }
}


/**
 * （6）静态内部类式， 推荐使用
 *
 *优缺点说明:
 * 1)这种方式采用了类装载的机制来保证初始化实例时只有一个线程。
 * 2)静态内部类方式在Singleton类被装载时并不会立即实例化，
 * 而是在需要实例化时，调用getInstance方法，才
 * 会装载SingletonInstance类，从而完成Singleton的实例化。
 * 3）类的静态属性只会在第一次加载类的时候初始化,所以在这里，JVM帮助我们保证了线程的安全性，
 * 在类进行初始化时，别的线程是无法进入的。
 * 4)优点:避免了线程不安全，利用静态内部类特点实现延迟加载，效率高
 * 5)结论: 推荐使用
 */

class Singleton061 {
    //1.构造器私有化（防止直接new）
    private Singleton061() {}

    //使用volatile的目的是为了在多线程下，线程之间对该对象的可见性
    private static volatile Singleton061 instance;

    //写成static的原因就是为了可以使用类名直接调用，而不必new对象来调用
    //2.再写一个静态内部类,该类中有一个静态属性 Singleton
    private static class SingletonInstance {
        //静态方法可以访问静态属性
        private static final Singleton061 INSTANCE = new Singleton061();//在这里真正创建该对象
    }

    //3.提供一个静态的公有方法，直接返回SingletonInstance.INSTANCE
    public static synchronized Singleton061 getInstance() {
        return SingletonInstance.INSTANCE;
    }
}
