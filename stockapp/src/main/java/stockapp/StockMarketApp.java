package stockapp;

import java.text.DecimalFormat;
import java.util.List;
import java.util.Scanner;

import stockapp.model.Stock;
import stockapp.service.Stockservice;
import stockapp.service.tradeService;
import stockapp.service.Impl.StockserviceImpl;
import stockapp.service.Impl.tradeServiceImpl;

public class StockMarketApp {

	Stockservice stockService = new StockserviceImpl();
	tradeService tradeService = new tradeServiceImpl();

	static Scanner input = new Scanner(System.in);
	
	DecimalFormat df = new DecimalFormat("00.00");

	public static void main(String[] args) {
		StockMarketApp stockMarket = new StockMarketApp();

		int option;
		int stockId;
		double price;
		try {
			do {
				displayActionMenu();
				option = input.nextInt();
				switch (option) {
				case 1:
					stockId = stockMarket.getStockIDFromUser();
					if (stockId != 6) {
						price = stockMarket.getPriceFromUser();
						if (price > 0.0) {
							System.out.println("Dividend Yield of the stock is "
									+ stockMarket.calculateDividentYield(stockId, price) + " ##");
						}
					}
					break;
				case 2:
					stockId = stockMarket.getStockIDFromUser();
					if (stockId != 6) {
						price = stockMarket.getPriceFromUser();
						if (price > 0.0) {
							System.out.println("P/E Ratio for the stock is "
									+ stockMarket.calculatePERatio(stockId, price) + " ##");
						}
					}
					break;
				case 3:
					stockId = stockMarket.getStockIDFromUser();
					if (stockId != 6) {
						int numbers = stockMarket.getQuantityFromUser();
						if (numbers > 0) {
							int type = stockMarket.getTransactionTypeFromUser();
							price = stockMarket.getPriceFromUser();
							stockMarket.addtrans(stockId, numbers, type, price);
						}
					}
					System.out.println("The transaction is successful. ##");
					break;
				case 4:
					stockId = stockMarket.getStockIDFromUser();
					if (stockId != 6) {
						System.out.println(stockMarket.getVolumeWeightedStockPrice(stockId));
					}
					break;
				case 5:
					System.out.println(stockMarket.calculateGBCE());
					break;
				default:
					if (option != 6) {
						System.out.println("Invalid choice");
					} else {
						input.close();
						System.out.println("Application is closing now");
					}
				}
			} while (option != 6);
		} catch (Exception e) {
			System.out.println(StockConstants.INVALID_DATA);
			System.exit(0);
		}
	}

	private static void displayActionMenu() {
		System.out.println("Please select an option to perform  your desired action");
		System.out.println("1. Calculate Dividend.");
		System.out.println("2. Calculate P/E ratio.");
		System.out.println("3. Record a trade.");
		System.out.println("4. Calculate Volume Weighted Stock Price");
		System.out.println("5. Calculate the GBCE");
		System.out.println("6. Quit.");
	}

	private static void displayAvailableStocks() {
		System.out.println("Please select the stock ");
		System.out.println("1. TEA");
		System.out.println("2. POP");
		System.out.println("3. ALE");
		System.out.println("4. GIN");
		System.out.println("5. JOE");
		System.out.println("6. Main Menu");
	}

	public int getStockIDFromUser() {
		int stockID = 0;
		try {
			do {
				displayAvailableStocks();
				stockID = input.nextInt();
				if (stockID < 1 || stockID > 6) {
					System.out.println("Invalid Stock Selected.");
				}
			} while ((stockID < 1 || stockID > 6));
		} catch (Exception e) {
			System.out.println(StockConstants.INVALID_DATA);
			System.exit(0);
		}
		return stockID;
	}

	public double getPriceFromUser() {
		double price = 0.0;
		try {
			do {
				System.out.println("Enter the price");
				price = input.nextDouble();
				if (price <= 0.0) {
					System.out.println("Invalid Price!!!");
				}
			} while (price <= 0.0);
		} catch (Exception e) {
			System.out.println(StockConstants.INVALID_DATA);
			System.exit(0);
		}
		return price;
	}

	public int getQuantityFromUser() {
		int quantity = 0;
		try {
			do {
				System.out.println("Enter the Quantity");
				quantity = input.nextInt();
				if (quantity <= 0) {
					System.out.println("Invalid Quantity!!!");
				}
			} while (quantity <= 0);
		} catch (Exception e) {
			System.out.println(StockConstants.INVALID_DATA);
			System.exit(0);
		}
		return quantity;
	}

	public int getTransactionTypeFromUser() {
		int type = 0;
		try {
			do {
				System.out.println("Enter the transaction type");
				System.out.println("1.BUY");
				System.out.println("2.SELL");
				type = input.nextInt();
				if (type != 1 || type != 2) {
					System.out.println("Invalid transaction type!!!");
				}
			} while (!(type == 1 || type == 2));
		} catch (Exception e) {
			System.out.println(StockConstants.INVALID_DATA);
			System.exit(0);
		}
		return type;
	}

	public String calculateDividentYield(int stockID, double price) {
		return stockService.calculateDividentYield(stockID, price);
	}

	public String calculatePERatio(int stockID, double price) {
		return stockService.calculatePERatio(stockID, price);
	}

	public void addtrans(int stockID, int quanity, int type, double price) {
		tradeService.recordTrade(stockID, quanity, type, price);
	}

	public String getVolumeWeightedStockPrice(int stockID) {
		String message;
		double volumeWeightStockPrice = tradeService.getVolumeWeightedStockPrice(stockID, 5);
		if (volumeWeightStockPrice == 0.0) {
			message = "No Data is present for the selected stock.";
		} else {
			message = "**** The Volume Weighted Stock Price is " + df.format(volumeWeightStockPrice) + " ****";
		}

		return message;
	}

	public String calculateGBCE() {
		String message;
		List<Stock> stocks = stockService.getAllStocks();
		double geometricMean = tradeService.calculateGBCE(stocks);
		if (geometricMean == 0.0) {
			message = "No Data to calculate GBCE Index.";
		} else {
			message = "**** The GBCE All Share Index is " + df.format(geometricMean) + " ****";
		}
		return message;
	}
}
