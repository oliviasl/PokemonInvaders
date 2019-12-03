import java.awt.Color;
import java.awt.Graphics;
 
public class Projectile{
    
	private int x, y, width, height;
    private Color green;
	private boolean visible;
 
     
    public Projectile(int x, int y){
        this.x = x;
        this.y = y;
        this.width = 10;
        this.height = 10;
		
		visible = false;
        
        green = new Color(255,0,0);
    }
 
 
    public void drawMe(Graphics g){    
		if( visible ){
			g.setColor(green);
			g.fillOval(x,y,width,height);
		}
    }
     
	 
    public void moveUp(){
		if( visible ){
			y -= 5;
		}
		if( y < 0 ){
			visible = false;
		}
	}
     
     
    public void shoot(){
		visible = true;
	}
	
	
	public void setPosition(int x, int y){
		if( !visible ){
			this.x = x;
			this.y = y;
		}
	}
     
	
    public void checkCollision(Enemy e){
		if( e.getVisible() ){
			//projectile
			int pX = x;
			int pY = y;
			int pWidth = width;
			int pHeight = height;
		
			//enemy
			int tX = e.getX();
			int tY = e.getY();
			int tWidth = e.getWidth();
			int tHeight = e.getHeight();

			if( pX+pWidth >= tX && pX <= tX + tWidth  &&  pY+pHeight >= tY && pY <= tY + tHeight ){
				//set visible of enemy to false
				e.die();
			}
		}
	}
 
 
}