package cn.olair.leetcode;

import org.junit.Test;

/**
 * 二维平面上的回溯
 * 给定一个2D屏幕和一个单词，寻找单词是否在这个网格中。
 * 单词可以从每个相邻格子中的字母组合而成，这个相邻只能是水平或者竖直方向的。
 * <p>
 * 比如给定：
 * board =
 * [
 * ['A','B','C','E'],
 * ['S','F','C','S'],
 * ['A','D','E','E']
 * ]
 * 单词 = "ABCCED", -> 返回 true,
 * 单词 = "SEE",    -> 返回 true,
 * 单词 = "ABCB",   -> 返回 false.
 * <p>
 * Created by olair on 18.1.2.
 */

public class WordSearch {

    public boolean exist(char[][] board, String word) {

        for (int y = 0; y < board.length; y++) {
            for (int x = 0; x < board[y].length; x++) {
                if (mgPath(board, x, y, word, 0)) {
                    return true;
                }
            }
        }
        return false;
    }

    // 深度优先寻路算法
    private boolean mgPath(char[][] board, int startX, int startY, String word, int index) {

        if (index == word.length()) {
            return true;
        }

        if (startX < 0 || startY < 0 || startX >= board[0].length || startY >= board.length) {
            return false;
        }

        if (board[startY][startX] != word.charAt(index) || board[startY][startX] == ' ') {
            return false;
        }

        char x = board[startY][startX];
        board[startY][startX] = ' ';

        if (mgPath(board, startX + 1, startY, word, index + 1))
            return true;
        if (mgPath(board, startX, startY + 1, word, index + 1))
            return true;
        if (mgPath(board, startX - 1, startY, word, index + 1))
            return true;
        if (mgPath(board, startX, startY - 1, word, index + 1))
            return true;

        board[startY][startX] = x;

        return false;
    }


    @Test
    public void test() {
        char[][] board = {
                {'C', 'A', 'A'},
                {'A', 'A', 'A'},
                {'B', 'C', 'D'}
        };

        System.out.print(exist(board, "AAB"));

    }

}
