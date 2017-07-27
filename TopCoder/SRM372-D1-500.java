

import java.util.Arrays;

public class RoundOfEleven {
	static long[][][] memo;
	static String x;
	static int end;
	public static long maxIncome(int n, int money){

		x = "" + n;
		end = x.length();
		memo = new long[11][11][money+1];
		for (int i = 0; i < memo.length; i++) {
			for (int j = 0; j < memo[i].length; j++) {
				Arrays.fill(memo[i][j], -1);
			}
		}

		return solve(0, x.length()-1, money);
	}



	static long solve(int mod, int idx, int rem)
	{
		if(rem < 0)
			return 0;
		if(idx == -1)
			if(mod == 0)
				return rem;
			else
				return 0;
		if(memo[mod][idx][rem] != -1)
			return memo[mod][idx][rem];

		int cur = x.charAt(idx)-'0';
		int sign = 1;
		if(idx % 2 == 1)
			sign = -1;
		long ret = 0;
		for(int i=0;i<=9;i++)
			ret = ret + solve((mod + sign * i + 11)%11, idx-1, rem- Math.abs(cur-i));
		return memo[mod][idx][rem] = ret;
	}

	public static void main(String[] args) {
		System.out.println(maxIncome(19759 , 435));
	}

}
