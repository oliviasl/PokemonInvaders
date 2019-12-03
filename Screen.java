import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
 
 
public class Screen extends JPanel implements KeyListener{
 
    private Projectile p1;
    private Ship s1;
	private Enemy[] enemies;
	private boolean visible;
	private int score, lives;
    
	
    public Screen(){
        s1 = new Ship(375,500);
        p1 = new Projectile(50,300);  

		enemies = new Enemy[5];
		for(int i = 0;i < enemies.length;i ++){
			enemies[i] = new Enemy((i+1)*125);
		}

		visible = false;
		score = 0;
		lives = 3;
		
		setFocusable(true);
		addKeyListener(this);
    }
 
 
    public Dimension getPreferredSize() {
        //Sets the size of the panel
            return new Dimension(800,600);
    }
     
	 
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
		
		//background
        g.setColor(Color.white);
        g.fillRect(0,0,800,600);     
     
        //Draw ship
        s1.drawMe(g);
		
        //Draw Projectile
		p1.drawMe(g);
		
		//Draw Enemies
		for( Enemy each : enemies ){
			each.drawMe(g);
			each.move();
		}
		
		//show score
		score = 0;
		for( Enemy each : enemies ){
			if( !each.getVisible() ){
				score += 1;
			}
		}
		g.setColor(Color.black);
		g.drawString("Score: " + score,700,500);
		g.drawString("Lives: " + lives,700,515);
    } 
 
 
    public void animate(){ 
        while(true){
			//projectile
			p1.moveUp();
			for( Enemy each : enemies ){
				p1.checkCollision(each);
			}
			
			//check for death
			for( Enemy each : enemies ){
				s1.checkCollision(each);
			}
			
            //wait for .01 second
            try {
                Thread.sleep(10);
            } catch(InterruptedException ex) {
                Thread.currentThread().interrupt();
            }
            //repaint the graphics drawn
            repaint();
        }
    }
     
	 
	public void keyPressed(KeyEvent e){
		if( e.getKeyCode() == 32 /*spacebar*/ ){
			//change projectile position to match ship's position
			p1.setPosition(s1.getX()+25,s1.getY());
			//shoot projectile
			p1.shoot();
		} else if( e.getKeyCode() == 37 /*left arrow*/ ){
			//move player up
			s1.moveLeft();
		} else if( e.getKeyCode() == 39 /*right arrow*/ ){
			//move player down
			s1.moveRight();
		}
	}
	
	//override
	public void keyReleased(KeyEvent e){
	}
	
	//override
	public void keyTyped(KeyEvent e){
	}
 
}