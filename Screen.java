import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import java.awt.Font;
import java.awt.geom.Rectangle2D;
import java.awt.Graphics2D;
import java.awt.GradientPaint;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
 
 
public class Screen extends JPanel implements KeyListener, ActionListener{
 
    private Projectile[] p1;
    private Ship s1;
	private Enemy[] enemies;
	private Boss boss;
	private boolean visible;
	private int score, lives, level, betweenLevelDelay, countDown, beforeLevelDelay, projectileCount;
	private Color green, ocean1, ocean2, ocean3, sand1, sand2, grayPillar1, grayPillar2;
	private Font levelCleared;
	private GradientPaint oceanGradient;
	private JButton startEasy, returnToTitleScreen;
	private ImageIcon pokeInvadersLogo, pokemon1, pokemon2, pokemon3, pokemonStart, winPikachu, losePikachu;
	private ImageIcon youWin, gameOver, gameOverMessage;
    
	
    public Screen(){
        //characters/assets
        s1 = new Ship(375,500);
        p1 = new Projectile[3];
        for(int i = 0;i < p1.length;i ++){
        	p1[i] = new Projectile(-20,0);
        }  
		boss = new Boss();
		enemies = new Enemy[10];
		for(int i = 0;i < enemies.length;i ++){
			enemies[i] = new Enemy((i+1)*60);
		}
		
		//logistics
		visible = false;
		lives = 3;
		//level 0 is title, levels 1-3 are gameplay, level 4 is win, level 5 is lose
		level = 0;
		score = 0;
		betweenLevelDelay = 0;
		countDown = 3;
		beforeLevelDelay = 0;
		projectileCount = 0;
		
		//colors
		green = new Color(18,194,16);
		ocean1 = new Color(126,221,252);
		ocean2 = new Color(30,112,148);
		ocean3 = new Color(19,98,133);
		sand1 = new Color(237,206,133);
		sand2 = new Color(222,178,75);
		grayPillar1 = new Color(69,85,99);
		grayPillar2 = new Color(56,70,82);
		
		//fonts
		levelCleared = new Font("TimesRoman", Font.BOLD, 15);
		
		//gradient
		oceanGradient = new GradientPaint(0,0,ocean1,0,600,ocean2);
		
		//buttons
		startEasy = new JButton("START");
		startEasy.setBounds(350,450,100,50);
		add(startEasy);
		startEasy.addActionListener(this);
		
		returnToTitleScreen = new JButton("RETURN TO TITLE SCREEN");
		returnToTitleScreen.setBounds(300,220,200,50);
		add(returnToTitleScreen);
		returnToTitleScreen.addActionListener(this);
		returnToTitleScreen.setVisible(false);
		
		//images
		pokeInvadersLogo = new ImageIcon("ImageAssets/Poke-InvadersLogo.png");
		pokemon1 = new ImageIcon("ImageAssets/Pokemon1.png");
		pokemon2 = new ImageIcon("ImageAssets/Pokemon2.png");
		pokemon3 = new ImageIcon("ImageAssets/Pokemon3.png");
		pokemonStart = new ImageIcon("ImageAssets/PokemonStart.png");
		winPikachu = new ImageIcon("ImageAssets/WinPikachu.png");
		losePikachu = new ImageIcon("ImageAssets/LosePikachu.png");
		youWin = new ImageIcon("ImageAssets/YouWin.png");
		gameOver = new ImageIcon("ImageAssets/GameOver.png");
		gameOverMessage = new ImageIcon("ImageAssets/GameOverMessage.png");
		
		setLayout(null);
		setFocusable(true);
		addKeyListener(this);
    }
 
 
    public Dimension getPreferredSize() {
        //Sets the size of the panel
            return new Dimension(800,600);
    }
     
	 
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
		
		Graphics2D g2 = (Graphics2D) g;
		
