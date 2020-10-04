package 秋招笔试.新浪;
public class Test {
    static class Father {
        static {
            System.out.println("Static Father");
        }

        {
            System.out.println("Non-static Father");
        }

        public Father() {
            System.out.println("Constructor Father");
        }
    }

        static class Son extends Father {
            static {
                System.out.println("Static Son");
            }

        {
            System.out.println("Non-static Son");
        }

        public Son() {
            System.out.println("Constructor Son");
        }
        }

        public static void main(String[] args) {
            new Son();
        }
    }
