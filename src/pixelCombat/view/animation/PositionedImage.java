package pixelCombat.view.animation;

import javafx.scene.image.Image;
import pixelCombat.Math.Vector2d;

public class PositionedImage extends Image {

	private Vector2d offSetPos;
	private String url;

	public PositionedImage(String url, float x, float y) {
		super(url);
		this.url = url;
		this.offSetPos = new Vector2d(x, y);
	}

	public Vector2d getOffSetPos() {
		return offSetPos;
	}

	public void setOffSetPos(Vector2d offSetPos) {
		this.offSetPos = offSetPos;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

}
