package marcos.ferreira.project;

import java.util.Scanner;

public class Client {
	private static Database db = new Database();
	
	public static void main(String args[]) {
		Scanner in = new Scanner(System.in);
		
		int house;
		String toWhom;
		Scanner days;
		boolean[] reserved = {false,false,false};


		db.init();
		db.readReservations();
		
		System.out.println("Welcome to the client side\n");
		
		//db.print();
		showAvailability();
		System.out.println("Which house would you like to reserve?: (e.g '1' for house 1 )(-1 to exit)");
		house = in.nextInt();
		
		while(house != -1) {
			
			System.out.print("Which days? (e.g '2 3' for Saturday and Sunday): ");
			in.nextLine();
			days = new Scanner(in.nextLine());
			while(days.hasNext()) {
				reserved[days.nextInt()-1] = true;
			}
			
			System.out.print("What is your name?: ");
			toWhom = in.nextLine();
			
			db.addReservation(new Reservation(house,toWhom,0,reserved[0],reserved[1],reserved[2]));
			
			db.save();
			
			reserved[0] = false;
			reserved[1] = false;
			reserved[2] = false;
			
			showAvailability();
			System.out.println("Which house would you like to reserve?: (e.g '1' for house 1 )(-1 to exit)");
			house = in.nextInt();
		}
			
		
		in.close();
		
		
		
	}
	
	public static void showAvailability() {
		for(House h: db.getHouses()) {
			if(h.getReserved()[0] == false || h.getReserved()[1] == false || h.getReserved()[2] == false) {
				System.out.println(h.getID()+".   "+h.getDescription()+" ("+h.getPrice()+"$ rent per day)");
				System.out.println("Avaible days: ");
				for(int i = 0; i < 3; i++) {
					if(h.getReserved()[i] == false) {
						System.out.println("      "+(i+1)+".   "+Reservation.getLiterals()[i]);
					}
				}
			}
			System.out.println();
			
		}
		
	}
}
