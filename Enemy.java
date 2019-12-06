import java.awt.Graphics;
import java.awt.Color;
import javax.swing.ImageIcon;


public class Enemy{
	
	private int x, y, width, height;
	private int resetX, resetY;
	private Color green;
	private boolean visible, zigzag, moveDownCount;
	private ImageIcon squirtle;
	
	public Enemy(int x){
		//set dimensions
		this.x = x;
		y = 0;;
		this.width = 50;
		this.height = 50;
		resetX = this.x;
		resetY = this.y;
		
		//set image logistics
		green =  new Color(0,255,00);
		visible = true;
		
		//image icon
		squirtle = new ImageIcon("ImageAssets/Squirtle.png");
		
		//set moving variables
		moveDownCount = true;
		if( (int)(Math.random()*2) == 1 ){
			zigzag = true;
		} else {
			zigzag = false;
		}
	}
	
	
	public void drawMe(Graphics g){
		if( visible ){
			squirtle.paintIcon(null,g,x,y);
		}
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
	
	
	public boolean getVisible(){
		return visible;
	}
	
	
	public void die(){
		visible = false;
	}
	
	public boolean getAlive(){
		return visible;
	}
	
	public void move(){
		if( zigzag ){
			x ++;
		} else {
			x --;
		}
		if( x == 750 ){
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
		x = resetX;
		y = resetY;
		visible = true;
	}
	
	public boolean touchBottom(){
		if( y == 550 && visible ){
			return true;
		} else {
			return false;
		}
	}
	
	public void gameOver(){
		x = -50;
		y = 0;
	}
	
}