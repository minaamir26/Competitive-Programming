package Codeforces;

import java.util.HashMap;
import java.util.Scanner;

public class AAndBAndInterstingSubstrings
{
	
	public static void main(String[] args)
	{
		Scanner sc = new Scanner(System.in);
		int[] arr = new int[26];
		for(int i=0;i<26;i++)
			arr[i] = sc.nextInt();
		
		String s = sc.next();
		HashMap<Long, Integer> map[] = new HashMap[26];
		for(int i=0;i<26;i++)
			map[i] = new HashMap<>();
		long ans = 0;
		long sum = 0;
		for(int i=0;i<s.length();i++)
		{
			int c = s.charAt(i)-'a';
			Integer add = map[c].get(sum);
			if(add == null) add = 0;
			ans+=add;
			sum+=arr[c];
			Integer prev = map[c].get(sum);
			if(prev == null) prev = 0;
			map[c].put(sum, prev + 1);
		}
		System.out.println(ans);
	}
	
}
