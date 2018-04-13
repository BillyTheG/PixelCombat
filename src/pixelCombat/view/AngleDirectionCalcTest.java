package pixelCombat.view;

import pixelCombat.Math.Vector2d;

public class AngleDirectionCalcTest {

	public static void main(String[] args) {
		AngleDirectionCalcTest test = new AngleDirectionCalcTest();
		System.out.println(test.calculateDirection(new Vector2d(1,-4)));// TODO Auto-generated method stub

	}

	public double calculateDirection(Vector2d direction)
	{
		
				
		double r = Math.sqrt(direction.x*direction.x + direction.y*direction.y );
		double angle = 0;
		if(direction.y >=0)
		{
			angle = Math.acos(direction.x/r)/Math.PI * 180;
		}
		else
		{
			angle = 0- Math.acos(direction.x/r)/Math.PI * 180;
		}
		return angle;	
	}
	
}
