package pixelCombat.projectiles;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

import pixelCombat.Math.BoundingRectangle;
import pixelCombat.Math.GeometryUtils;
import pixelCombat.Math.Vector2d;
import pixelCombat.model.Character;
import pixelCombat.utils.Console;
import pixelCombat.xml.StatsParser;
import pixelCombat.xml.XML_Box_Reader;

public class ProjectileBoxLogic {
	private Projectile projectile;
	public int currentAnimation;

	// Stütze für Bilder
	public final int CREATION = 0;
	public final int MOVE = 1;
	public final int EXPLOSION = 2;
	public final int DEAD = 3;
	public final int SPECIALEFFECT1 = 4;
	public final int SPECIALEFFECT2 = 5;
	
	public BoundingRectangle currentColBox;
	private Console console;

	public ProjectileBoxLogic(Projectile projectile, Console console) {
		this.console	= console;
		this.projectile = projectile;
		init();
	}

	public void init() {
		currentAnimation = CREATION;
		projectile.currentBox = projectile.boxes.get(CREATION);
	}

	public void update() {
		if (projectile.picManager.getCurrentAnimation() == currentAnimation)
			return;

		switch (projectile.picManager.getCurrentAnimation()) {
		case CREATION:
			currentAnimation = CREATION;
			projectile.currentBox = projectile.boxes.get(CREATION);
			break;
		case MOVE:
			currentAnimation = MOVE;
			projectile.currentBox = projectile.boxes.get(MOVE);
			break;
		case EXPLOSION:
			currentAnimation = EXPLOSION;
			projectile.currentBox = projectile.boxes.get(EXPLOSION);
			break;
		case SPECIALEFFECT1:
			currentAnimation = SPECIALEFFECT1;
			projectile.currentBox = projectile.boxes.get(SPECIALEFFECT1);
			break;
		case SPECIALEFFECT2:
			currentAnimation = SPECIALEFFECT2;
			projectile.currentBox = projectile.boxes.get(SPECIALEFFECT2);
			break;
		default:
			break;
		}

	}

