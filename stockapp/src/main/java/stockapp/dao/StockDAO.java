package stockapp.dao;

import java.util.List;

import stockapp.model.Stock;

public interface StockDAO {

	public List<Stock> getAllStocks();

	public Stock getStockByID(int stockID);

}