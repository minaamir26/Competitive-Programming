import java.util.Arrays;

public class TheTicketsDivTwo
{
	static double[] ans;
	static int n1,m1,k1;
	
	static void bt(int idx, int[] arr, double prop)
	{
		if(idx == k1 || arr.length == 1)
		{
			ans[arr[0]]+=prop;
			return;
		}
		ans[arr[0]]+=prop/6.0;
		int[] arr2 = new int[arr.length];
		for(int i=0;i<arr.length;i++)
			arr2[i] = arr[(1+i)%arr.length];
		bt(idx+1,arr2,prop * 0.5);
		bt(idx+1,Arrays.copyOfRange(arr, 1, arr.length),prop/3);
	}
	
	public static double find(int n, int m, int k)
	{
		ans = new double[n];
		n1 = n;
		m1 = m;
		k1 = k;
		int[] arr = new int[n];
		for(int i=0;i<n;i++)
			arr[i] = i;
		
		bt(0,arr,1);
		System.out.println(Arrays.toString(ans));
		return ans[m-1];
	}
}
