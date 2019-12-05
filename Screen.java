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
	private Boss boss;
	private boolean visible;
	private int score, lives, level, betweenLevelDelay;
    
	
    public Screen(){
        s1 = new Ship(375,500);
        p1 = new Projectile(0,0);  
		boss = new Boss();

		enemies = new Enemy[10];
		for(int i = 0;i < enemies.length;i ++){
			enemies[i] = new Enemy((i+1)*60);
		}

		visible = false;
		lives = 3;
		level = 1;
		score = 0;
		betweenLevelDelay = 0;
		
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
		if( level == 1 ){
			for(int i = 0;i < enemies.length;i += 2){
				enemies[i].drawMe(g);
				enemies[i+1].die();
			}
		} else if ( level == 2 ){
			for( Enemy each : enemies ){
				each.drawMe(g);
			}
		} else if ( level == 3 ){
			boss.drawMe(g);
		}
		
		//show score
		g.setColor(Color.black);
		g.drawString("Score: " + score,700,500);
		
		//show lives
		if( level == 1 || level == 2 ){
			if( lives > s1.getLives() ){
				for( Enemy each : enemies ){
					each.reset();
				}
			}
		} else if ( level == 3 ){
			if( lives > s1.getLives() ){
				boss.reset();
			}
		}
		lives = s1.getLives();
		g.drawString("Lives: " + lives,700,515);
		
		//show level
		g.setColor(Color.black);
		g.drawString("Level: " + level,700,530);
		
		//Game over
		if( lives == 0 ){
			g.setColor(Color.red);
			g.drawString("GAME OVER",375,300);
		}
		
		//level cleared and move to level 2
		if( score == 5 && level == 1 ){
			g.setColor(Color.green);
			g.drawString("LEVEL 1 CLEARED",350,300);
		} else if ( score == 10 && level == 2 ){
			g.setColor(Color.green);
			g.drawString("LEVEL 2 CLEARED",350,300);
		} else if ( score == 1 && level == 3 ){
			g.setColor(Color.green);
			g.drawString("LEVEL 3 CLEARED",350,300);
		}
    } 
 
 
    public void animate(){ 
        while(true){
			
			//projectile
			p1.moveUp();
			for( Enemy each : enemies ){
				p1.checkCollision(each);
			}
			
			//update score
			score = 0;
			if( level == 1 ){
				for(int i = 0;i < enemies.length;i += 2){
					if( !enemies[i].getVisible() ){
						score += 1;
					}
				}
			} else if ( level == 2 ){
				for( Enemy each : enemies ){
					if( !each.getVisible() ){
						score += 1;
					}
				}
			} else if ( level == 3 ){
				if( !boss.getVisible() ){
					score += 1;
				}
			}
			
			//check for death and game over
			if( level == 1 || level == 2 ){
				if( lives > 0 ){
					for( Enemy each : enemies ){
						each.move();
						//checking if it touches the bottom
						if( each.touchBottom() ){
							lives --;
							s1.changeLives(lives);
							for( Enemy each2 : enemies ){
								each2.reset();
							}
						}
						//checking if it touches the player
						s1.checkCollision(each);
					}
				} else /*no lives left*/{
					for( Enemy each : enemies ){
						each.gameOver();
					}
				}
			} else if ( level == 3 ){
				if( lives > 0 ){
					boss.move();
					if( boss.touchBottom() ){
						lives --;
						s1.changeLives(lives);
						boss.reset();
						s1.checkCollisionBoss(boss);
					}
				} else {
					boss.gameOver();
				}
			}
			
			//level cleared
			if( score == 5 && level == 1){
				if( betweenLevelDelay < 101 ){
					betweenLevelDelay ++;
				} else {
					for( Enemy each : enemies ){
						each.reset();
					}
					level = 2;
					resetLives();
					betweenLevelDelay = 0;
				}
			} else if ( score == 10 && level == 2 ){
				for( Enemy each : enemies ){
					each.gameOver();
				}
				if( betweenLevelDelay < 101 ){
					betweenLevelDelay ++;
				} else {
					level = 3;
					resetLives();
					boss.reset();
				}
			} else if ( score == 1 && level == 3 ){
				boss.gameOver();
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
		} else if( e.getKeyCode() == 80 /*p*/ ){
			if( level == 1 ){
				for(int i = 0;i < enemies.length;i += 2){
					enemies[i].die();
				}
			} else if ( level == 2 ){
				for( Enemy each : enemies ){
					each.die();
				}
			} else if ( level == 3 ){
				boss.die();
			}
		}
	}
	
	//override
	public void keyReleased(KeyEvent e){
	}
	
	//override
	public void keyTyped(KeyEvent e){
	}
	
	//reset lives
	public void resetLives(){
		lives = 3;
	}
 
}