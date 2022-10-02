package marcos.ferreira.project;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;


public class Database {
	private static ArrayList<House> houses;
	private static File db = new File("database");
	private static File ch = new File("change");

	public Database() {
		houses = new ArrayList<House>();	
	}
	
	public ArrayList<House> getHouses(){
		return houses;
	}
	
	public void addReservation(Reservation r) {
		for(int i = 0; i < 2; i++) {
			if(houses.get(i).getID() == r.getID()) {
				
				//Checking to see that the days selected are free.
				for(int x = 0; x < 3; x++) {
					if(houses.get(i).getReserved()[x] == true && r.getReserved()[x] == true) {
						System.out.println("Booking unsuccessful! (Err. days chosen are invalid)");
						return;
					}
				}
				// Setting occupied places to false
				houses.get(i).getReserved()[0] = (r.getReserved()[0] == true)?true:houses.get(i).getReserved()[0];
				houses.get(i).getReserved()[1] = (r.getReserved()[1] == true)?true:houses.get(i).getReserved()[1];
				houses.get(i).getReserved()[2] = (r.getReserved()[2] == true)?true:houses.get(i).getReserved()[2];
				houses.get(i).addReservation(new Reservation(r.getID(),r.getToWhom(),houses.get(i).getPrice(),
						r.getReserved()[0],r.getReserved()[1],r.getReserved()[2]));
				
				//Finally need to write the reservation to file
				try {
					FileWriter fw = new FileWriter(db,true);
					PrintWriter pw = new PrintWriter(fw);
					pw.print(r.getID()+" ");
					pw.println(houses.get(i).getPrice());
					pw.println(r.getToWhom());
					pw.println(r.getReserved()[0]);
					pw.println(r.getReserved()[1]);
					pw.println(r.getReserved()[2]);
					
					pw.close();
					fw.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
				System.out.println("Booking Successful!");
				return;
			}
		}
		System.out.println("Booking unsuccessful! (Err. ID chosen is invalid)");
	}
	
	public void readReservations() {
		try {
	
			Scanner in = new Scanner(db);
			//Consuming...
			for(int i = 0; i < 10; i++) {
				in.nextLine();
			}
			
			while(in.hasNext()) {
				int ID = in.nextInt();
				double price = in.nextDouble();
				in.nextLine();
				String toWhom = in.nextLine();
				boolean fri = in.nextBoolean();
				in.nextLine();
				boolean sat = in.nextBoolean();
				in.nextLine();
				boolean sun = in.nextBoolean();
				
				for(int i = 0; i < houses.size(); i++) {
					if(houses.get(i).getID() == ID) {
						houses.get(i).addReservation(new Reservation(ID,toWhom,price,fri,sat,sun));
						houses.get(i).getReserved()[0] = (fri == true)? true:houses.get(i).getReserved()[0];
						houses.get(i).getReserved()[1] = (sat == true)? true:houses.get(i).getReserved()[1];
						houses.get(i).getReserved()[2] = (sun == true)? true:houses.get(i).getReserved()[2]; 
						break;
					}
				}
			}
			
			in.close();
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public void init() {
		try {
			File db = new File("database");
			Scanner in = new Scanner(db);
			
			if(!in.hasNext()) {
				FileWriter fw = new FileWriter(db,true);
				PrintWriter pw = new PrintWriter(fw);
				
				pw.print(1+" ");
				pw.println(100+" ");
				pw.println(false);
				pw.println(false);
				pw.println(false);
				pw.println("3 bedroom(s) and 3 bathroom(s)");
				houses.add(new House(1,"3 bedroom(s) and 3 bathroom(s)", 100, false, false, false));
				
				pw.print(2+" ");
				pw.println(40);
				pw.println(false);
				pw.println(false);
				pw.println(false);
				pw.println("1 bedroom(s) and 1 bathroom(s)");
				houses.add(new House(2,"1 bedroom(s) and 1 bathroom(s)", 40, false, false, false));
				
				pw.close();
				fw.close();
				
			}else {
				for(int i = 0; i < 2; i++) {
					int ID = in.nextInt();
					double price = in.nextDouble();
					in.nextLine();
					boolean fri = in.nextBoolean();
					in.nextLine();
					boolean sat = in.nextBoolean();
					in.nextLine();
					boolean sun = in.nextBoolean();
					in.nextLine();
					String description = in.nextLine();
					houses.add(new House(ID,description,price,fri,sat,sun));
				}
				
			}
			in.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public void change() {
		if(ch.length() == 0) {
			System.out.println("FILE IS EMPTY!");
			return;
		}
		
		int tempID;
		double tempPrice;
		
		try {
			Scanner in = new Scanner(ch);
			while(in.hasNext()) {
				tempID = in.nextInt();
				tempPrice = in.nextDouble();
				
				for(int i = 0; i < houses.size(); i++) {
					if(houses.get(i).getID() == tempID) {
						houses.get(i).setPrice(tempPrice);
						break;
					}
				}
			}
			in.close();
			
			// Just to clear the change file (since the changes have been applied) and also clear the DB file
			FileWriter fw = new FileWriter(ch, false); 
	        PrintWriter pw = new PrintWriter(fw, false);
	        pw.flush();
	        pw.close();
	        fw.close();
	        
	        FileWriter fw1 = new FileWriter(db, false); 
	        PrintWriter pw1 = new PrintWriter(fw1, false);
	        pw1.flush();
	        pw1.close();
	        fw1.close();
	        
	        //Now we need to clear the database file and write the recent changes to it
	        FileWriter fw2 = new FileWriter(db, true); 
	        PrintWriter pw2 = new PrintWriter(fw2, true);
	        for(House h: houses) {
	        	pw2.print(h.getID()+" ");
				pw2.println(h.getPrice()+" ");
				pw2.println(h.getReserved()[0]);
				pw2.println(h.getReserved()[1]);
				pw2.println(h.getReserved()[2]);
				pw2.println(h.getDescription());
	        }
	        for(House h: houses) {
	        	for(Reservation r: h.getReservations()) {
	        		pw2.print(r.getID()+" ");
					pw2.println(r.getPrice());
					pw2.println(r.getToWhom());
					pw2.println(r.getReserved()[0]);
					pw2.println(r.getReserved()[1]);
					pw2.println(r.getReserved()[2]);
	        	}
	        }
	        
	        pw2.close();
	        fw2.close();
	        
	        
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	public void save() {
		try {
	        FileWriter fw1 = new FileWriter(db, false); 
	        PrintWriter pw1 = new PrintWriter(fw1, false);
	        pw1.flush();
	        pw1.close();
	        fw1.close();
	        
	        //After clearing the database file and write the recent changes to it
	        FileWriter fw2 = new FileWriter(db, true); 
	        PrintWriter pw2 = new PrintWriter(fw2, true);
	        
	        for(House h: houses) {
	        	pw2.print(h.getID()+" ");
				pw2.println(h.getPrice()+" ");
				pw2.println(h.getReserved()[0]);
				pw2.println(h.getReserved()[1]);
				pw2.println(h.getReserved()[2]);
				pw2.println(h.getDescription());
	        }
	        for(House h: houses) {
	        	for(Reservation r: h.getReservations()) {
	        		pw2.print(r.getID()+" ");
					pw2.println(r.getPrice());
					pw2.println(r.getToWhom());
					pw2.println(r.getReserved()[0]);
					pw2.println(r.getReserved()[1]);
					pw2.println(r.getReserved()[2]);
	        	}
	        }
	        
	        pw2.close();
	        fw2.close();
	        
	        
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void print() {
		for(House h: houses) {
			System.out.println("----------------------------------------------");
			h.toString();
			System.out.println("----------------------------------------------");
			for(Reservation r: h.getReservations()) {
				r.toString();
			}
		}
		System.out.println();
		System.out.println("TOTAL PROFIT: "+Reservation.getTotal());
	}
}