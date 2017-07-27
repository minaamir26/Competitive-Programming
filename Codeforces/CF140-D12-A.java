package Codeforces;

import java.util.Scanner;

public class NewYearTable_140A {
	
	/*
	 * I modeled the adjacent circles' radii connected together as a regular polygon
	 * I deduced a formula to get the radius of the circumcircle of some polygons
	 * angle of circumcircle of regular polygon with n sides = sin(360/(n*2)
	 */

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int n = sc.nextInt() , R = sc.nextInt() , r = sc.nextInt();
		if(n == 1)
			if(r <= R)
				System.out.println("YES");
			else
				System.out.println("NO");
		else
		{
			double angle = 2 * Math.PI / n / 2;
			double minRadius = r + r / Math.sin(angle);
			if(R >= minRadius)
				System.out.println("YES");
			else
				System.out.println("NO");
		}
		sc.close();
	}
}
