package 数据结构与算法.LeetCode题解.回溯_递归_记忆化搜索;

/**
 * 79. 单词搜索
 * 给定一个二维网格和一个单词，找出该单词是否存在于网格中。
 * 单词必须按照字母顺序，通过相邻的单元格内的字母构成，
 * 其中“相邻”单元格是那些水平相邻或垂直相邻的单元格。
 * 同一个单元格内的字母不允许被重复使用。
 *
 * 示例:
 * board =
 * [
 *   ['A','B','C','E'],
 *   ['S','F','C','S'],
 *   ['A','D','E','E']
 * ]
 *
 * 给定 word = "ABCCED", 返回 true
 * 给定 word = "SEE", 返回 true
 * 给定 word = "ABCB", 返回 false
 *
 * 提示：
 * board 和 word 中只包含大写和小写英文字母。
 * 1 <= board.length <= 200
 * 1 <= board[i].length <= 200
 * 1 <= word.length <= 10^3
 */
public class 单词搜索79 {
    int m;//行数
    int n;//列数
    boolean[][] isUsed;//只能定义在外面，不然就变成了局部变量，在其他方法中不能被访问
        //                  x-1,y(-1,0)(上）
        //x,y-1(0,-1)（左）    x,y（0,0)     x,y+1(0,1)（右）
        //                  x+1,y(1,0)（下）
        //定义一个二维数组，4行2列，表示当前点的四个位置，
        //其中前后位置可用x+1或x-1来表示，上下可用y+1或y-1来表示
        private int[][] direction = {{-1, 0}, {0, -1}, {0, 1}, {1, 0}};//这里只不过是用行的形式来写的
        private String word;
        private char[][] board;

        public boolean exist(char[][] board, String word) {
            m = board.length;//行数
            //特判
            if (m == 0) {
                return false;
            }
            n = board[0].length;//列数
            //定义一个boolean类型的数组用于标记每一个位置是否被使用过，
            //默认为未使用过，即false，若使用过，则把其置为true
             isUsed = new boolean[m][n];

             //该遍历的意义在于：先从（0,0）开始一直向其前后左右的每一个位置继续DFS搜索，匹配成功就可以直接返回结果，
            // 若没有匹配到，则通过该循环遍历，开始从第二个位置（0,1）开始，继续进行同样地DFS搜索操作，
            // 以此类推（即采用“先行后列”的原则固定起始点），直到某一次匹配成功为止
            for (int i = 0; i < m; i++) {//行遍历
                for (int j = 0; j < n; j++) {//列遍历
                    if (dfs(i, j, 0,board,word)) {//递归（即在起点已经确定了的情况下进行DFS深度搜索）
                        return true;//只要有一次递归匹配到了，就返回true
                    }
                }
            }
            //若二维网格上的所有位置都匹配完之后都没有找到的话，则返回false。
            return false;
        }

    /**
     * 具体的递归函数
     * @param i 二维网格的行数
     * @param j 二维网格的列数
     * @param start 从第几个字母开始比对（前面的字母已经匹配成功）
     * @param board 题给的二维网格
     * @param word 题给的目标单词
     * @return
     */
        boolean dfs(int i, int j, int start,char[][] board,String word) {
            //递归结束的条件
            if (start == word.length() - 1) {
                return board[i][j] == word.charAt(start);
            }
            //一般情况
            //做选择
            if (board[i][j] == word.charAt(start)) {//当前位置的字母对应相等时，才有意义；否则，直接返回false
                isUsed[i][j] = true;//把该位置标记为已经使用过
                //开始循环其前后左右四个位置并递归（四次），只要有一次是成功的，就返回true
                for (int k = 0; k < 4; k++) {
                    //计算下一个/新坐标
                    int newX = i + direction[k][0];
                    int newY = j + direction[k][1];
                    if (inArea(newX, newY) && !isUsed[newX][newY]) {//剪枝，即只有在当新的坐标没有越界且没有被使用过的情况下才进行递归，否则没有意义
                        if (dfs(newX, newY, start + 1,board,word)) {//调用递归函数（此时第一个字母已经匹配成功，开始匹配第二个字母
                            return true;//若找到，则返回true
                        }
                    }
                }
                //撤回，即把刚才已经被访问过了的位置再次设为false，表示可以继续使用，用于回溯
                isUsed[i][j] = false;
            }
            //否则，说明当前位置对应的字母不相等，则直接返回false，后面的字母是否还相等根本不用再考虑。
                return false;
        }

        //用于判断坐标是否越界
        private boolean inArea(int x, int y) {
            return x >= 0 && x < m && y >= 0 && y < n;
        }

        //测试
        public static void main(String[] args) {

//        char[][] board =
//                {
//                        {'A', 'B', 'C', 'E'},
//                        {'S', 'F', 'C', 'S'},
//                        {'A', 'D', 'E', 'E'}
//                };
//
//        String word = "ABCCED";

            char[][] board = {{'a', 'b'}};
            String word = "ba";
            单词搜索79 solution = new 单词搜索79();
            boolean exist = solution.exist(board, word);
            System.out.println(exist);
        }
    }

