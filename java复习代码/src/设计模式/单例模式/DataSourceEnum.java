package 设计模式.单例模式;
/**
 * 单例模式的枚举实现
 * @author :zoutongkun
 * @date :2022/8/10 11:04 上午
 * @description :
 * @modyified By:
 */
enum DataSourceEnum {
    //反编译后的结果为：
    //public final class DataSourceEnum extends Enum<DataSourceEnum> {
    //      public static final DataSourceEnum DATASOURCE;
    //      public static DataSourceEnum[] values();
    //      public static DataSourceEnum valueOf(String s);
    //      static {};
    //}
    DATA_SOURCE_ENUM;
    //需要私有化，不允许外部直接得到该单例对象
      private DbConnection connection = new DbConnection();
     //这个无参构造器可以不写
//     DataSourceEnum(){
//        connection = new DbConnection();
//    }

    public DbConnection getConnection(){
        return connection;
    }
}


class DbConnection{}

class Main{
    public static void main(String[] args) {
        DataSourceEnum db = DataSourceEnum.DATA_SOURCE_ENUM;
        DbConnection connection1 = db.getConnection();
        DbConnection connection2 = db.getConnection();
        System.out.println(connection1);
        System.out.println(connection2);
        System.out.println(connection1 == connection2);

    }
}