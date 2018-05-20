import java.util.ArrayList;

/**
 * 
 * @author amir hosein Created on 19 May 2018
 */
public class Main {
	static private ArrayList<Item> items = new ArrayList<Item>();
	final static double tax = 0; // assume that tax is 0 percent

	public static void main(String[] args) {
		double price = 0;

		DB db = new DB();
		Order order = new Order();

		db.readRecordsAndFillItems(items);

		// Show items
		System.out.println("Welcome to out shop . please choose your options:");
		for (int i = 1; i < items.size(); i++) {
			Item currentItem = items.get(i);
			System.out.println(currentItem.getId() + "_ "
					+ currentItem.getName() + " : " + currentItem.getPrice()
					+ "$");
		}
		System.out
				.println(items.get(0).getId() + "_ " + items.get(0).getName());

		order.takeOrdesr(items);

		order.printBill();

		price = order.calculatePrice(tax);
		System.out.println("Price : " + price);

		db.addRecordsToORDERS(order.bill, price);
		
		// you can use this line to see the ORDERS_DETAIL DB
		// db.readORDERS_DETAIL();
	}

}