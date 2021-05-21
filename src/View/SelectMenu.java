package View;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SelectMenu extends JMenuBar{
    private JMenu fileMenu;
    private JMenu helpMenu;
    //file
    private JMenuItem NewGame; // new game
    private JMenuItem Exit; // exit

    public JMenuItem getNewGame() {
        return NewGame;
    }

    //Help
    private JMenuItem Tutorial;
    private final ButtonGroup buttonGroup = new ButtonGroup();
    public SelectMenu()
    {
        super();
        this.init();
    }
    public void init()
    {
        fileMenu = new JMenu("File");
        helpMenu = new JMenu("Help");
        this.add(fileMenu);
        this.add(helpMenu);
        // File
        NewGame = new JMenuItem("New");
        fileMenu.add(NewGame);
        fileMenu.addSeparator();
        Exit = new JMenuItem("Exit");
        fileMenu.add(Exit);
        // Gán hành động thoát cho nút exit
        Exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);

            }
        });

        //Help
        Tutorial = new JMenuItem("Tutorial");
        helpMenu.add(Tutorial);
        // Gán hành động khi ấn vào nút Tutorial
        Tutorial.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String s="Cách chơi:\n"
                        + "- Nhiệm vụ của người chơi lúc này chính là tìm một vị trí thích hợp để viên gạch hạ xuống khớp với những viên ở dưới mà không để khoảng trống nào bên trong khối hình đã được xếp\n"
                        + "- Người chơi có thể sử dụng các nút điều khiển trong thiết bị chơi game của mình bằng để điều chỉnh cho viên gạch đó được nằm ngang hay nằm dọc tùy theo tình hình của ván game lúc đó.\n"
                        + "- Với mỗi dòng nằm ngang, nếu bạn khéo léo xếp viên gạch kín và phủ đầy 10 ô này thì ngay lập tức hàng gạch đó sẽ biến mất, và bạn được cộng 500 điểm"
                        + "\n- Trò chơi kết thúc khi hàng đầu tiên bị "
                        + "lấp bởi 1 ô vuông màu.";
                JOptionPane.showMessageDialog(null,s,"Hướng dẫn",JOptionPane.INFORMATION_MESSAGE);

            }
        });
    }
}
