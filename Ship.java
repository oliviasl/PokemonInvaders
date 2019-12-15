import java.awt.Color;
import java.awt.Graphics;
import javax.swing.ImageIcon; 

public class Ship{
    
	private int x, y, width, height, lives, originalX, originalY;
    private Color blue;
    private ImageIcon pikachu;
     
    public Ship(int x, int y){    
        this.x = x;
        this.y = y;
        originalX = x;
        originalY = y;
        this.width = 65;
        this.height = 65;
        
        pikachu = new ImageIcon("ImageAssets/Pikachu.png");
         
        blue = new Color(0,0,255);
		lives = 3;
    }
     
 
    public void drawMe(Graphics g){
        pikachu.paintIcon(null,g,x,y);
    }
	
	
	public void moveLeft(){
		if( x > 0 ){
			x -= 7;
		}
	}
	
	
	public void moveRight(){
		if( y < 800 ){
			x += 7;
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
	
	public int getLives(){
		return lives;
	}
	
	public void reset(){
		x = originalX;
		y = originalY;
	}
	
	public boolean checkCollision(Enemy e){
		if( e.getVisible() ){
			//ship
			int sX = x + 2;
			int sY = y + 2;
			int sWidth = width;
			int sHeight = height;
		
			//enemy
			int tX = e.getX();
			int tY = e.getY();
			int tWidth = e.getWidth();
			int tHeight = e.getHeight();

			if( sX+sWidth >= tX && sX <= tX + tWidth  &&  sY+sHeight >= tY && sY <= tY + tHeight ){
				//set visible of enemy to false
				lives --;
				return true;
			}
		}
		return false;
	}
	
	public boolean checkCollisionBoss(Boss b){
		if( b.getVisible() ){
			//ship
			int sX = x;
			int sY = y;
			int sWidth = width;
			int sHeight = height;
		
			//boss
			int tX = b.getX() + 40;
			int tY = b.getY();
			int tWidth = b.getWidth();
			int tHeight = b.getHeight();
			
			if( sX+sWidth >= tX && sX <= tX + tWidth  &&  sY+sHeight >= tY && sY <= tY + tHeight ){
				//set visible of enemy to false
				lives --;
				return true;
			}
		}
		return false;
	}
	
	public void changeLives(int num){
		lives = num;
	}
     
}