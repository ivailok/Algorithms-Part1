package testing;

import implementations.IUnionFind;
import implementations.QuickFindUF;
import implementations.QuickUnionUF;
import java.util.Arrays;
import java.util.Collection;
import java.util.Random;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

@RunWith(Parameterized.class)
public class UFBasicPerformanceTester {
	private IUnionFind uf;
	
	public UFBasicPerformanceTester(IUnionFind uf)
	{
		this.uf = uf;
	}
	
	@Parameterized.Parameters(name = "{index}: {0}")
	public static Collection<Object[]> instanceToTest()
	{
		int size = 100000;
		return Arrays.asList(
				new Object[] { new QuickFindUF(size) },
				new Object[] { new QuickUnionUF(size) }
		);
	}
	
	@Test(timeout = 200)
	public void testUnionOfManyNodes()
	{
		Random rnd = new Random();
		for (int i = 0; i < 20000; i++)
		{
			uf.union(rnd.nextInt(100000), rnd.nextInt(100000));
		}
	}
	
	@Test(timeout = 200)
	public void testConnectionOfManyNodes()
	{
		Random rnd = new Random();
		for (int i = 0; i < 50000; i++)
		{
			int a = rnd.nextInt(100000), b = rnd.nextInt(100000);
			while (uf.connected(a, b))
			{
				a = rnd.nextInt(10000);
				b = rnd.nextInt(10000);
			}
			
			uf.union(rnd.nextInt(100000), rnd.nextInt(100000));
		}
	}
}
