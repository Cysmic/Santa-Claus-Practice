package sprites;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Present extends Sprite {

	public Present(double x, double y, double width, double height, Color color) {
		super(x, y, width, height, color);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void draw(GraphicsContext context){
		context.setFill(Color.DEEPSKYBLUE);
		context.fillRect(this.getX(), this.getY(), 40, 40);
		
		
	}
}
