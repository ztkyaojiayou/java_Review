package java基础.finally关键字;

public class test11finally {
        public int add(int a,int b) {
            try {
                return 67;
            }
            catch(Exception e){
                System.out.println("catch语句块");
            }
            finally {
                a = 12;
                System.out.println("finally语句块");
                 a=1;
            }
            return 12;
        }
        public static void main(String[] args) {
            test11finally t=new test11finally();
            System.out.println("和是"+t.add(9, 34));
        }

    }