	public Map<String, ArrayList<ArrayList<BoundingRectangle>>> loadBox(String name) {

		Map<String, ArrayList<ArrayList<BoundingRectangle>>> boxes = new HashMap<String, ArrayList<ArrayList<BoundingRectangle>>>();

		try {
			InputStream stream = StatsParser.class.getResourceAsStream("/chars_boxes/" + name + ".xml");
			InputSource source = new InputSource(stream);

			XMLReader xmlreader = XMLReaderFactory.createXMLReader();
			XML_Box_Reader ch = new XML_Box_Reader(console);
			xmlreader.setContentHandler(ch);
			xmlreader.parse(source);

			boxes = ch.getBoxes();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return boxes;
	}

	/**
	 * Checks if two rectangles overlap
	 * 
	 * @param c1
	 *            rectangle c1
	 * @param c2
	 *            rectangle c2
	 * @return do rectangles collide
	 */
	public boolean isCollision(Character defender) {

		List<ArrayList<BoundingRectangle>> enemyBoxes = defender.currentBox;
		List<ArrayList<BoundingRectangle>> ownBoxes = projectile.currentBox;
		int currentOwnAnimation = projectile.picManager.getCurrFrameIndex();
		int currentDefenderAnimation = defender.picManager.getCurrFrameIndex();

		for (int i = 0; i < ownBoxes.get(currentOwnAnimation).size(); i++) {
			float x1 = projectile.getPos().x + projectile.getDir() * ownBoxes.get(currentOwnAnimation).get(i).getPos().x;
			float y1 = projectile.getPos().y + ownBoxes.get(currentOwnAnimation).get(i).getPos().y;
			float width1 = ownBoxes.get(currentOwnAnimation).get(i).getWidth();
			float height1 = ownBoxes.get(currentOwnAnimation).get(i).getHeight();
			boolean hurts1 = ownBoxes.get(currentOwnAnimation).get(i).getHurts();

			BoundingRectangle ownBox = new BoundingRectangle(height1, new Vector2d(x1, y1), width1);
			ownBox.setHurts(hurts1);

			for (int j = 0; j < enemyBoxes.get(currentDefenderAnimation).size(); j++) {
				float x2 = defender.getPos().x + defender.getDir() * enemyBoxes.get(currentDefenderAnimation).get(j).getPos().x;
				float y2 = defender.getPos().y + enemyBoxes.get(currentDefenderAnimation).get(j).getPos().y;
				float width2 = enemyBoxes.get(currentDefenderAnimation).get(j).getWidth();
				float height2 = enemyBoxes.get(currentDefenderAnimation).get(j).getHeight();
				boolean hurts2 = enemyBoxes.get(currentDefenderAnimation).get(j).getHurts();

				BoundingRectangle enemyBox = new BoundingRectangle(height2, new Vector2d(x2, y2), width2);
				enemyBox.setHurts(hurts2);

				if (GeometryUtils.isCollision(ownBox, enemyBox) && ownBox.getHurts() && !enemyBox.getHurts())
					return true;
			}

		}

		return false;
	}
	


	
	public BoundingRectangle intersection(Character defender) {

		List<ArrayList<BoundingRectangle>> enemyBoxes = defender.currentBox;
		List<ArrayList<BoundingRectangle>> ownBoxes = projectile.currentBox;
		int currentOwnAnimation = projectile.picManager.getCurrFrameIndex();
		int currentDefenderAnimation = defender.picManager.getCurrFrameIndex();

		for (int i = 0; i < ownBoxes.get(currentOwnAnimation).size(); i++) {
			float x1 = projectile.getPos().x + projectile.getDir() * ownBoxes.get(currentOwnAnimation).get(i).getPos().x;
			float y1 = projectile.getPos().y + ownBoxes.get(currentOwnAnimation).get(i).getPos().y;
			float width1 = ownBoxes.get(currentOwnAnimation).get(i).getWidth();
			float height1 = ownBoxes.get(currentOwnAnimation).get(i).getHeight();
			boolean hurts1 = ownBoxes.get(currentOwnAnimation).get(i).getHurts();

			if (!hurts1) {
				BoundingRectangle ownBox = new BoundingRectangle(height1, new Vector2d(x1, y1), width1);
				ownBox.setHurts(hurts1);

				for (int j = 0; j < enemyBoxes.get(currentDefenderAnimation).size(); j++) {
					float x2 = defender.getPos().x + defender.getDir() * enemyBoxes.get(currentDefenderAnimation).get(j).getPos().x;
					float y2 = defender.getPos().y + enemyBoxes.get(currentDefenderAnimation).get(j).getPos().y;
					float width2 = enemyBoxes.get(currentDefenderAnimation).get(j).getWidth();
					float height2 = enemyBoxes.get(currentDefenderAnimation).get(j).getHeight();
					boolean hurts2 = enemyBoxes.get(currentDefenderAnimation).get(j).getHurts();

					if (!hurts2) {
						BoundingRectangle enemyBox = new BoundingRectangle(height2, new Vector2d(x2, y2), width2);
						enemyBox.setHurts(hurts2);
						if (GeometryUtils.isCollision(ownBox, enemyBox)) {

							x1 = ownBox.getUpperLeft().x;
							y1 = ownBox.getUpperLeft().y;
							x2 = enemyBox.getUpperLeft().x;
							y2 = enemyBox.getUpperLeft().y;

							float x3 = (x1 + ownBox.getWidth());
							float x4 = (x2 + enemyBox.getWidth());
							float y3 = (y1 + ownBox.getHeight());
							float y4 = (y2 + enemyBox.getHeight());

							float xmin = Math.max(x1, x2);
							float xmax1 = x3;
							float xmax2 = x4;
							float xmax = Math.min(xmax1, xmax2);
							if (xmax > xmin) {
								float ymin = Math.max(y1, y2);
								float ymax1 = y3;
								float ymax2 = y4;
								float ymax = Math.min(ymax1, ymax2);
								if (ymax > ymin) {
									float out_x = xmin + (xmax - xmin) / 2f;
									float out_y = ymin + (ymax - ymin) / 2f;
									float out_width = xmax - xmin;
									float out_height = ymax - ymin;
									this.currentColBox = ownBox;
									return new BoundingRectangle(out_height, new Vector2d(out_x, out_y), out_width);

								}
							}

						}
					}
				}

			}

		}

		return null;
	}

	public BoundingRectangle intersection(BoundingRectangle box1) {

		ArrayList<ArrayList<BoundingRectangle>> ownBoxes = projectile.currentBox;
		int currentOwnAnimation = projectile.picManager.getCurrFrameIndex();

		for (int i = 0; i < ownBoxes.get(currentOwnAnimation).size(); i++) {
			float x1 = projectile.getPos().x + projectile.getDir() * ownBoxes.get(currentOwnAnimation).get(i).getPos().x;
			float y1 = projectile.getPos().y + ownBoxes.get(currentOwnAnimation).get(i).getPos().y;
			float width1 = ownBoxes.get(currentOwnAnimation).get(i).getWidth();
			float height1 = ownBoxes.get(currentOwnAnimation).get(i).getHeight();
			BoundingRectangle ownBox = new BoundingRectangle(height1, new Vector2d(x1, y1), width1);
			if (GeometryUtils.isCollision(ownBox, box1)) {

				x1 = ownBox.getUpperLeft().x;
				y1 = ownBox.getUpperLeft().y;
				float x2 = box1.getUpperLeft().x;
				float y2 = box1.getUpperLeft().y;

				float x3 = (x1 + ownBox.getWidth());
				float x4 = (x2 + box1.getWidth());
				float y3 = (y1 + ownBox.getHeight());
				float y4 = (y2 + box1.getHeight());

				float xmin = Math.max(x1, x2);
				float xmax1 = x3;
				float xmax2 = x4;
				float xmax = Math.min(xmax1, xmax2);
				if (xmax > xmin) {
					float ymin = Math.max(y1, y2);
					float ymax1 = y3;
					float ymax2 = y4;
					float ymax = Math.min(ymax1, ymax2);
					if (ymax > ymin) {
						float out_x = xmin + (xmax - xmin) / 2f;
						float out_y = ymin + (ymax - ymin) / 2f;
						float out_width = xmax - xmin;
						float out_height = ymax - ymin;
						this.currentColBox = ownBox;
						return new BoundingRectangle(out_height, new Vector2d(out_x, out_y), out_width);

					}
				}

			}
		}

		return null;
	}
	
	
}
