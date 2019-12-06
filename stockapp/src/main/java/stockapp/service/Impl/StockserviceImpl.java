package stockapp.service.Impl;

import java.text.DecimalFormat;
import java.util.List;

import stockapp.StockConstants;
import stockapp.dao.StockDAO;
import stockapp.dao.Impl.StockDAOImpl;
import stockapp.model.Stock;
import stockapp.service.Stockservice;

public class StockserviceImpl implements Stockservice {

	private StockDAO stockDAO = new StockDAOImpl();
	DecimalFormat df = new DecimalFormat("0.00");

	@Override
	public List<Stock> getAllStocks() {
		return stockDAO.getAllStocks();
	}

	public Stock getStockByID(int stockID) {
		return stockDAO.getStockByID(stockID);
	}

	@Override
	public String calculateDividentYield(int stockID, double price) {
		Stock stock = getStockByID(stockID);
		double dividendYield;
		if (stock.getType().equalsIgnoreCase(StockConstants.COMMON)) {
			dividendYield = stock.getLastDividend() / price;
		} else {
			dividendYield = (stock.getFixedDividend() * stock.getParValue()) / price;
		}
		return df.format(dividendYield);
	}

	public String calculatePERatio(int stockID, double price) {
		Stock stock = getStockByID(stockID);
		double peRatio = price / stock.getLastDividend();
		if (Double.isInfinite(peRatio)) {
			System.out.println(StockConstants.DIVIDE_BY_ZERO_ERROR);
			peRatio = 0.0;
		}
		return df.format(peRatio);
	}

}