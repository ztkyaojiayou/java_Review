package 数据结构与算法.数据结构.稀疏数组;

/**
 * 二维数组 转 稀疏数组 的思路
 * 1. 遍历原始的二维数组，得到有效数据的个数 sum
 * 2. 根据sum 就可以创建 稀疏数组 sparseArr int[sum + 1] [3]
 * 3. 将二维数组的有效数据数据存入到 稀疏数组
 * <p>
 * 稀疏数组 转 原始的二维数组 的思路
 * <p>
 * 1. 先读取稀疏数组的第一行，根据第一行的数据，创建原始的二维数组，比如上面的  chessArr2 = int [11][11]
 * 2. 再读取稀疏数组后几行的数据，并赋给 原始的二维数组 即可.
 */
public class SaveChessBoardDemo {
    public static void main(String[] args) {
        //1、先创建一个原始的二维数组，代表目标棋盘（11*11），其中，0表示没有棋子，1表示黑子，2表示蓝子
        int chessArr1[][] = new int[11][11];
        chessArr1[1][2] = 1;
        chessArr1[2][3] = 2;
        chessArr1[4][5] = 2;
        //输出此原始二维数组（棋盘）
        System.out.println("原始二维数组为：");

        //这里用的增强for循环，且有两层，其遍历思路为：先遍历每一行，再遍历每一行中的值
        //int[] row要一起看，其中int[]表示要获取的结果的类型，row是一个变量（代表行），相当于普通for循环里面的i，且结果也用这个变量处理
        // chessArr1则表示要遍历/循环的目标
        //
        for (int[] row : chessArr1) {
            for (int data : row) {
                System.out.printf("%d\t", data);

            }
            System.out.println();
        }

        //2、再将此原始二维数组转化为稀疏数组
        //思路很简单：把稀疏数组里面的变量的具体值（三个值，行数和列数(已知，就都是11嘛）和非零值的个数，这是单独的一行，且在第一行，这是稀疏数组的最大特点，这一行的值表示的是其总数
        // 从第二行起则表示每一个非零值所在的行数、列数以及其对应的值）获取出来即可
        // 2.1、先遍历二维数组，得到非零值的个数（sum）
        int sum = 0;
        for (int i = 0; i < 11; i++) {
            for (int j = 0; j < 11; j++) {
                if (chessArr1[i][j] != 0) {
                    sum++;
                }
            }

        }
        //2.2、创建稀疏数组（易知，行数由非零值决定，有几个非零值就得有几行，但由于第一行要单独放置行数、列数、非零值个数，因此行数要比sum值多一行，列数是固定的，就是3列）
        //由于列是固定的，因此只需要创建行变量，则相当于创建一维数组
        int[][] sparseArr = new int[sum + 1][3];

        //先给第一行赋值
        sparseArr[0][0] = 11;//11行
        sparseArr[0][1] = 11;//11列
        sparseArr[0][2] = sum;//非零值的个数

        //再遍历原始二维数组，把非零值存入其中
        int count = 0;
        for (int i = 0; i < 11; i++) {
            for (int j = 0; j < 11; j++) {
                if (chessArr1[i][j] != 0) {
                    count++;
                    sparseArr[count][0] = i;
                    sparseArr[count][1] = j;
                    sparseArr[count][2] = chessArr1[i][j];
                }
            }
        }
        System.out.println("就是换行啦----------------------------");
        System.out.println("得到的稀疏数组为：");
        //用于相当于是一维数组，因此不需要两层循环，一层即可,即遍历其行数即可
        for (int i = 0; i < sparseArr.length; i++) {//遍历行数
            System.out.printf("%d\t%d\t%d\t\n", sparseArr[i][0], sparseArr[i][1], sparseArr[i][2]);//这里的\t相当于按下一个Tab
            // 键，即空出8个字符宽度的空间，\n则代表（第一行输出结束后）换行
        }
        System.out.println("再次换行啦---------------------------");


        //3、再将此稀疏数组恢复成原始数组
        //3.1、先读取第一行，据此创建二维数组（的规模）
        int[][] chessArr2 = new int[sparseArr[0][0]][sparseArr[0][1]];

        //3.2、再读取后面的几行（从第二行开始，但下标从1开始），并把值赋给此二维数组
        for (int i = 1; i < sparseArr.length; i++) {
            chessArr2[sparseArr[i][0]][sparseArr[i][1]] = sparseArr[i][2];//第i行第2列的值即代表所在行和列对应的非零值，赋回去即可
        }
        //3.3、输出此恢复后的数组即可，因为是二维数组，因此可以继续用两层循环遍历，这里再次用增强的for循环
        System.out.println("我又来换行啦--------------------------------------");
        System.out.println("恢复后的二维数组为：");
        for (int[] row : chessArr2) {//这里的row是一个用于遍历每一行的变量，相当于在遍历一个一个的一维数组，因此类型为int[]，目标则是这个二维数组（ chessArr2）
            for (int data : row) {//这里的data是一个用于遍历每一行（即每一个一维数组）中的值的变量，因此类型为int型，目标则是每一行（即每一个一维数组row）
                System.out.printf("%d\t", data);
            }
            System.out.println("搞定啦，下班，耶耶耶耶耶------------------------");
        }

    }


}



