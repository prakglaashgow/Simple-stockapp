package stockapp;


import static org.mockito.ArgumentMatchers.anyInt;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import stockapp.StockConstants;
import stockapp.dao.Impl.StockDAOImpl;
import stockapp.model.Stock;
import stockapp.service.Impl.StockserviceImpl;

public class StockServiceImplTest {
	StockserviceImpl service = new StockserviceImpl();
	private List<Stock> stocks = new ArrayList<>();
	StockDAOImpl mockDao = Mockito.mock(StockDAOImpl.class);

	@Before
	public void setUp() throws Exception {
		stocks.add(new Stock(1, "TEA", StockConstants.COMMON, 0, 0, 100));
		stocks.add(new Stock(2, "POP", StockConstants.COMMON, 8, 0, 100));
		stocks.add(new Stock(3, "ALE", StockConstants.COMMON, 23, 0, 60));
		stocks.add(new Stock(4, "GIN", StockConstants.PREFERRED, 8, 2, 100));
		Stock stock = new Stock(4, "GIN", StockConstants.PREFERRED, 8, 2, 100);
		Mockito.when(mockDao.getAllStocks()).thenReturn(stocks);
		Mockito.when(mockDao.getStockByID(anyInt())).thenReturn(stock);
	}

	@Test
	public void testCalculatePERatio() {
		String result = service.calculatePERatio(4, 12.5);
		Assert.assertNotNull(result);
	}

	@Test
	public void testCalculateDividendYieldPreferred() {
		String result = service.calculateDividentYield(4, 1);
		Assert.assertEquals("200.00", result);
	}

	@Test
	public void testCalculateDividendYield() {
		Stock stock = new Stock(1, "TEA", StockConstants.COMMON, 0, 0, 100);
		Mockito.when(mockDao.getStockByID(anyInt())).thenReturn(stock);
		String result = service.calculateDividentYield(1, 12.5);
		Assert.assertEquals("0.00", result);
	}

	@Test
	public void testGetAllStocks() {
		List<Stock> stockList = mockDao.getAllStocks();
		Assert.assertEquals(4, stockList.size());
	}

}