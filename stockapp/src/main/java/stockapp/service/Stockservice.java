package stockapp.service;

import java.util.List;
import stockapp.model.Stock;

public interface Stockservice {

	public List<Stock> getAllStocks();

	public Stock getStockByID(int stockID);

	public String calculateDividentYield(int stockID, double price);

	public String calculatePERatio(int stockID, double price);

}