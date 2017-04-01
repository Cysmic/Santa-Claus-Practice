package main;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import sprites.Coal;
import sprites.PhysicsSprite;
import sprites.Player;
import sprites.PlayerTwo;
import sprites.Sprite;
import sprites.Present;
import sprites.Sled;
import sprites.Bomb;
import sprites.Block;

public class Game {
	
	
	public static Game instance;
	private Main main;
	public List<Sprite> sprites = new ArrayList<Sprite>();
	
	public Player player;
	public PlayerTwo playerT;
	
	public Game() {
		instance = this;
		main = Main.getInstance();
	}
	
	public static Game getInstance() {
		return instance;
	}
	
	public void drawTen(){
		int p = 0;
		int l = 0;
		for (int i = 0; i<15; i++){
			p = (int) (Math.random()*1200);
			l = (int) (Math.random()*600);
			Block blockTwo = new Block(p, l);
			sprites.add(blockTwo);
		}
	}
	
	public void initialize() {
		player = new Player(100, 100, 50, 50, 0, 0, true, Color.WHITESMOKE);
		playerT = new PlayerTwo(170, 170, 50, 50, 0, 0, true, Color.BLUEVIOLET);
		sprites.add(player);
		sprites.add(playerT);
		// False for now since we don't have a floor
		//sprites.add(new Present(150, 644, 20, 20, Color.DEEPSKYBLUE));
		//sprites.add(new Bomb(290, 644, 20, 20, Color.DEEPSKYBLUE));
		sprites.add(new Sled(340, 614, 75, 50, 0, 0, true, Color.RED));
		drawTen();
	}
	
	public void renderFrame(GraphicsContext context, int frame) {
		
		if(main.isPressed("BACK_SPACE"))
			System.exit(0);
		
		context.clearRect(0, 0, main.WIDTH, main.HEIGHT);
		context.setFill(Color.BLACK);
		context.fillRect(0, 0, main.WIDTH, main.HEIGHT);
		
		System.out.println("Size " + sprites.size());
		for(int i = sprites.size()-1; i >= 0; i--) {
			Sprite sprite = sprites.get(i);
			if(sprite instanceof Player)
				((Player)sprite).parseInput(this);
			if(sprite instanceof PlayerTwo)
				((PlayerTwo)sprite).parseInput(this);
			if(sprite instanceof Sled)
				((Sled)sprite).parseInput(this);
			/*
			if(sprite instanceof Bomb)
			{
				if(((Bomb)sprite).checkCollisionFour(player) == true){
					sprites.remove(i);
				}
			}
			if(sprite instanceof Sled){
				//if(((Sled)sprite).checkCollisionLeft(player) == true){
				//	player.canMoveRight = false;
				//}
				//if(((Sled)sprite).checkCollisionRight(player) == true){
				//	player.canMoveLeft = false;
				//}
				//if(((Sled)sprite).checkCollisionTop(player) == true){
				//	player.setGravity(false);
				//} else {
				//	player.setGravity(true);
				//}
				//if(((Sled)sprite).checkCollisionFour(player) == true){
					//player.setVy(0);
					//player.setGravity(false);
				//}
			}
			*/
			sprite.tick();
			sprite.draw(context);
		}
	}
	
	public void handleKey() {
		
	}
}