		//background features
		if( level == 0 ){
			g.setColor(Color.white);
			g.fillRect(0,0,800,600);
			pokeInvadersLogo.paintIcon(null,g,100,100);
		} else if( level == 1 ){
        	g2.setPaint(oceanGradient);
        	g2.fill(new Rectangle2D.Double(0,0,800,600));	
			g.setColor(sand2);
			g.fillArc(-200,400,700,500,0,180);
			g.setColor(sand1);
			g.fillArc(300,450,700,450,0,180);
		} else if ( level == 2 ){
			g2.setPaint(oceanGradient);
        	g2.fill(new Rectangle2D.Double(0,0,800,600));
			g.setColor(ocean3);
			g.fillPolygon(new int[] {-150,600,150},new int[] {600,600,275},3);
			g.setColor(ocean2);
			g.fillPolygon(new int[] {200,875,650},new int[] {600,600,325},3);
		} else if ( level == 3 ){
			g2.setPaint(oceanGradient);
        	g2.fill(new Rectangle2D.Double(0,0,800,600));
			g.setColor(sand1);
			g.fillArc(0,500,800,200,0,180);
			g.setColor(grayPillar1);
			g.fillRect(25,0,50,600);
			g.fillRect(110,0,50,600);
			g.fillRect(715,0,50,600);
			g.fillRect(630,0,50,600);
			g.setColor(grayPillar2);
			g.fillRect(35,80,50,520);
			g.fillRect(120,80,50,520);
			g.fillRect(725,80,50,520);
			g.fillRect(640,80,50,520);
			g.fillRect(0,0,800,80);
		} else if ( level == 4 ){
			g.setColor(Color.green);
			g.fillRect(0,0,800,600);
			winPikachu.paintIcon(null,g,250,300);
			youWin.paintIcon(null,g,100,50);
			returnToTitleScreen.setVisible(true);
		} else if ( level == 5 ){
			g.setColor(Color.red);
			g.fillRect(0,0,800,600);
			losePikachu.paintIcon(null,g,100,280);
			gameOver.paintIcon(null,g,100,50);
			returnToTitleScreen.setVisible(true);
		}
     
        //Draw ship
        if( level != 0 && level != 4 && level != 5 ){
        	s1.drawMe(g);
        }
		
        //Draw Projectile
		for( Projectile each : p1 ){
			each.drawMe(g);
		}
		
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
		
		//show score, lives, and level
		g.setColor(Color.white);
		if( level == 1 || level == 2 || level == 3 ){
			g.drawString("Score: " + score,700,500);
			//change lives if touched player
			if( level == 1 || level == 2 ){
				if( lives > s1.getLives() ){
					for( Enemy each : enemies ){
						each.reset();
					}
					lives = s1.getLives();
				}
			} else if ( level == 3 ){
				if( lives > s1.getLives() ){
					boss.reset();
					lives = s1.getLives();
				}
			}
			g.drawString("Lives: " + lives,700,515);
			g.drawString("Level: " + level,700,530);
		}
		
		//Game over
		if( lives <= 0 && level != 0 && level != 4 && level != 5 ){
			g.setColor(Color.white);
			g.fillRoundRect(350,275,125,40,20,20);
			g.setColor(Color.red);
			g.drawString("GAME OVER",375,300);
		}
		
		//level cleared and move to level 2
		g.setFont(levelCleared);
		if( score == 5 && level == 1 ){
			g.setColor(Color.white);
			g.fillRoundRect(320,275,175,40,20,20);
			g.setColor(green);
			g.drawString("LEVEL 1 CLEARED",340,300);
		} else if ( score == 10 && level == 2 ){
			g.setColor(Color.white);
			g.fillRoundRect(320,275,175,40,20,20);
			g.setColor(green);
			g.drawString("LEVEL 2 CLEARED",340,300);
		} else if ( score == 1 && level == 3 ){
			g.setColor(Color.white);
			g.fillRoundRect(320,275,175,40,20,20);
			g.setColor(green);
			g.drawString("LEVEL 3 CLEARED",340,300);
		}
		
