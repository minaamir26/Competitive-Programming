public class BirthdayOdds
{
	public static int minPeople(int minOdds, int daysInYear)
	{
		int rev = 100 - minOdds;
		double prob = 1;
		int k = 0;
		for(int i=daysInYear;i >= 0; i--)
		{
			prob*=(i*1.0/daysInYear);
			if(prob * 100 < rev)
				return k + 1;
			k++;
		}
		return 0;
	}
}