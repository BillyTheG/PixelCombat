package pixelCombat.artworks;

import java.util.ArrayList;

import javafx.scene.image.Image;
import pixelCombat.Math.Vector2d;
import pixelCombat.model.PXMapHandler;
import pixelCombat.view.Animation;

public class ZorroWaterDrop extends ArtWork {
	
	
	public ZorroWaterDrop() {
		super(new Vector2d(PXMapHandler.X_FIELDS/2f,PXMapHandler.Y_FIELDS/2), 0f, 0f);
		ArrayList<Image> images = new ArrayList<Image>();
		
		images.add(loadImage("/dusts/water/IMG_Dust_WaterDrop_001.png"));
		images.add(loadImage("/dusts/water/IMG_Dust_WaterDrop_002.png"));
		images.add(loadImage("/dusts/water/IMG_Dust_WaterDrop_003.png"));
		images.add(loadImage("/dusts/water/IMG_Dust_WaterDrop_004.png"));
		images.add(loadImage("/dusts/water/IMG_Dust_WaterDrop_005.png"));
	
		images.add(loadImage("/dusts/water/IMG_Dust_WaterDrop_006.png"));
		images.add(loadImage("/dusts/water/IMG_Dust_WaterDrop_007.png"));
		images.add(loadImage("/dusts/water/IMG_Dust_WaterDrop_008.png"));
		images.add(loadImage("/dusts/water/IMG_Dust_WaterDrop_009.png"));
		images.add(loadImage("/dusts/water/IMG_Dust_WaterDrop_010.png"));
		
		images.add(loadImage("/dusts/water/IMG_Dust_WaterDrop_011.png"));
		images.add(loadImage("/dusts/water/IMG_Dust_WaterDrop_012.png"));
		images.add(loadImage("/dusts/water/IMG_Dust_WaterDrop_013.png"));
		images.add(loadImage("/dusts/water/IMG_Dust_WaterDrop_014.png"));
		images.add(loadImage("/dusts/water/IMG_Dust_WaterDrop_015.png"));
		
		images.add(loadImage("/dusts/water/IMG_Dust_WaterDrop_016.png"));
		images.add(loadImage("/dusts/water/IMG_Dust_WaterDrop_017.png"));
		images.add(loadImage("/dusts/water/IMG_Dust_WaterDrop_018.png"));
		images.add(loadImage("/dusts/water/IMG_Dust_WaterDrop_019.png"));
		images.add(loadImage("/dusts/water/IMG_Dust_WaterDrop_020.png"));
		
		images.add(loadImage("/dusts/water/IMG_Dust_WaterDrop_021.png"));
		images.add(loadImage("/dusts/water/IMG_Dust_WaterDrop_022.png"));
		images.add(loadImage("/dusts/water/IMG_Dust_WaterDrop_023.png"));
		images.add(loadImage("/dusts/water/IMG_Dust_WaterDrop_024.png"));
		images.add(loadImage("/dusts/water/IMG_Dust_WaterDrop_025.png"));
		
		images.add(loadImage("/dusts/water/IMG_Dust_WaterDrop_026.png"));
		images.add(loadImage("/dusts/water/IMG_Dust_WaterDrop_027.png"));
		images.add(loadImage("/dusts/water/IMG_Dust_WaterDrop_028.png"));
		images.add(loadImage("/dusts/water/IMG_Dust_WaterDrop_029.png"));
		images.add(loadImage("/dusts/water/IMG_Dust_WaterDrop_030.png"));
		
		images.add(loadImage("/dusts/water/IMG_Dust_WaterDrop_031.png"));
		images.add(loadImage("/dusts/water/IMG_Dust_WaterDrop_032.png"));
		images.add(loadImage("/dusts/water/IMG_Dust_WaterDrop_033.png"));
		images.add(loadImage("/dusts/water/IMG_Dust_WaterDrop_034.png"));
		images.add(loadImage("/dusts/water/IMG_Dust_WaterDrop_035.png"));
		
		images.add(loadImage("/dusts/water/IMG_Dust_WaterDrop_036.png"));
		images.add(loadImage("/dusts/water/IMG_Dust_WaterDrop_037.png"));
		images.add(loadImage("/dusts/water/IMG_Dust_WaterDrop_038.png"));
		images.add(loadImage("/dusts/water/IMG_Dust_WaterDrop_039.png"));
		images.add(loadImage("/dusts/water/IMG_Dust_WaterDrop_040.png"));
		
		images.add(loadImage("/dusts/water/IMG_Dust_WaterDrop_041.png"));
		images.add(loadImage("/dusts/water/IMG_Dust_WaterDrop_042.png"));
		images.add(loadImage("/dusts/water/IMG_Dust_WaterDrop_043.png"));
		images.add(loadImage("/dusts/water/IMG_Dust_WaterDrop_044.png"));
	
		
		ArrayList<Float> times = new ArrayList<Float>();
		times.add(0.03f);
		times.add(0.03f);
		times.add(0.03f);
		times.add(0.03f);
		times.add(0.03f);

		times.add(0.03f);
		times.add(0.03f);
		times.add(0.03f);
		times.add(0.03f);
		times.add(0.03f);

		times.add(0.03f);
		times.add(0.03f);
		times.add(0.03f);
		times.add(0.03f);
		times.add(0.03f);

		times.add(0.03f);
		times.add(0.03f);
		times.add(0.03f);
		times.add(0.03f);
		times.add(0.03f);

		times.add(0.03f);
		times.add(0.03f);
		times.add(0.03f);
		times.add(0.03f);
		times.add(0.03f);

		times.add(0.03f);
		times.add(0.03f);
		times.add(0.03f);
		times.add(0.03f);
		times.add(0.03f);

		times.add(0.03f);
		times.add(0.03f);
		times.add(0.03f);
		times.add(0.03f);
		times.add(0.03f);

		times.add(0.03f);
		times.add(0.03f);
		times.add(0.03f);
		times.add(0.03f);
		times.add(0.03f);

		times.add(0.03f);
		times.add(0.03f);
		times.add(0.03f);
		times.add(0.03f);

		
		scaleX = 1.25f;
		scaleY = 1.25f;
		this.dustAnimator = new Animation(images,times,true);
		setSpecialArtWork(true);
	}

}
