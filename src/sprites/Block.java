package sprites;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Block extends Sprite{
	
	public Block(double x, double y, double width, double height, Color color) {
		super(x, y, width, height, color);
		// TODO Auto-generated constructor stub
	}
	
	public Block(double x, double y){
		super(x, y, 50, 50, Color.BEIGE);
		
	}
	
	@Override
	public void draw(GraphicsContext context){
		context.setFill(Color.BROWN);
		context.fillRect(this.getX(), this.getY(), this.getWidth(), this.getHeight());
	}
}
