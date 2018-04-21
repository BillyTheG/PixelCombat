package pixelCombat.view;

import pixelCombat.Math.Vector2d;

public class Test {

	public static void main(String[] args) {
		Test test = new Test();
		System.out.println(test.calculateDirection(new Vector2d(1,-4)));

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
