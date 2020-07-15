package 多线程与并发.叶子猿代码.多线程基础.如何使多个线程按照顺序执行;

//使用的是wait和notify机制（例子来自网上）
//但要注意：这个demo并没有使用“标记+判断”方式，而是单纯地采用了锁机制，要求对synchronized锁机制的理解比较深，思路比较新颖
    public class Demo03 implements Runnable {

        private String name;//当前线程名称
        private Object prev;//前一个线程所持有的对象锁
        private Object self;//自身对象锁

        private Demo03(String name, Object prev, Object self) {
            this.name = name;
            this.prev = prev;
            this.self = self;
        }

        /**
         * 先来解释一下其整体思路，从大的方向上来讲，该问题为三线程间的同步唤醒操作，
         * 主要的目的就是ThreadA->ThreadB->ThreadC->ThreadA循环执行三个线程。
         * 为了控制线程执行的顺序，那么就必须要确定唤醒、等待的顺序，所以每一个线程必须同时持有两个对象锁（则要使用同步的嵌套），才能继续执行。
         * 一个对象锁是prev，就是前一个线程所持有的对象锁。还有一个就是自身对象锁，即self。
         *
         * 主要的思想就是，为了控制执行的顺序，必须要先持有prev锁（也就前一个线程释放出来的），再去申请自身的对象锁self，
         * 两者兼备时打印，之后首先调用self.notify()唤醒下一个等待线程B，再释放自身对象锁self,
         * 再调用prev.wait()释放当前对象的prev对象锁，终止当前线程，等待循环结束后再次被唤醒。
         * 运行上述代码，可以发现三个线程循环打印ABC，共10次。
         *
         * 程序运行的主要过程就是A线程最先运行，持有C,A对象锁，后释放A,C锁，唤醒B。
         * 线程B等待A锁，再申请B锁，后打印B，再释放B，A锁，唤醒C，线程C等待B锁，再申请C锁，后打印C，再释放C,B锁，唤醒A。
         *
         * 看起来似乎没什么问题，但如果你仔细想一下，就会发现有问题，
         * 就是初始条件，三个线程按照A,B2,C的顺序来启动，按照前面的思考，A唤醒B，B唤醒C，C再唤醒A。
         * 但是若不顺序启动，则会出现问题。
         */

        @Override
        public void run() {
            int count = 10;
            while (count > 0) {
                synchronized (prev) {//当前线程A先获取到前一个线程的对象锁（则前一个线程即便被唤醒也获取不到该锁），
                    // 则其他线程都会在外面等待
                    synchronized (self) {//再获取到自身对象锁，则易知此时A线程已经获取到两个锁了，后面必须一一释放才行。
                        System.out.print(name);//打印，执行当前线程的任务
                        count--;

                        self.notify();//（先）当前线程唤醒一个线程，但这个线程之只能是下一个线程B，因为此时pre锁还没有释放，
                        // 则前一个线程即便被唤醒也获取不到锁。然后A线程执行完毕后再退出，此时自动释放自己的锁（此时还剩一把锁pre没有释放）。
                    }
                    try {
                        prev.wait();//（后）让获取了前一个线程的锁pre的当前线程A释放掉该锁
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }

            }
        }

        public static void main(String[] args) throws Exception {
            //创建三个object对象，用于对象锁
            Object a = new Object();
            Object b = new Object();
            Object c = new Object();
            Demo03 pa = new Demo03("A2", c, a);//分别为：当前线程名称，前一个线程所持有的对象锁，自身对象锁
            Demo03 pb = new Demo03("B2", a, b);
            Demo03 pc = new Demo03("C2", b, c);

//创建三个线程并顺序启动（这一点很重要）
            new Thread(pa).start();//A线程先执行
            Thread.sleep(10);  //确保按顺序执行，此时都在休眠，休眠时间到了之后会变成可运行状态
            new Thread(pb).start();
            Thread.sleep(10);//此时都在休眠，需要被唤醒才可以执行
            new Thread(pc).start();
            Thread.sleep(10);//此时都在休眠，需要被唤醒才可以执行
        }
    }
