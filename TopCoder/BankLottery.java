import java.util.Arrays;

public class BankLottery
{
	static long sum, jackpot, weeks;
	static long ana;
	static double[][] memo;
	static double solve(int idx, int taken)			//Expected Win Amount Only without The Beginning !
	{
		if(idx == weeks)
			return 0;
		if(memo[idx][taken] != -1)
			return memo[idx][taken];
		long mine = ana + taken * jackpot;
		long total = sum + idx * jackpot;
		long others = total - mine;
		double ret = 1.0 * mine / total * ( jackpot + solve(idx+1, taken+1)) + 
				1.0 * others / total * (solve(idx+1, taken));
		return memo[idx][taken] = ret;
	}
	
	public static double expectedAmount(int[] accountBalance, int weeklyJackpot, int weekCount)
	{
		for(int i=0;i<accountBalance.length;i++)
			sum = sum + accountBalance[i];
		weeks = weekCount;
		jackpot = weeklyJackpot;
		ana = accountBalance[0];
		memo = new double[weekCount + 1][weekCount + 1];
		for(int i=0;i<memo.length;i++)
			Arrays.fill(memo[i], -1);
		return ana + solve(0, 0);
	}
}
