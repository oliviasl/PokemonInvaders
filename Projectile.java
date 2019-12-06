import java.awt.Color;
import java.awt.Graphics;
import javax.swing.ImageIcon;
 
public class Projectile{
    
	private int x, y, width, height;
    private Color green;
	private boolean visible;
	private ImageIcon pokeball;
 
     
    public Projectile(int x, int y){
        this.x = x;
        this.y = y;
        this.width = 20;
        this.height = 20;
		
		visible = false;
        
        green = new Color(255,0,0);
        
        pokeball = new ImageIcon("ImageAssets/Pokeball.png");
    }
 
 
    public void drawMe(Graphics g){    
		if( visible ){
			pokeball.paintIcon(null,g,x,y);
		}
    }
     
	 
    public void moveUp(){
		if( visible ){
			y -= 5;
		}
		if( y < -20 ){
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
	
	public void checkCollisionBoss(Boss b){
		if( b.getVisible() ){
			//projectile
			int pX = x;
			int pY = y;
			int pWidth = width;
			int pHeight = height;
		
			//enemy
			int tX = b.getX();
			int tY = b.getY();
			int tWidth = b.getWidth();
			int tHeight = b.getHeight();

			if( pX+pWidth >= tX && pX <= tX + tWidth  &&  pY+pHeight >= tY && pY <= tY + tHeight ){
				//set visible of boss to false
				b.die();
			}
		}
	}
 
 
}