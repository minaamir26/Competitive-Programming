import java.util.Arrays;

public class BagsOfGold
{
	static int n;
	static int[] arr;
	static int[][][] memo;
	//7:58
	static int solve(int s,int e,int turn)
	{
		if(s > e)
			return 0;
		if(memo[turn][s][e] != -1)
			return memo[turn][s][e];
		int ret = 0;
		if(turn == 1)
			ret = Math.min(-arr[s] + solve(s+1, e, 0), -arr[e] + solve(s, e-1, 0));
		else
			ret = Math.max(arr[s] + solve(s+1, e, 1), arr[e] + solve(s, e-1, 1));
		return memo[turn][s][e] = ret;
	}
	public static int netGain(int[] bags)
	{
		arr = bags;
		n = arr.length;
		memo = new int[2][n+1][n+1];
		for(int[][] a : memo)
			for(int [] aa : a)
			Arrays.fill(aa, -1);
		return solve(0,n-1,0);
	}
}
