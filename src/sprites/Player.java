package sprites;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import main.Main;
import main.Util;

public class Player extends PhysicsSprite {

	Image image;
	double theta;
	private int reload;
	public static final int RELOAD_TIME = 20;
	private long lastJumped = 0;
	public boolean canMoveRight = true;
	public boolean canMoveLeft = true;

	public Player(double x, double y, double width, double height, double vx, double vy, boolean hasGravity,
			Color color) {
		super(x, y, width, height, vx, vy, hasGravity, color);
	}

	public void parseInput() {
		if(reload > 0) {
			reload--;
		}
		

		this.setVx(0);
		
		Main main = Main.getInstance();
		if(main.isPressed("A"))
			this.setVx(-7.5);
		else if(main.isPressed("D")){
			this.setVx(7.5);
		}		
		
		if(main.isPressed("W") && System.currentTimeMillis() - lastJumped >= 500 && this.getVy() == 0){
			this.setVy(this.getVy() - 5);
			lastJumped = System.currentTimeMillis();
		}
		
		if(main.isPressed("T") && reload == 0) {
			main.game.sprites.add(new Coal(getX(), getY(), this.getVx() < 0 ? -40 : 40, 10));
			reload = RELOAD_TIME;
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
	
}
