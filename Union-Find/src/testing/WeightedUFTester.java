package testing;

import static org.junit.Assert.*;
import implementations.WeightedUF;
import org.junit.Test;

public class WeightedUFTester 
{
	@Test
	public void test1()
	{
		WeightedUF uf = new WeightedUF(10);
		uf.union(6, 2);
		uf.union(1, 9);
		uf.union(2, 9);
		uf.union(3, 0);
		uf.union(5, 3);
		uf.union(3, 7);
		uf.union(4, 3);
		uf.union(9, 7);
		uf.union(6, 8);
		
		int[] expected = { 3, 6, 6, 3, 3, 3, 3, 3, 3, 1 };
		int[] actual = new int[10];
		for (int i = 0; i < 10; i++)
		{
			actual[i] = uf.getId(i);
		}
		
		assertArrayEquals(expected, actual);
	}
	
	@Test
	public void test2()
	{
		WeightedUF uf = new WeightedUF(10);
		uf.union(4, 6);
		uf.union(8, 5);
		uf.union(6, 3);
		uf.union(7, 0);
		uf.union(1, 2);
		uf.union(7, 6);
		uf.union(5, 1);
		uf.union(0, 8);
		uf.union(8, 9);
		
		int[] expected = { 3, 6, 6, 3, 3, 3, 3, 3, 3, 1 };
		int[] actual = new int[10];
		for (int i = 0; i < 10; i++)
		{
			actual[i] = uf.getId(i);
			System.out.println(actual[i]);
		}
		
		assertArrayEquals(expected, actual);
	}
}
