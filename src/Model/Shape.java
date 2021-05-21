package Model;

import java.awt.*;

public interface Shape {
    public void move(int x,int y);
    /*
     * hàm chuyển vị trí hình sang vị trí (x;y)
     */
    public void rotate();
    /*
     * hàm xoay hình
     */
    public void paint(Graphics g);
    /*
     * hàm vẽ hình
     */
    public int lastvalue();
    /*
     * hàm trả về giá trị y max của hình
     */
    public int maxright();
    /*
     * hàm trả về giá trị x max bên phải của hình
     */
    public int maxleft();
    /*
     * hàm trả về max giá trị bên trái của hình
     */
    public boolean touch(Square game[][]);
    /*
     * hàm kiểm tra xem có bị đè, trùng giá trị k? theo hàng dọc
     * vói giá trị tiếp theo, hay ô kế tiếp có trùng hay không?
     * true là trùng, false là không
     * hay còn đi xuống được nữa hay không?
     */
    public boolean touchl(Square game[][]);
    /*
     * vẫn giống hàm touch, nhưng theo hàng ngang sang trai
     * hay có sang ngang được nữa hay k?
     */
    public boolean touchr(Square game[][]);
    /*
     * vẫn giống hàm touch, nhưng theo hàng ngang sang trai
     * hay có sang ngang được nữa hay k?
     */
    public void addvalue(Square game[][]);
    /*
     * thêm giá trị vào bảng quản lý
     */
    public int rotate(int value,Square game[][]);
    /*
     * xoay theo vị trí giá trị
     * va tra ve xrun
     */
    public boolean isrotate(Square game[][]);
    /*
     * kiểm tra xem xem nếu xoay thì có xoay được không?
     * true la xoay duoc
     * false la khong xoay duoc
     */

}
