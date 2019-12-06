package stockapp;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import stockapp.StockConstants;
import stockapp.dao.Impl.StockDAOImpl;
import stockapp.model.Stock;

import junit.framework.Assert;

public class StockDAOImplTest {
	private List<Stock> stocks = new ArrayList<>();
	StockDAOImpl mockDao = org.mockito.Mockito.mock(StockDAOImpl.class);

	@Before
	public void setUp() throws Exception {
		stocks.add(new Stock(1, "TEA", StockConstants.COMMON, 0, 0, 100));
		stocks.add(new Stock(2, "POP", StockConstants.COMMON, 8, 0, 100));
		stocks.add(new Stock(3, "ALE", StockConstants.COMMON, 23, 0, 60));
		stocks.add(new Stock(4, "GIN", StockConstants.PREFERRED, 8, 2, 100));
		Mockito.when(mockDao.getAllStocks()).thenReturn(stocks);
	}

	@SuppressWarnings("deprecation")
	@Test
	public void testGetAllStocks() {
		List<Stock> stockTest = mockDao.getAllStocks();
		Assert.assertEquals(4, stockTest.size());
	}

	@SuppressWarnings("deprecation")
	@Test
	public void testGetStockByID() {
		StockDAOImpl stockDao = new StockDAOImpl();
		Stock stock = stockDao.getStockByID(2);
		Assert.assertEquals("POP", stock.getStockName());
	}

}