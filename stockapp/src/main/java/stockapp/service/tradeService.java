package stockapp.service;

import java.util.List;
import stockapp.model.Stock;

public interface tradeService {

	public void recordTrade(int stockID, int quanity, int type, double price);

	public double getVolumeWeightedStockPrice(int stockID, int mins);

	public double calculateGBCE(List<Stock> stock);
}