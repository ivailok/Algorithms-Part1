package testing;

import static org.junit.Assert.assertEquals;
import java.util.Arrays;
import java.util.Collection;
import implementations.IUnionFind;
import implementations.QuickFindUF;
import implementations.QuickUnionUF;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

@RunWith(Parameterized.class)
public class UFFunctionalityTester
{
	private IUnionFind uf;
	
	public UFFunctionalityTester(IUnionFind uf)
	{
		this.uf = uf;
	}
	
	@Parameterized.Parameters(name = "{index}: {0}")
	public static Collection<Object[]> instanceToTest()
	{
		int size = 5;
		return Arrays.asList(
				new Object[] { new QuickFindUF(size) },
				new Object[] { new QuickUnionUF(size) }
		);
	}
	
	@Test
	public void testConnectedNodes()
	{
		uf.union(0, 3);
		uf.union(0, 1);
		
		assertEquals(true, uf.connected(1, 3));
	}
	
	@Test
	public void testNonConnectedNodes()
	{
		uf.union(0, 3);
		uf.union(0, 1);
		
		assertEquals(false, uf.connected(2, 3));
	}
}
