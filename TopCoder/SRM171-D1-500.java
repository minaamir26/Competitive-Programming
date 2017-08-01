import java.util.HashSet;

public class ResistorCombinations
{
	static double[] arr;
	static int n;
	static int[] perm;
	static HashSet<Double> solve(int st, int en)
	{
		HashSet<Double> loc = new HashSet<>();
		for(int i=st;i<=en;i++)
			loc.add(arr[perm[st]]);
		if(st == en)
		{
			loc.add(arr[perm[st]]);
		}
		else
		{
			for(int i=st;i<en;i++)
			{
				HashSet<Double> x = solve(st, i);
				HashSet<Double> y = solve(i+1, en);
				for (Double xx : x)
					for (Double yy : y)
					{
						double anss = 0;
						anss = xx + yy;
						loc.add(anss);
						anss = 1/(1/xx+1/yy);
						loc.add(anss);
					}
			}
		}
		return loc;

	}
	public static double closestValue(String[] resistances, double target)
	{
		arr = new double[resistances.length];
		n = arr.length;
		for (int i = 0; i < n; i++)
			arr[i] = Double.parseDouble(resistances[i]);
		perm = new int[n];
		for (int i = 0; i < n; i++)
			perm[i] = i;
		double ans = 0;
		double min = Double.MAX_VALUE;
		do
		{
			HashSet<Double> now = (solve(0, n-1));
//			Collections.sort(now);
//			System.out.println(now);
			//			System.out.println(now.size());
			for (Double d : now)
			{
				if(Math.abs(d-target) < min)
				{
					min = Math.abs(d-target);
					ans = d;
				}
			}
			//			System.out.println(min);
		} while (nextPermutation(perm));
		return ans;
	}

	public static void main(String[] args)
	{
		double x = 
				closestValue(new String[]	
						{"9.381", "7.93", "7.48", "6.604", "3.386"}, 1.359);
		System.out.println(x);
	}


	static boolean nextPermutation(int[] c)
	{
		int first = getFirst(c);
		if (first == -1)
			return false;
		int toSwap = c.length - 1;
		while (c[first] >= c[toSwap])
			--toSwap;
		swap(c, first++, toSwap);
		toSwap = c.length - 1;
		while (first < toSwap)
			swap(c, first++, toSwap--);
		return true;
	}
	static int getFirst(int[] c)
	{
		for (int i = c.length - 2; i >= 0; i--)
			if (c[i] < c[i + 1])
				return i;
		return -1;
	}
	static void swap(int[] c, int i, int j)
	{
		int tmp = c[i];
		c[i] = c[j];
		c[j] = tmp;
	}

}
