import java.awt.Graphics;
import java.awt.Color;
import javax.swing.ImageIcon;

public class Boss{
	
	private int x, y, width, height, health, moveDownCount, randNum;
	private boolean zigzag, visible;
	private String gameMode;
	private ImageIcon blastoise, charizard, venusaur;
	
	public Boss(){
		x = -200;
		y = 0;
		width = 160;
		height = 170;
		health = 5;
		moveDownCount = 3;
		gameMode = "easy";
		
		randNum = (int)(Math.random()*2);
		if( randNum == 0 ){
			zigzag = true;
		} else {
			zigzag = false;
		}
		visible = true;
		
		blastoise = new ImageIcon("ImageAssets/Blastoise.png");
		charizard = new ImageIcon("ImageAssets/Charizard.png");
		venusaur = new ImageIcon("ImageAssets/Venusaur.png");
	}
	
	public void drawMe(Graphics g){
		if( gameMode == "easy" ){
			blastoise.paintIcon(null,g,x,y);
		} else if ( gameMode == "medium" ){
			charizard.paintIcon(null,g,x,y);
		} else if ( gameMode == "hard" ){
			venusaur.paintIcon(null,g,x,y);
		}
		
		//health bar
		if( health == 5 ){
			g.setColor(Color.green);
			for(int i = 0;i < 10;i ++){
				g.fillRect(x+40+(i*12),y-25,10,10);
			}
		} else if ( health == 4 ){
			g.setColor(Color.green);
			for(int i = 0;i < 8;i ++){
				g.fillRect(x+40+(i*12),y-25,10,10);
			}
		} else if ( health == 3 ){
			g.setColor(Color.yellow);
			for(int i = 0;i < 6;i ++){
				g.fillRect(x+40+(i*12),y-25,10,10);
			}
		} else if ( health == 2 ){
			g.setColor(Color.yellow);
			for(int i = 0;i < 4;i ++){
				g.fillRect(x+40+(i*12),y-25,10,10);
			}
		} else if ( health == 1 ){
			g.setColor(Color.red);
			for(int i = 0;i < 2;i ++){
				g.fillRect(x+40+(i*12),y-25,10,10);
			}
		} else if ( health <= 0 ){
			die();
		}
		
	}
	
	public void move(){
		if( zigzag ){
			x ++;
		} else {
			x --;
		}
		if( x == 600 ){
			zigzag = false;
		} else if ( x == 0 ){
			zigzag = true;
		}
		if( gameMode == "easy" || gameMode == "hard" ){
			if( y < 600 ){
				if( moveDownCount == 3 ){
					y ++;
					moveDownCount = 1;
				} else {
					moveDownCount ++;
				}
			}
		} else if ( gameMode == "medium" ){
			if( y < 600 ){
				y ++;
			}
		}
	}
	
	public void reset(){
		x = 350;
		y = 0;
		randNum = (int)(Math.random()*2);
		if( randNum == 0 ){
			zigzag = true;
		} else {
			zigzag = false;
		}
		health = 5;
		visible = true;
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
		if( y > 399 ){
			return true;
		}
		return false;
	}
	
	public void loseHealth(){
		health --;
	}
	
	public void updateGameMode(String mode){
		gameMode = mode;
		if( gameMode == "easy" ){
			width = 160;
			height = 170;
		} else if ( gameMode == "medium" ){
			width = 200;
			height = 200;
		} else if ( gameMode == "hard" ){
			width = 200;
			height = 200;
		} 
	}
	
}