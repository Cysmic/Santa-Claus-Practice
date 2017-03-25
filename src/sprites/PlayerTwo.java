package sprites;

import java.util.List;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import main.Game;
import main.Main;
import main.Util;

public class PlayerTwo extends PhysicsSprite {

	Image image;
	double theta;
	private int reload;
	public static final int RELOAD_TIME = 20;
	private long lastJumped = 0;
	public boolean canMoveRight = true;
	public boolean canMoveLeft = true;
	private boolean canCombine = false;
	int i = -1;
	public PlayerTwo(double x, double y, double width, double height, double vx, double vy, boolean hasGravity,
			Color color) {
		super(x, y, width, height, vx, vy, hasGravity, color);
	}

	public void parseInput(Game game) {
		if(reload > 0) {
			reload--;
		}
		
		
		
		//boolean moveLeft = true, moveRight = true;
		boolean moveDown = true;
		
		this.setVx(0);
		
		List<Sprite> sprites = game.sprites;
		Sprite sprite = null;
		Main main = Main.getInstance();
		
		i = checkCollisionX(sprites, false);
		if (i != -1){
			this.setVy(-1);
		}
			
		if(main.isPressed("LEFT")) {
			i = checkCollisionX(sprites, false);
			if (i == -1) {
				this.setVx(-7.5);
			} else {
				this.setVx(0);
				if (sprites.get(i) instanceof Sled) {
					canCombine = true;
				}
			}
		} else if(main.isPressed("RIGHT")){
			i = checkCollisionX(sprites, true);
			if (i == -1) {
				this.setVx(7.5);
			} else {
				this.setVx(0);
				if (sprites.get(i) instanceof Sled) {
					canCombine = true;
				}
			}
		}
		
		if(main.isPressed("SHIFT")) {
			if (canCombine) {
				this.setColor(Color.MIDNIGHTBLUE);
				this.setWidth(75);
				sprites.remove(i);
				
			}
		}
		
		if(main.isPressed("UP") && System.currentTimeMillis() - lastJumped >= 500 && this.getVy() == 0){
			this.setVy(this.getVy() - 5);
			lastJumped = System.currentTimeMillis();
		}
		
		if(main.isPressed("SLASH") && reload == 0) {
			main.game.sprites.add(new Coal(getX(), getY(), this.getVx() < 0 ? -40 : 40, 10));
			reload = RELOAD_TIME;
		}
		
		if (moveDown == false) {
			//this.setX(340);
			//this.setY(514);
			this.setVy(0);
			this.setGravity(false);
		}
	}

	@Override
	public void tick(int timePassed){
		setX(getX() + this.getVx() * timePassed);
		if(this.getGravity()){
			double targetY = getY() + (this.getVy() + GRAVITY * timePassed / 2) * timePassed;
			double bottom = Main.getInstance().HEIGHT*9/10.0 - this.getHeight();
			if(targetY <= bottom){
				setY(targetY);
				setVy(getVy() + GRAVITY * timePassed);
			}
			else if(this.getVy() > 0){
				setY(bottom);
				setVy(0);
			}
		} else {
			
			return;
		}
	}
	@Override
	public void draw(GraphicsContext context) {

		context.setFill(this.getColor());
		context.fillRect(this.getX(), this.getY(), this.getWidth(), this.getHeight());
		
		// Normally you'd be drawing the player image but until we get images rectangles will do
		/*double[] coords = Util.rotate(this.getX() + this.getWidth() / 2, this.getY() + this.getHeight() / 2, theta);
		context.save();
		context.rotate(theta);
		context.drawImage(image, coords[0] - this.getWidth() / 2, coords[1] - this.getHeight() / 2);
		context.restore();*/
		
	}

	public boolean checkCollisionLeft(Sprite sprite){
		if (sprite.getX() <= this.getX()) {
			if((sprite.getX() + sprite.getWidth()) - this.getX() > 0){
				return true;
			}
		}
		return false;
	}
	
	public boolean checkCollisionTop(Sprite sprite){
		if ((sprite.getY() + sprite.getHeight())- (this.getY()) > 0){
			return true;
		}
		return false;
	}
	
	public boolean checkCollisionRight(Sprite sprite){
		if (sprite.getX() > this.getX()) {
			if(sprite.getX()-(sprite.getWidth()/2) <= (this.getX() + this.getWidth())){
				return true; 
			}
		}
		return false;
	}
	
	public boolean checkCollisionBottom(Sprite sprite){
		if ((this.getY()) - (sprite.getY() + sprite.getHeight()) > 0){
			return true;
		}
		return false;
	}
	
	public boolean checkCollisionFour(Sprite sprite){
		if (checkCollisionLeft(sprite) == true || checkCollisionRight(sprite) == true || checkCollisionTop(sprite) == true){
			return true;
		}
		return false;
	}
	
	public int checkCollisionX(List<Sprite> sprites, boolean moveRight) {
		System.out.println("x =" + this.getX());
		for(int i = sprites.size()-1; i >= 0; i--) {
			
			Sprite sprite = sprites.get(i);
			if (!(sprite instanceof PlayerTwo)) {
				System.out.println("x_s =" + sprite.getX());
					if (this.getCollision().getBoundsInParent().intersects(sprite.getCollision().getBoundsInParent())){
						System.out.println("Collide1");
							if (moveRight) {
								//if (this.getCollision().getTranslateX() + 50 >= sprite.getCollision().getTranslateX()) {
									System.out.println("Collide");
									return i;
								//}
							} else {
								//if (this.getCollision().getTranslateX() == sprite.getCollision().getTranslateX() + 50) {
									System.out.println("Collide");
									return i;
								//}
							}
					}
			}
			
		}
		return -1;
	}
	public int checkCollisionY(List<Sprite> sprites, boolean moveUp) {
		System.out.println("x =" + this.getY());
		for(int i = sprites.size()-1; i >= 0; i--) {
			
			Sprite sprite = sprites.get(i);
			if (!(sprite instanceof PlayerTwo)) {
					if (this.getCollision().getBoundsInParent().intersects(sprite.getCollision().getBoundsInParent())){
						System.out.println("Collide1");
							if (moveUp) {
								//if (this.getCollision().getTranslateX() + 50 >= sprite.getCollision().getTranslateX()) {
									System.out.println("Collide");
									return i;
								//}
							} else {
								//if (this.getCollision().getTranslateX() == sprite.getCollision().getTranslateX() + 50) {
									System.out.println("Collide");
									return i;
								//}
							}
					}
			}
			
		}
		return -1;
	}
	
}
