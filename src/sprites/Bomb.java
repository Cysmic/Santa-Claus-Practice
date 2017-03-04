package sprites;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import sprites.Hitbox;

public class Bomb extends Sprite {

	public Bomb(double x, double y, double width, double height, Color color) {
		super(x, y, width, height, color);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void draw(GraphicsContext context){
		context.setFill(Color.DEEPSKYBLUE);
		context.fillRect(this.getX(), this.getY(), 40, 40);
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
		if (checkCollisionLeft(sprite) == true || checkCollisionRight(sprite) == true){
			return true;
		}
		return false;
	}
}
