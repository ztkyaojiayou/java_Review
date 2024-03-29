package 数据结构与算法.离职后刷题.第四遍必会版;

public class demo107_回溯之单词搜索 {
    //先定义几个全局变量，目的是简化代码的编写，否则的话，在递归方法中需要带上这些变量，略显麻烦和臃肿！
    int rows;//行
    int cols;//列
    boolean[][] isVisited;//访问数组
    int curWordIndex = 0;//深度搜索得到的路径的下标（可以理解为长度，比下标好理解）

    public boolean exist(char[][] board, String word) {
//        if (board == null || board.length == 0 || board[0].length == 0) {
//            return false;
//        }
        rows = board.length;
        cols = board[0].length;
        isVisited = new boolean[rows][cols];
        //从原点开始搜索
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (dfs(board, i, j, word)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean dfs(char[][] board, int cur_row, int cur_col, String word) {
        //递归出口
        //1.目标单词找到（即当前字符串的最大长度=目标目标单词的最大长度），此时结束递归，返回true
        if (curWordIndex == word.length()-1) {//当当前单词的长度等于目标单词的长度时，就表示已经找到该单词了
            return true;
        }
        //2.若当前位置越界，当前位置的字母与目标单词的该位置的字母不相等，或当前位置已经访问过了时，都应该结束递归，此时返回false
        if (cur_row < 0 || cur_row > board.length - 1
                || cur_col < 0 || cur_col > board[0].length - 1
                || board[cur_row][cur_col] != word.charAt(curWordIndex)
                || isVisited[cur_row][cur_col]) {
            return false;
        }
        //做选择（两步）
        curWordIndex++;
        isVisited[cur_row][cur_col] = true;
        //下一层递归/即开始深度优先搜索
        boolean res = dfs(board, cur_row + 1, cur_col, word)
                || dfs(board, cur_row - 1, cur_col, word)
                || dfs(board, cur_row, cur_col + 1, word)
                || dfs(board, cur_row, cur_col - 1, word);
        //再看搜索结果
        if (!res) {
            //撤销
            curWordIndex--;
            isVisited[cur_row][cur_col] = false;
        }
        return res;
    }

    //自写一遍
    public boolean exist02(char[][] board, String word) {
        rows = board.length;
        cols = board[0].length;
        isVisited = new boolean[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (dfs02(board, i, j, word)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean dfs02(char[][] board, int i, int j, String word) {
        //递归出口
        if (curWordIndex == board.length) {
            return true;
        }

        if (i < 0 || i > board.length - 1 || j < 0 || j > board[0].length - 1
                || isVisited[i][j]
                || board[i][j] != word.charAt(curWordIndex)) {
            return false;
        }

        //决策
        isVisited[i][j] = true;
        //即搜索到的单词长度加1
        curWordIndex++;
        //下一轮递归
        boolean res02 = dfs02(board, i + 1, j, word)
                || dfs02(board, i - 1, j, word)
                || dfs02(board, i, j + 1, word)
                || dfs02(board, i, j - 1, word);
        //撤销
        if (!res02) {
            isVisited[i][j] = false;
            curWordIndex--;
        }
        return true;
    }
}
