import java.awt.Color;
import java.awt.Graphics;
 
public class Ship{
    
	private int x, y, width, height, lives;
    private Color blue;
     
    public Ship(int x, int y){    
        this.x = x;
        this.y = y;
        this.width = 50;
        this.height = 50;
         
        blue = new Color(0,0,255);
		lives = 3;
    }
     
 
    public void drawMe(Graphics g){
        g.setColor(blue);
        g.fillRect(x,y,width,height);
    }
	
	
	public void moveLeft(){
		if( x > 0 ){
			x -= 10;
		}
	}
	
	
	public void moveRight(){
		if( y < 800 ){
			x += 10;
		}
	}
	
	
	public int getX(){
		return x;
	}
	
	
	public int getY(){
		return y;
	}
	
	public int getLives(){
		return lives;
	}
	
	public void shipDie(){
		lives --;
	}
	
	public void checkCollision(Enemy e){
		if( e.getVisible() ){
			//ship
			int sX = x;
			int sY = y;
			int sWidth = width;
			int sHeight = height;
		
			//enemy
			int tX = e.getX();
			int tY = e.getY();
			int tWidth = e.getWidth();
			int tHeight = e.getHeight();

			if( sX+sWidth >= tX && sX <= tX + tWidth  &&  sY+sHeight >= tY && sY <= tY + tHeight ){
				//set visible of enemy to false
				shipDie();
			}
		}
	}
	
	public void changeLives(int num){
		lives = num;
	}
     
}