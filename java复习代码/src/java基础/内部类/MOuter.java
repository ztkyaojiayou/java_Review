package java基础.内部类;

    //外部类
    public class MOuter {
        //外部类中的方法
        int d = 28;//外部类中的成员变量,不局限于final类型

        //当外部类中的变量与内部类中的变量重名时，
        //和定义在成员变量上的内部类规则相同，使用外部类类名.this即可访问到
        String name = "外部类中的张三";
        public void show( ) {
            //方法内的局部变量，jdk1.8以后不再局限于final类型
            int b = 25;

            //当内部类定义在方法中时
            class MInner {//不能使用访问控制符和static关键字了
                int a = 2; //内部类中的变量（当然不局限于final类型）
                String name = "内部类中的张三";
                public void print() {
                    System.out.println("当然可以访问内部类中的变量a: " + a);
                    System. out. println("也可以访问外部类的方法中的变量b:"+ b);
                    System.out.println("还可以访问外部类中的成员变量d:"+ d);
                    System.out.println("变量重名时，直接访问时，则为内部类里面的值，即：" + name);
                    System.out.println("变量重名时，使用外部类类名.this同样可以访问到，即：" + MOuter.this.name);
                }
            }
            MInner mi = new MInner( );//创建方法内部类的对象(只能在此处创建，因为该内部类只能在此方法中使用
            mi.print( );//再调用内部类的方法
        }


        //测试方法内部类
        public static void main(String[] args) {
            MOuter mo = new MOuter();//创建外部类的对象
            mo. show( );//调用外部类的方法，还要在该方法中创建内部类对象，才可以访问内部类
        }
    }
