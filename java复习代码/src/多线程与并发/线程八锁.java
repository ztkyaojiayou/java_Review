package 多线程与并发;

public class 线程八锁 {
    /*
     * 题目：判断打印的 "one" or "two" ？
     *
     * 1. 两个普通同步方法，两个线程，一个 Number 对象，标准打印， 打印? //one  two
     * 2. 新增 Thread.sleep() 给 getOne() ,打印? //one  two
     * 3. 新增普通方法 getThree() , 打印? //three  one   two
     * 4. 两个普通同步方法，两个线程，两个 Number 对象，打印?  //two  one
     * 5. 修改 getOne() 为静态同步方法，打印?  //two   one
     * 6. 修改两个方法均为静态同步方法，一个 Number 对象?  //one   two
     * 7. 一个静态同步方法，一个非静态同步方法，两个 Number 对象?  //two  one
     * 8. 两个静态同步方法，两个 Number 对象?   //one  two
     *
     * 线程八锁的关键：
     * ①非静态方法的锁默认为  this,  静态方法的锁为 对应的 class 实例
     * ②某一个时刻内，只能有一个线程持有锁，无论几个方法。
     *
     * 总结：

* 所有的非静态同步方法用的都是同一把锁，即实例对象本身，或者说this对象，
* 如果一个实例对象获取了某一个非静态同步方法的锁后，该实例对象就无法获取其他非静态同步方法的锁，
* 而必须释放完当前方法的锁后才能获取其他非静态方法的锁。
* 但如果别的对象的非静态同步方法与该实例对象的非静态同步方法获取不同的锁，则不需要等待。
* 所有的静态同步方法用的也是同一把锁，即类对象本身，
* 所以静态同步方法与非静态同步方法之间是不会有竞态的，
* 但是一个静态同步方法获取class实例的锁后，其他静态同步方法都必须等待该方法释放锁才能获取锁。
* 所谓线程八锁实际上对应于是否加上synchronized，是否加上static等8种常见情况，
*
①一个对象里面如果有多个synchronized方法，
* 某一个时刻内，只要一个线程去调用其中的一个synchronized方法了，其他的线程都只能等待，
* 换句话说，某一时刻内，只能有唯一一个线程去访问这些synchronized方法。

②锁的是当前对象this，被锁定后，其他线程都不能进入到当前对象的其他的synchronized的非静态/普通方法。

③加个普通方法后发现和同步锁无关。

④换成静态同步方法后，情况又变化

⑤所有的非静态同步方法用的都是同一把锁 -- 实例对象本身，也就是说如果一个实例对象的非静态同步方法获取锁后，
* 该实例对象的其他非静态同步方法必须等待获取锁的方法释放锁后才能获取锁，
* 可是别的实例对象的非静态同步方法因为跟该实例对象的非静态同步方法用的是不同的锁，
* 所以毋须等待该实例对象已经取锁的非静态同步方法释放锁就可以获取他们自己的锁。

⑥所有的静态同步方法用的也是同一把锁 -- 类对象本身，
* 易知，这两把锁是两个不同的对象，
* 所以静态同步方法与非静态同步方法之间不会有竞争条件。
* 但是一旦一个静态同步方法获取锁后，其他的静态同步方法都必须等待该方法释放锁后才能获取锁，
* 而不管是同一个实例对象的静态同步方法之间，还是不同的实例对象的静态同步方法之间，只要它们是同一个实例对象
     */

        public static void main(String[] args) {
            Number number = new Number();
            Number number2 = new Number();
            //线程1
            new Thread(new Runnable() {
                @Override
                public void run() {
                    number.getOne();
                }
            }).start();

            //线程2
            new Thread(new Runnable() {
                @Override
                public void run() {
              //number.getTwo();
                    number2.getTwo();
                }
            }).start();

                /*new Thread(new Runnable() {
            @Override
            public void run() {
                number.getThree();
            }
        }).start();*/

        }

    }

    class Number{

        //方法1
        public static synchronized void getOne(){//Number.class
//            try {
//                Thread.sleep(3000);
//            } catch (InterruptedException e) {
//            }

            System.out.println("one");
        }

        //方法2
        public synchronized void getTwo(){//this
            System.out.println("two");
        }

        //方法3
        public void getThree(){
            System.out.println("three");
        }

    }


