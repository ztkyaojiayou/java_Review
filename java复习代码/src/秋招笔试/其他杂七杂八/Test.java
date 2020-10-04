package 秋招笔试.其他杂七杂八;

class User {
    private static String name;

    User(String name) {
        this.name = name;
//1
    }

    public void show()
    {
        System.out.println(name);
    }
}

public class Test {
    public static void main(String args) {
        User u1 = new User("关羽");
        User u2 = new User("张飞");
        u1.show();
        u2.show();
    }
}


