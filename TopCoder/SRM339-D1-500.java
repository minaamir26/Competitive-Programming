import java.util.Arrays;

public class TestBettingStrategy
{
	static int goal;
	static double pro, memo[][][];
	
	static double solve(int idx, int curSum, int bett)
	{
		if(curSum >= goal)
			return 1;
		if(idx == 0)
			return 0;
		if(memo[idx][curSum][bett] != -1)
			return memo[idx][curSum][bett];
		double ret = 0;
		int bet = (1<<bett);
		ret+=pro*solve(idx-1, curSum + bet, 0);
		if(curSum - bet >= bet * 2)
			ret+=(1-pro)*solve(idx-1, curSum - bet, bett + 1);
		return memo[idx][curSum][bett] = ret;
	}
	
	public static double winProbability(int initSum, int goalSum, int rounds, int prob)
	{
		pro = prob / 100.0;
		goal = goalSum;
		memo = new double[rounds + 1][goalSum + 1][13];
		for (int i = 0; i < memo.length; i++)
			for (int j = 0; j < memo[i].length; j++)
				Arrays.fill(memo[i][j], -1);
		return solve(rounds, initSum, 0);
	}
	public static void main(String[] args)
	{
		System.out.println(winProbability(10, 20, 20, 50));
	}
}
