package 数据结构与算法.第三遍;

public class demo83_2多线程按顺序打印奇偶数_Synchronized {
        //共享变量
        public static int number = 0;
        public static void main(String[] args) {
            //用于加锁
            Object object = new Object();
            //线程1，打印偶数
            Thread t1 = new Thread(new Runnable() {
                @Override
                public void run() {
                    while (number < 100) {//打印100以内的
                        synchronized (object) {
                            if (number % 2 == 0) {//偶数
                                System.out.println(number);
                                number++;
                                //打印完成后，调用notifyAll，唤醒其他线程。
                                object.notifyAll();
                            } else {//奇数
                                //不应该自己打印时，进行等待，避免空循环浪费CPU。
                                try {
                                    object.wait();
                                } catch (Exception e) {

                                }
                            }
                        }
                    }
                }
            });

            //线程2，打印奇数
            Thread t2 = new Thread(new Runnable() {
                @Override
                public void run() {
                    while (number < 100) {
                        synchronized (object){
                            if (number % 2 == 1) {
                                System.out.println(number);
                                number++;
                                //打印完成后，调用notifyAll，唤醒其他线程。
                                object.notifyAll();
                            } else {
                                //不应该自己打印时，进行等待，避免空循环浪费CPU。
                                try {
                                    object.wait();
                                } catch (Exception e) {

                                }
                            }
                        }
                    }
                }
            });

            //启动线程
            t1.start();
            t2.start();
            //先线程1，后线程2
            try {
                t1.join();
                t1.join();
            } catch (Exception e){

            }
        }
}
