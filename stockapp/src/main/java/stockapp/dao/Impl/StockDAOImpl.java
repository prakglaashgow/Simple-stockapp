package stockapp.dao.Impl;

import java.util.ArrayList;
import java.util.List;

import stockapp.StockConstants;
import stockapp.dao.StockDAO;
import stockapp.model.Stock;

public class StockDAOImpl implements StockDAO {

	private List<Stock> stocks;

	public StockDAOImpl() {
		stocks = new ArrayList<>();
		stocks.add(new Stock(1, "TEA", StockConstants.COMMON, 0, 0, 100));
		stocks.add(new Stock(2, "POP", StockConstants.COMMON, 8, 0, 100));
		stocks.add(new Stock(3, "ALE", StockConstants.COMMON, 23, 0, 60));
		stocks.add(new Stock(4, "GIN", StockConstants.PREFERRED, 8, 2, 100));
		stocks.add(new Stock(5, "JOE", StockConstants.COMMON, 13, 0, 250));
	}

	@Override
	public List<Stock> getAllStocks() {
		return stocks;
	}

	@Override
	public Stock getStockByID(int stockID) {
		for (Stock stockObj : stocks) {
			if (stockObj.getStockID() == stockID) {
				return stockObj;
			}
		}
		return null;
	}
}