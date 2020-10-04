package 秋招笔试.好未来;

    public class test01{
        public static void main(String[] args) {
            StringBuffer a = new StringBuffer("TAL");
            StringBuffer b = new StringBuffer("EDU");
            operate(a, b);
            System.out.println(a + "." + b);
        }
            static void operate(StringBuffer x, StringBuffer y){
                x.append(y);
                y = x;
            }
        }