		//countdown
		if( countDown == 3 && level == 1 ){
			g.setColor(Color.white);
			g.fillRoundRect(375,275,50,40,10,10);
			g.setColor(green);
			g.drawString("3",398,300);
		} else if ( countDown == 2 && level == 1 ){
			g.setColor(Color.white);
			g.fillRoundRect(375,275,50,40,10,10);
			g.setColor(green);
			g.drawString("2",398,300);
		} else if ( countDown == 1 && level == 1 ){
			g.setColor(Color.white);
			g.fillRoundRect(375,275,50,40,10,10);
			g.setColor(green);
			g.drawString("1",398,300);
		} else if ( countDown == 0 && level == 1 ){
			g.setColor(Color.white);
			g.fillRoundRect(370,275,60,40,20,20);
			g.setColor(green);
			g.drawString("Start",385,300);
		}
    } 
 
 
    public void animate(){ 
        while(true){
			
			//projectile
			for( Projectile each : p1 ){
				each.moveUp();
			}
			if( level == 1 || level == 2 ){
				for( Enemy each : enemies ){
					for( Projectile each2 : p1 ){
						each2.checkCollision(each);
					}
				}
			} else  if ( level == 3 ){
				for( Projectile each : p1 ){
						each.checkCollisionBoss(boss);
				};
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
						//countdown to start
						if( beforeLevelDelay == 501 ){
							countDown = 2;
							beforeLevelDelay ++;
						} else if ( beforeLevelDelay == 1001 ) {
							countDown = 1;
							beforeLevelDelay ++;
						} else if ( beforeLevelDelay == 1501 ) {
							countDown = 0;
							beforeLevelDelay ++;
						} else if ( beforeLevelDelay == 2001 ) {
							each.move();
							countDown = -1;
						} else if ( beforeLevelDelay < 2002 ){
							beforeLevelDelay ++;
						}
						//checking if enemies touches the bottom
						if( each.touchBottom() ){
							lives --;
							s1.changeLives(lives);
							for( Enemy each2 : enemies ){
								each2.reset();
							}
						}
						//checking if enemies touches the player
						if( s1.checkCollision(each) ){
							break;
						}
					}
				} else {
					//no lives left
					for( Enemy each : enemies ){
						each.gameOver();
					}
					//game over screen
					if( betweenLevelDelay < 101 ){
						betweenLevelDelay ++;
					} else {
						level = 5;
						betweenLevelDelay = 0;
						beforeLevelDelay = 0;
					}
				}
			} else if ( level == 3 ){
				if( lives > 0 ){
					boss.move();
					//checking if boss touches the bottom
					if( boss.touchBottom() ){
						lives --;
						s1.changeLives(lives);
						boss.reset();
					}
					//checking if boss touches the player
					s1.checkCollisionBoss(boss);
				} else {
					//no lives left
					boss.gameOver();
					//game over screen
					if( betweenLevelDelay < 101 ){
						betweenLevelDelay ++;
					} else {
						level = 5;
						betweenLevelDelay = 0;
						beforeLevelDelay = 0;
					}
				}
			}
			//in case lives goes below 0
			if( lives < 0 ){
				lives = 0;
				s1.changeLives(lives);
			}
			
			//level cleared
			if( score == 5 && level == 1){
				if( betweenLevelDelay < 101 ){
					betweenLevelDelay ++;
				} else {
					level = 2;
					for( Enemy each : enemies ){
						each.reset();
						each.setLevel(level);
					}
					lives = 3;
					s1.changeLives(lives);
					betweenLevelDelay = 0;
				}
			} else if ( score == 10 && level == 2 ){
				if( betweenLevelDelay < 101 ){
					betweenLevelDelay ++;
				} else {
					level = 3;
					for( Enemy each : enemies ){
						each.gameOver();
					}
					lives = 3;
					s1.changeLives(lives);
					boss.reset();
					betweenLevelDelay = 0;
				}
			} else if ( score == 1 && level == 3 ){
				boss.gameOver();
				if( betweenLevelDelay < 101 ){
					betweenLevelDelay ++;
				} else {
					level = 4;
					betweenLevelDelay = 0;
					beforeLevelDelay = 0;
				}
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
			if( !p1[projectileCount].getVisible() ){
				//change projectile position to match ship's position
				p1[projectileCount].setPosition(s1.getX()+25,s1.getY());
				//shoot projectile
				p1[projectileCount].shoot();
				//switch projectile
				if( projectileCount < 2 ){
					projectileCount ++;
				} else if ( projectileCount == 2 ){
					projectileCount = 0;
				}
			}
		} else if( e.getKeyCode() == 37 /*left arrow*/ ){
			//move player up
			s1.moveLeft();
		} else if( e.getKeyCode() == 39 /*right arrow*/ ){
			//move player down
			s1.moveRight();
		} else if( e.getKeyCode() == 80 /*p cheat key*/ ){
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
		} else if( e.getKeyCode() == 79 /*o cheat key*/){
			lives = 0;
		}
	}
	
	//override
	public void keyReleased(KeyEvent e){
	}
	
	//override
	public void keyTyped(KeyEvent e){
	}
	
	public void actionPerformed(ActionEvent e){
		if( e.getSource() == startEasy ){
			level = 1;
			startEasy.setVisible(false);
			lives = 3;
			s1.changeLives(lives);
			for( Enemy each : enemies ){
				each.reset();
				each.setLevel(1);
				countDown = 3;
			}
			boss.reset();
		} else if ( e.getSource() == returnToTitleScreen ){
			level = 0;
			returnToTitleScreen.setVisible(false);
			startEasy.setVisible(true);
		}
	}
 
}