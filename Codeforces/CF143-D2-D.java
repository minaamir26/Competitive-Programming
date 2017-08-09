package Codeforces;

import java.util.Scanner;

public class HelpGeneral_143D
{
	public static void main(String[] args)
	{
		Scanner sc = new Scanner(System.in);

		int n = sc.nextInt();
		int m = sc.nextInt();
		int ans = 0;
		if(n == 1 || m == 1)
			ans = (n*m);
		else
		{
			if(n % 2 == 1 && m % 2 == 1)
				ans = ((n*m+1)/2);
			else
				ans = ((n*m)/2);
		}
		if(n == 2)
		{
			int cnt = (m/4) * 4;
			cnt+= Math.min(2, m%4) * 2;
			ans = Math.max(ans, cnt);
		}
		else
			if(m == 2)
			{
				int cnt = (n/4) * 4;
				cnt+= Math.min(2, n%4) * 2;
				ans = Math.max(ans, cnt);
			}
		System.out.println(ans);
	}

}
