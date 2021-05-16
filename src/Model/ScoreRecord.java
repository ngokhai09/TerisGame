package Model;

import Config.Constants;

import java.io.IOException;
import java.sql.*;

public class ScoreRecord{
    private int highsocre;
    private int score;

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public static void loadDriver() throws IOException {
        try {
            Class.forName(Constants.TETRIS_DB_DRIVER);
        } catch (ClassNotFoundException e) {
            throw new IOException("Can not load database driver!");
        }
    }

    public void readHightScore() throws SQLException{
        Connection conn = null;
        Statement stmt = null;
        try{
            conn = DriverManager.getConnection(Constants.TETRIS_URL, Constants.TETRIS_USER, Constants.TETRIS_PASSWORD);
            stmt = conn.createStatement();
            String sql = "SELECT Score FROM `scorerecord` ORDER BY Score DESC LIMIT 1;";
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()){
                String value = rs.getString("Score");
                highsocre = Integer.parseInt(value);
            }

        }finally {
            if (stmt != null) {
                stmt.close();
            }
            if (conn != null) {
                conn.close();
            }
        }

    }
    public void writeHightScore() throws SQLException{
        Connection conn = null;
        Statement stmt = null;
        try{
            conn = DriverManager.getConnection(Constants.TETRIS_URL, Constants.TETRIS_USER, Constants.TETRIS_PASSWORD);
            stmt = conn.createStatement();
            String sql = "INSERT INTO `scorerecord`(`Score`) VALUES ("+ score +");";
            stmt.executeUpdate(sql);
        }finally {
            if (stmt != null) {
                stmt.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
    }

    public int getHighsocre() {
        return highsocre;
    }

    public void setHighsocre() throws SQLException {
        readHightScore();
    }
}
