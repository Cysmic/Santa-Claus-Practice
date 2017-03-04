package sprites;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Sled extends PhysicsSprite{

	
	public Sled(double x, double y, double width, double height, double vx, double vy, boolean hasGravity,
			Color color) {
		super(x, y, width, height, vx, vy, hasGravity, color);
	}
	
	@Override
	public void draw(GraphicsContext context){
		context.setFill(Color.RED);
		context.fillRect(this.getX(), this.getY(), 75, 50);
	}
	public boolean checkCollisionLeft(Sprite sprite){
		if (sprite.getX() <= this.getX()) {
			if((sprite.getX() + sprite.getWidth()) - this.getX() > 0){
				if ((sprite.getY() + sprite.getHeight())- (this.getY()) > 0){
					return true;
				}
			}
		}
		return false;
	}
	
	public boolean checkCollisionTop(Sprite sprite){
		if ((sprite.getY() + sprite.getHeight())- (this.getY()) > 0){
			if((sprite.getX() + sprite.getWidth()) - this.getX() > 0){
				return true;
			}
		}
		return false;
	}
	
	public boolean checkCollisionRight(Sprite sprite){
		if (sprite.getX() > this.getX()) {
			if(sprite.getX()-(sprite.getWidth()/2) <= (this.getX() + this.getWidth())){
				if ((sprite.getY() + sprite.getHeight()) - this.getY() > 0){
					return true;
				}
			}
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