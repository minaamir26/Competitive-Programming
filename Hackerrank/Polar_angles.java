import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Scanner;

public class Polar_angles {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		Pair[] arr = new Pair[n];
		for(int i=0;i<n;i++)
		{
			int x = sc.nextInt();
			int y = sc.nextInt();
			double angle = 0;
			if(x == 0 && y > 0)
				angle = 90;
			else
				if(x == 0 && y < 0)
					angle = 270;
				else
					if(y == 0 && x > 0)
						angle = 0;
					else
						if(y == 0 && x < 0)
							angle = 180;
						else
						{
							angle = 180 / Math.PI * Math.atan(y*1.0/x);
							if(angle > 0)
								if(x < 0 && y < 0)
									angle+=180;
							else
								if(x < 0 && y > 0)
									angle+=180;
								else
									angle+=360;
						}
			double dis = Math.sqrt(x*x+y*y);
			arr[i] = new Pair(angle,dis,x,y);
			
		}
		Arrays.sort(arr);
		PrintWriter pw = new PrintWriter(System.out);
		for(Pair x : arr)
			pw.println(x.x + " " + x.y);
		pw.flush();
	}
	
	
	static class Pair implements Comparable<Pair>{
		double angle , dis;
		int x, y;
		Pair(double a , double b, int aa , int bb)
		{
			angle = a;
			dis = b;
			x = aa;
			y = bb;
		}
		@Override
		public int compareTo(Pair o) {
			if(angle != o.angle)
				if(angle > o.angle)
					return 1;
				else
					return -1;
			if(dis > o.dis)
				return 1;
			else
				return -1;
		}
	}
}
