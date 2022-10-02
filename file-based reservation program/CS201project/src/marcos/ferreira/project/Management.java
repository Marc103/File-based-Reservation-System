package marcos.ferreira.project;

import java.util.Scanner;

public class Management {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		String input;
		System.out.println("Welcome to the management side");
		System.out.println("Here you can see the houses, their current pricing");
		System.out.println("and respective reservations");
		Database db = new Database();
		db.init();
		db.readReservations();
		db.print();
		
		System.out.println("Apply updates from file?('y' to proceed)(anything else to exit)");
		input = in.nextLine();
		
		while(input.equals("y")) {
			db.change();
			db.print();
			System.out.println("Apply updates from file?('y' to proceed)(anything else to exit)");
			input = in.nextLine();
		}
		
		in.close();
	}

}
