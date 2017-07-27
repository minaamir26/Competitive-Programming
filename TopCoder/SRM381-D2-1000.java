import java.util.Arrays;
import java.util.Comparator;

public class TheNumbersLord {
	
	public static String create(int[] numbers, int n)
	{
		int extra = n - numbers.length;
		
		String[] arr = new String[numbers.length];
		for(int i=0;i<arr.length;i++)
			arr[i] = "" + numbers[i];
		Arrays.sort(arr , new Comparator<String>() {
			public int compare(String a , String b)
			{
				if(a.length() != b.length())
					return b.length() - a.length();
				return b.compareTo(a);
			}
		});
		
		String ex = arr[0];
		String[] a = new String[n];
		for(int i=0;i<arr.length;i++)
			a[i] = arr[i];
		int k = arr.length;
		while(extra-->0)
			a[k++] = ex;
		
		Arrays.sort(a , new Comparator<String>() {
			public int compare(String a , String b)
			{
				return -1 * (a+b).compareTo(b+a);
			}
		});
		System.out.println(Arrays.toString(a));
		StringBuilder sb = new StringBuilder();
		for(int i=0;i<a.length;i++)
			sb.append(a[i]);
		return sb.toString();
	}
}
