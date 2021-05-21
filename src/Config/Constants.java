package Config;

public class Constants {
    public static final int WIDTH = 480; // Chiều rộng của khung giao diện
    public static final int HEIGHT = 481; // Chiều dài của khung giao diện
    public static final int TIME = 500; // Đỗ trễ để các viên gạch di chuyển

    // Cấu hình
    public static final int sizex = 240; // Chiều rộng của màn hình chơi game (Nơi để thực hiện xếp gạch)
    public static final int sizey = 480; // Chiều dài của màn hình chơi game
    public static final int size = 20; // chiều dài và chiều rộng của từng ô lưới
    //
    public static final int maxX = sizex / size; // Số ô lưới xếp theo chiều ngang
    public static final int maxY = sizey / size; // Số ô lưới xếp theo chiều dọc

    // Các biến cấu hình để kết nối với MySQL
    public static final String TETRIS_DB_DRIVER = "com.mysql.cj.jdbc.Driver";
    public static final String TETRIS_URL = "jdbc:mysql://localhost:3306/tetris_game";
    public static final String TETRIS_USER = "root";
    public static final String TETRIS_PASSWORD = "";

}
