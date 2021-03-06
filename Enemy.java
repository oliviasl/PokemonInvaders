import java.awt.Graphics;
import java.awt.Color;
import javax.swing.ImageIcon;


public class Enemy{
	
	private int x, y, width, height;
	private int resetX, resetY, level, moveDownCount, moveDownCount2;
	private Color green;
	private boolean visible, zigzag;
	private String gameMode;
	private ImageIcon squirtle, wartortle;
	private ImageIcon charmander, charmeleon;
	private ImageIcon bulbasaur, ivysaur;
	
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
		level = 1;
		gameMode = "easy";
		
		//image icon
		squirtle = new ImageIcon("ImageAssets/Squirtle.png");
		wartortle = new ImageIcon("ImageAssets/Wartortle.png");
		charmander = new ImageIcon("ImageAssets/Charmander.png");
		charmeleon = new ImageIcon("ImageAssets/Charmeleon.png");
		bulbasaur = new ImageIcon("ImageAssets/Bulbasaur.png");
		ivysaur = new ImageIcon("ImageAssets/Ivysaur.png");
		
		//set moving variables
		moveDownCount = 1;
		moveDownCount2 = 1;
		if( (int)(Math.random()*2) == 1 ){
			zigzag = true;
		} else {
			zigzag = false;
		}
	}
	
	
	public void drawMe(Graphics g){
		if( visible ){
			if( gameMode == "easy" ){
				if( level == 1 ){
					squirtle.paintIcon(null,g,x,y);
				} else if ( level == 2 ){
					wartortle.paintIcon(null,g,x,y);
				}
			} else if ( gameMode == "medium" ){
				if( level == 1 ){
					charmander.paintIcon(null,g,x,y);
				} else if ( level == 2 ){
					charmeleon.paintIcon(null,g,x,y);
				}
			} else if ( gameMode == "hard" ){
				if( level == 1 ){
					bulbasaur.paintIcon(null,g,x,y);
				} else if ( level == 2 ){
					ivysaur.paintIcon(null,g,x,y);
				}
			}
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
				if( moveDownCount2 == 2 ){
					y ++;
					moveDownCount2 = 1;
				} else {
					moveDownCount2 = 2;
				}
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
		x = 850;
		y = 0;
	}
	
	public void setLevel(int num){
		level = num;
	}
	
	public void updateGameMode(String mode){
		gameMode = mode;
	}
	
}