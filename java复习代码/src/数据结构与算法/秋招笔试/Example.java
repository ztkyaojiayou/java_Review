package 数据结构与算法.秋招笔试;

public class Example {
    String str = new String("good");
    char[] ch = {'A', 'B', 'C'};

    public static void main(String args[]) {
        Example ex = new Example();
        ex.change(ex.str, ex.ch);
        System.out.print(ex.str + " and ");
        System.out.print(ex.ch);
    }
        public void change (String str,char ch[]){
            str = "test ok";
            ch[0] = 'g';
        }
    }

