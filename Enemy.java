import java.awt.Graphics;
import java.awt.Color;


public class Enemy{
	
	private int x, y, width, height;
	private Color green;
	private boolean visible, zigzag;
	
	public Enemy(int x){
		this.x = x;
		y = 0;;
		this.width = 50;
		this.height = 50;
		
		green =  new Color(0,255,00);
		
		visible = true;
		if( (int)(Math.random()*2) == 1 ){
			zigzag = true;
		} else {
			zigzag = false;
		}
	}
	
	
	public void drawMe(Graphics g){
		if( visible ){
			g.setColor(green);
			g.fillRect(x,y,width,height);
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
		} else if ( x == 50 ){
			zigzag = true;
		}
		if( y < 600 ){
			y ++;
		}
	}
}