package 秋招笔试.其他杂七杂八;

public class aF {
    public static void main(String[] args) {
        User1 u = new User1("关羽");
        u.show();
    }
}

    class User1 {
        private String name = "张飞";
        User1(String name) {
            name = name;
        }

        public void show() {
            System.out.println(name);
        }
    }

