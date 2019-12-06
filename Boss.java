import java.awt.Graphics;
import java.awt.Color;
import javax.swing.ImageIcon;

public class Boss{
	
	private int x, y, width, height;
	private boolean zigzag, visible, moveDownCount;
	private Color purple;
	private ImageIcon blastoise;
	
	public Boss(){
		x = -200;
		y = 0;
		width = 160;
		height = 170;
		
		zigzag = true;
		visible = true;
		moveDownCount = true;
		
		purple = new Color(102,0,153);
		
		blastoise = new ImageIcon("ImageAssets/Blastoise.png");
	}
	
	public void drawMe(Graphics g){
		blastoise.paintIcon(null,g,x,y);
		/* 
		g.setColor(purple);
		g.fillRect(x,y,width,height);
		*/
	}
	
	public void move(){
		if( zigzag ){
			x ++;
		} else {
			x --;
		}
		if( x == 700 ){
			zigzag = false;
		} else if ( x == 0 ){
			zigzag = true;
		}
		if( y < 600 ){
			if( moveDownCount ){
				y ++;
				moveDownCount = false;
			} else {
				moveDownCount = true;
			}
		}
	}
	
	public void reset(){
		x = 350;
		y = 0;
		zigzag = true;
	}
	
	public void die(){
		visible = false;
	}
	
	public boolean getVisible(){
		return visible;
	}
	
	public int getX(){
		return x;
	}
	
	
	public int getY(){
		return y;
	}
	
	
	public int getWidth(){
		return width;
	}
	
	
	public int getHeight(){
		return height;
	}
	
	public void gameOver(){
		x = -200;
		y = 0;
	}
	
	public boolean touchBottom(){
		if( y > 499 ){
			return true;
		}
		return false;
	}
	
}