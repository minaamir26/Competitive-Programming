public class MissileTarget
{
	/*
	 * Very easy one, just get the average of x and average of y .. that is the answer
	 */
	public static int[] bestFit(int[] x, int[] y)
	{
		int sumX = 0, sumY = 0;
		for(int i=0;i<x.length;i++)
		{
			sumX+=x[i];
			sumY+=y[i];
		}
		return new int[]{(int)Math.round(sumX*1.0/x.length) , (int)Math.round(sumY*1.0/y.length)}; 
	}
}
