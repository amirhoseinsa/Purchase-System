import java.util.ArrayList;
import java.util.Scanner;

/**
 * 
 * @author amir hosein Created on 19 May 2018
 */
public class Order {
	private Scanner scanner;

	ArrayList<Bill> bill = new ArrayList<Bill>();

	/**
	 * Take orders in console and fill bill ArrayList
	 * 
	 * @param items
	 * @return ArrayList of Bill
	 */
	public ArrayList<Bill> takeOrdesr(ArrayList<Item> items) {
		scanner = new Scanner(System.in);

		int id = -1, amount = -1, pointer = -1;
		Boolean duplicate;

		while (true) {
			id = scanner.nextInt();
			if (id == 0) {
				break;
			}

			amount = scanner.nextInt();

			duplicate = checkDuplicate(id, amount);
			if (duplicate == true) {
				continue;
			}

			// find the item position in items
			for (int i = 0; i < items.size(); i++) {
				if (items.get(i).getId() == id) {
					pointer = i;
					break;
				}
			}

			if (pointer == -1) {
				System.out.println("Doesn's exist in the list");
			} else {
				bill.add(new Bill(items.get(pointer), amount));
			}
		}
		return bill;
	}

	/**
	 * 
	 * @param id
	 * @param amount
	 *            Check that do we have this item in our bill or not if we have
	 *            we add amount to our bill
	 * @return duplicate (if it's be true it's mean that we don't need to
	 *         continue the loop process for the current input in "takeOrdesr"
	 *         function)
	 */
	private Boolean checkDuplicate(int id, int amount) {
		Boolean duplicate = false;
		for (int i = 0; i < bill.size(); i++) {
			if (bill.get(i).item.getId() == id) {
				bill.get(i).amount += amount;
				duplicate = true;
				break;
			}
		}
		return duplicate;
	}

	/**
	 * 
	 * @param tax
	 *            (final parameter in Main class)
	 * @return Calculated Price of the Order
	 */
	public double calculatePrice(double tax) {
		double price = 0;

		for (Bill currentItem : bill) {
			price += currentItem.item.getPrice() * currentItem.amount;
		}

		return price + price * tax;
	}

	/**
	 * 
	 */
	public void printBill() {
		for (Bill currentItem : bill) {
			System.out.println("Name: " + currentItem.item.getName()
					+ " _ Amount: " + currentItem.amount);
		}
	}
}
