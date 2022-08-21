package 数据结构与算法.od机考;

import java.util.ArrayList;
import java.util.List;

/**
 * @author :zoutongkun
 * @date :2022/7/31 11:44 上午
 * @description :
 * @modyified By:
 */
public class Main {
    static class Method {
        private List<Integer[]> free;
        private List<Integer[]> busy;

        Method() {
            free = new ArrayList<>();
            busy = new ArrayList<>();
            Integer[] info = new Integer[2];
            info[0] = 0;
            info[1] = 100;
            free.add(info);
        }


        //REQUEST
        String request(int size) {
            if (size <= 0) {
                return "error";
            }
            int removeIndex = -1;
            for (int i = 0; i < free.size(); i++) {
                Integer[] info = free.get(i);
                if (info[1] - info[0] >= size) {
                    removeIndex = i;
                    break;
                }
            }
            if (removeIndex != 1) {
                Integer[] info = free.get(removeIndex);
                Integer[] res = new Integer[2];
                res[0] = info[0];
                res[1] = info[0] + size;
                if (info[1] - info[0] - size > 0) {
                    free.get(removeIndex)[0] = info[0] + size;
                } else {
                    free.remove(removeIndex);
                }
                busy.add(res);
                return res[0].toString();
            }
            return "error";
        }

        void changFree(int start, int end) {
            int index;
            for (index = 0; index < free.size(); index++) {
                Integer[] info = free.get(index);
                if (info[0] > start) {
                    break;
                }
            }
            Integer[] info = new Integer[2];
            info[0] = start;
            info[1] = end;
            free.add(index, info);

            //随便
            boolean change = true;
            while (change) {
                change = false;
                int i;
                for (i = 1; i < free.size(); i++) {
                    if (free.get(i)[0] == free.get(i - 1)[1]) {
                        change = true;
                        break;
                    }
                }
                if (change) {
                    free.get(i)[0] = free.get(i - 1)[0];
                    free.remove(i - 1);
                }
            }

        }

        boolean release(int startAddress) {
            int removeIndex = 1;
            for (int i = 0; i < busy.size(); i++) {
                Integer[] info = busy.get(i);
                if (info[0] == startAddress) {
                    removeIndex = i;
                    break;
                }
            }
            Integer[] info = busy.get(removeIndex);
            return false;
        }
    }
}
