package pixelCombat.model;

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
import pixelCombat.utils.Console;
import pixelCombat.xml.StatsParser;
import pixelCombat.xml.XML_Box_Reader;

public abstract class BoxLogic {
	protected Character character;

	public int currentAnimation;

	// Stütze für Bilder
	public final int STAND = 0;
	public final int MOVE = 1;
	public final int JUMPING = 2;
	public final int BASICATTACK = 3;
	public final int SPECIALATTACK1 = 4;
	public final int SPECIALATTACK2 = 5;
	public final int SPECIALATTACK3 = 6;
	public final int ISHIT = 7;
	public final int KNOCKBACK = 8;
	public final int KNOCKEDOUT = 9;
	public final int AVATAR = 10;
	public final int BASICATTACK1 = 11;
	public final int JUMPATTACK = 12;
	public final int RETREATING = 13;
	public final int DASHING = 14;
	public final int DEFENDING = 15;
	public final int SPECIALATTACK4 = 16;
	public final int SPECIALATTACK5 = 17;
	public final int BASICATTACK21 = 18;
	public final int BASICATTACK22 = 19;
	public final int BASICATTACK23 = 20;
	public final int SPECIALATTACK6 = 21;
	public final int SPECIALATTACK7 = 22;
	public final int INTRO = 23;
    public final int WIN = 24;
    public final int DEAD = 25;
    public final int RUNATTACK1 = 26;
    public final int RUNATTACK2 = 27;
	public final int JUMPRECOVER = 28;
	public final int AIRDEFENDING = 29;

	public final int AIR_SPECIALATTACK1 = 30;
	
	public final int KNOCKBACKRECOVER = 31;
	public final int JUMPFALL = 32;
	
	
	public final int MAX_STANDARD_SPRITES = JUMPFALL;
	
    
	public BoundingRectangle currentColBox;
	private Console console;

	public BoxLogic(Character character, Console console) {
		this.character 	= 	character;
		this.console	=	console;	
	//	init();
	}

	public void init() {
		//character.boxes = loadBox(character.getClass().getSimpleName());
		currentAnimation = STAND;
		character.currentBox = character.boxes.get("stand");
	}

	public void update() {
		if (character.picManager.getCurrentAnimation() == currentAnimation)
			return;

		switch (character.picManager.getCurrentAnimation()) {
		case STAND:
			updateBoxSeq(STAND,"stand");
			break;
		case MOVE:
			updateBoxSeq(MOVE,"move");
			break;
		case JUMPING:
			updateBoxSeq(JUMPING,"jumping");
			break;
		case JUMPFALL:
			updateBoxSeq(JUMPFALL,"jumpFall");
			break;	
		case JUMPRECOVER:
			updateBoxSeq(JUMPRECOVER,"jumpRecover");
			break;
		case BASICATTACK:
			updateBoxSeq(BASICATTACK,"basicAttack");
			break;
		case SPECIALATTACK1:
			updateBoxSeq(SPECIALATTACK1,"specialAttack1");
			break;
		case SPECIALATTACK2:
			updateBoxSeq(SPECIALATTACK2,"specialAttack2");
			break;
		case SPECIALATTACK3:
			updateBoxSeq(SPECIALATTACK3,"specialAttack3");
			break;
		case SPECIALATTACK4:
			updateBoxSeq(SPECIALATTACK4,"specialAttack4");
			break;
		case SPECIALATTACK5:
			updateBoxSeq(SPECIALATTACK5,"specialAttack5");
			break;
		case SPECIALATTACK6:
			updateBoxSeq(SPECIALATTACK6,"specialAttack6");
			break;
		case SPECIALATTACK7:
			updateBoxSeq(SPECIALATTACK7,"specialAttack7");
			break;
		case AIR_SPECIALATTACK1:
			updateBoxSeq(AIR_SPECIALATTACK1,"airSpecialAttack1");
			break;
		case ISHIT:
			updateBoxSeq(ISHIT,"isHit");
			break;
		case KNOCKBACK:
			updateBoxSeq(KNOCKBACK,"knockBack");
			break;
		case KNOCKBACKRECOVER:
			updateBoxSeq(KNOCKBACKRECOVER,"knockBackRecover");
			break;	
		case KNOCKEDOUT:
			updateBoxSeq(KNOCKEDOUT,"knockedOut");
			break;
		case AVATAR:
			updateBoxSeq(AVATAR,"avatar");
			break;
		case BASICATTACK1:
			updateBoxSeq(BASICATTACK1,"basicAttack1");
			break;
		case BASICATTACK21:
			updateBoxSeq(BASICATTACK21,"basicAttack21");
			break;
		case BASICATTACK22:
			updateBoxSeq(BASICATTACK22,"basicAttack22");
			break;
		case BASICATTACK23:
			updateBoxSeq(BASICATTACK23,"basicAttack23");
			break;
		case JUMPATTACK:
			updateBoxSeq(JUMPATTACK,"jumpAttack");
			break;
		case DASHING:
			updateBoxSeq(DASHING,"dashing");
			break;
		case DEFENDING:
			updateBoxSeq(DEFENDING,"defend");
			break;
		case AIRDEFENDING:
			updateBoxSeq(AIRDEFENDING,"airDefend");
			break;
		case INTRO:
			updateBoxSeq(INTRO,"intro");
			break;
		case WIN:
			updateBoxSeq(WIN,"win");
			break;
		case DEAD:
			updateBoxSeq(DEAD,"dead");
			break;
		case RUNATTACK1:
			updateBoxSeq(RUNATTACK1,"runAttack1");
			break;
		case RUNATTACK2:
			updateBoxSeq(RUNATTACK2,"runAttack2");
			break;
		default:
			loadFurtherBoxes(character.picManager.getCurrentAnimation());
			break;
		}

	}

	public abstract void loadFurtherBoxes(int currentAnimation2);

	public void updateBoxSeq(int seq, String seq_str) {
		currentAnimation = seq;
		character.currentBox = character.boxes.get(seq_str);
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
		List<ArrayList<BoundingRectangle>> ownBoxes = character.currentBox;
		int currentOwnAnimation = character.picManager.getCurrFrameIndex();
		int currentDefenderAnimation = defender.picManager.getCurrFrameIndex();

		for (int i = 0; i < ownBoxes.get(currentOwnAnimation).size(); i++) {
			float x1 = character.getPos().x + character.getDir() * ownBoxes.get(currentOwnAnimation).get(i).getPos().x;
			float y1 = character.getPos().y + ownBoxes.get(currentOwnAnimation).get(i).getPos().y;
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
		List<ArrayList<BoundingRectangle>> ownBoxes = character.currentBox;
		int currentOwnAnimation = character.picManager.getCurrFrameIndex();
		int currentDefenderAnimation = defender.picManager.getCurrFrameIndex();

		for (int i = 0; i < ownBoxes.get(currentOwnAnimation).size(); i++) {
			float x1 = character.getPos().x + character.getDir() * ownBoxes.get(currentOwnAnimation).get(i).getPos().x;
			float y1 = character.getPos().y + ownBoxes.get(currentOwnAnimation).get(i).getPos().y;
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

		List<ArrayList<BoundingRectangle>> ownBoxes = character.currentBox;
		int currentOwnAnimation = character.picManager.getCurrFrameIndex();

		for (int i = 0; i < ownBoxes.get(currentOwnAnimation).size(); i++) {
			float x1 = character.getPos().x + character.getDir() * ownBoxes.get(currentOwnAnimation).get(i).getPos().x;
			float y1 = character.getPos().y + ownBoxes.get(currentOwnAnimation).get(i).getPos().y;
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
