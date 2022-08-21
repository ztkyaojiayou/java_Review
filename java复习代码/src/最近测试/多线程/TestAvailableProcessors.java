package 最近测试.多线程;

/**
 * @author :zoutongkun
 * @date :2022/8/20 6:30 下午
 * @description :
 * @modyified By:
 */
public class TestAvailableProcessors {
    public static void main(String[] args) {
        // 返回可用处理器的Java虚拟机的数量
        int nProcessors = Runtime.getRuntime().availableProcessors();
        System.out.println(nProcessors);
    }
}
