import java.awt.Color;
import java.awt.Graphics;

public class EnemyProjectile{
	
	private int x, y, width, height, moveCount, level;
	private Color green;
	private boolean visible;
	
	public EnemyProjectile(int x,int y){
		this.x = x;
		this.y = y;
		width = 10;
		height = 10;
		moveCount = 2;
		level = 1;
		
		visible = false;
		
		green = new Color(2,89,25);
	}
	
	public void drawMe(Graphics g){    
		if( visible ){
			g.setColor(green);
			g.fillOval(x,y,10,10);
			g.setColor(Color.green);
			g.fillOval(x+2,y+2,6,6);
		}
    }
	
	public void setPosition(int x, int y){
		if( !visible ){
			this.x = x;
			this.y = y;
		}
	}
	
	public void hit(){
		visible = false;
	}
     
    public void shoot(){
		visible = true;
	}
	
	public boolean getVisible(){
 		return visible;
 	}
	
	public boolean checkCollision(Ship s){
		if( visible ){
			//projectile
			int pX = x;
			int pY = y;
			int pWidth = width;
			int pHeight = height;
		
			//ship
			int tX = s.getX();
			int tY = s.getY();
			int tWidth = s.getWidth();
			int tHeight = s.getHeight();

			if( pX+pWidth >= tX && pX <= tX + tWidth  &&  pY+pHeight >= tY && pY <= tY + tHeight ){
				//set visible of enemy to false
				visible = false;
				return true;
			}
		}
		return false;
	}
	
	public void moveDown(){
		if( level == 1 || level == 2 ){
			if( moveCount == 5 ){
				if( visible ){
					y += 5;
				}
				if( y > 600 ){
					visible = false;
				}
				moveCount = 1;
			} else {
				moveCount ++;
			}
		} else if ( level == 3 ){
			if( visible ){
				y += 5;
			}
			if( y > 600 ){
				visible = false;
			}
		}
	}
	
	public void updateLevel(int l){
		level = l;
	}
	
}