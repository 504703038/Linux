import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.io.Serializable;
// ��ͼ�࣬����ͼ�ε�Ԫ���õ����л��ӿڣ�����ʱ����
class drawings implements Serializable{
int x1 , y1 , x2 , y2 ; // ������������
int R, G, B; // ����ɫ������
float stroke ; // ����������ϸ����
int type ; // ������������
String s1 ;
String s2 ; // ��������������
int thickness ;
void draw(Graphics2D g2d){
}; // �����ͼ����
}
//���ֻ���ͼ�ε�Ԫ�����࣬���̳��Ը��� drawings
//ֱ����
class Line extends drawings {
void draw(Graphics2D g2d){
g2d.setPaint( new Color( R, G, B));
g2d.setStroke( new
BasicStroke( stroke ,BasicStroke. CAP_ROUND,BasicStroke. JOIN_BEVEL ));
g2d.drawLine( x1 , y1 , x2 , y2 );
}
}
//������
class Rect extends drawings{
void draw(Graphics2D g2d){
g2d.setPaint( new Color( R, G, B));
g2d.setStroke( new BasicStroke( stroke ));
g2d.drawRect(Math. min ( x1 , x2 ),Math. min ( y1 , y2 ),
Math. abs ( x1 - x2 ),Math. abs ( y1 - y2 ));
}
}
//ʵ�ľ�����
class fillRect extends drawings{
void draw(Graphics2D g2d){
g2d.setPaint( new Color( R, G, B));
g2d.setStroke( new BasicStroke( stroke ));
g2d.fillRect(Math. min ( x1 , x2 ),Math. min ( y1 , y2 ),Math. abs ( x1 -
x2 ),Math. abs ( y1 - y2 ));
}
}
//��Բ��
class Oval extends drawings{
void draw(Graphics2D g2d){
g2d.setPaint( new Color( R, G, B));
g2d.setStroke( new BasicStroke( stroke ));
g2d.drawOval(Math. min ( x1 , x2 ),Math. min ( y1 , y2 ),Math. abs ( x1 -
x2 ),Math. abs ( y1 - y2 ));
}
}
//ʵ����Բ
class fillOval extends drawings{
void draw(Graphics2D g2d){
g2d.setPaint( new Color( R, G, B));
g2d.setStroke( new BasicStroke( stroke ));
g2d.fillOval(Math. min ( x1 , x2 ),Math. min ( y1 , y2 ),Math. abs ( x1 -
x2 ),Math. abs ( y1 - y2 ));
}
}
//Բ��
class Circle extends drawings{
void draw(Graphics2D g2d) {
g2d.setPaint( new Color( R, G, B));
g2d.setStroke( new BasicStroke( stroke ));
g2d.drawOval(Math. min ( x1 , x2 ),Math. min ( y1 , y2 ),
Math. max (Math. abs ( x1 - x2 ),Math. abs ( y1 - y2 )),
Math. max (Math. abs ( x1 - x2 ),Math. abs ( y1 - y2 )));
}
}
// ʵ��Բ
class fillCircle extends drawings{
void draw(Graphics2D g2d){
g2d.setPaint( new Color( R, G, B));
g2d.setStroke( new BasicStroke( stroke ));
g2d.fillOval(Math. min ( x1 , x2 ),Math. min ( y1 , y2 ),
Math. max (Math. abs ( x1 - x2 ),Math. abs ( y1 - y2 )),
Math. max (Math. abs ( x1 - x2 ),Math. abs ( y1 - y2 )));
}
}
// Բ�Ǿ�����
class RoundRect extends drawings{
void draw(Graphics2D g2d){
g2d.setPaint( new Color( R, G, B));
g2d.setStroke( new BasicStroke( stroke ));
g2d.drawRoundRect(Math. min ( x1 , x2 ),Math. min ( y1 , y2 ),
Math. abs ( x1 - x2 ),Math. abs ( y1 - y2 ),50,35);
}
}
// ʵ��Բ�Ǿ�����
class fillRoundRect extends drawings{
void draw(Graphics2D g2d){
g2d.setPaint( new Color( R, G, B));
g2d.setStroke( new BasicStroke( stroke ));
g2d.fillRoundRect(Math. min ( x1 , x2 ),Math. min ( y1 , y2 ),
Math. abs ( x1 - x2 ),Math. abs ( y1 - y2 ),50,35);
}
}
// ��ʻ���
class Pencil extends drawings{
void draw(Graphics2D g2d){
g2d.setPaint( new Color( R, G, B));
g2d.setStroke( new BasicStroke( stroke ,
BasicStroke. CAP_ROUND,BasicStroke. JOIN_BEVEL ));
g2d.drawLine( x1 , y1 , x2 , y2 );
}
}
//3D ������
class Rect3D extends drawings{
void draw(Graphics2D g2d){
g2d.setPaint( new Color( R, G, B));
g2d.setStroke( new BasicStroke( stroke ));
g2d.draw3DRect(Math. min ( x1 , x2 ),Math. min ( y1 , y2 ),Math. abs ( x1 - x2 ),
Math. abs ( y1 - y2 ), false );
}
}
// ���� 3D������
class fillRect3D extends drawings{
void draw(Graphics2D g2d){
g2d.setPaint( new Color( R, G, B));
g2d.setStroke( new BasicStroke( stroke ));
g2d.fill3DRect(Math. min ( x1 , x2 ),Math. min ( y1 , y2 ),
Math. abs ( x1 - x2 ),Math. abs ( y1 - y2 ), false );
}
}
// ��������
class Cube extends drawings{
void draw(Graphics2D g2d){
g2d.setPaint( new Color( R, G, B));
g2d.setStroke( new BasicStroke( stroke ));
thickness =MyPaint. thickness ;
for ( int i = thickness - 1; i >= 0; i--){
g2d.fill3DRect(Math. min ( x1 , x2 ) + i, Math. min ( y1 , y2 ) - i,
Math. abs ( x1 - x2 ), Math. abs ( y1 - y2 ), true );
}
}
}
// ��Ƥ����
class Rubber extends drawings{
void draw(Graphics2D g2d){
g2d.setPaint( new Color(255,255,255));
g2d.setStroke( new BasicStroke( stroke +4,BasicStroke. CAP_ROUND,
BasicStroke. JOIN_BEVEL ));
g2d.drawLine( x1 , y1 , x2 , y2 );
}
}
// ����������
class Word extends drawings{
void draw(Graphics2D g2d){
g2d.setPaint( new Color( R, G, B));
g2d.setFont( new Font( s2 , x2 +y2 ,(( int ) stroke )*18));
if ( s1 != null )
g2d.drawString( s1 , x1 , y1 );
}
}