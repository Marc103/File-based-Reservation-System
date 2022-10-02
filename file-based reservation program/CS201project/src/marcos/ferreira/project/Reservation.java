package marcos.ferreira.project;

import java.io.Serializable;



public class Reservation implements Serializable {
	
	private static final long serialVersionUID = 4670635049168421978L;

	private static double total;
	private int ID;
	private String toWhom;
	private double price;
	private boolean reserved[];
	private static String literals[] = {"Friday","Saturday","Sunday"};
	
	
	
	public Reservation(int ID, String toWhom, double price, boolean fri, boolean sat, boolean sun) {
		this.ID = ID;
		this.toWhom = toWhom;
		this.price = price;
		reserved = new boolean[3];
		reserved[0] = fri;
		reserved[1] = sat;
		reserved[2] = sun;
		for(int i = 0; i < 3; i++) {
			if(reserved[i] == true) {
				total += price;
			}
		}
		
	}
	
	public String getToWhom() {
		return toWhom;
	}
	
	public void setPrice(double price) {
		this.price = price;
	}
	
	public double getPrice() {
		return price;
	}
	public int getID() {
		return ID;
	}
	
	public String toString() {
		System.out.print(toWhom+" booked ");
		for(int i = 0; i < 3; i++) {
			if(reserved[i] == true) {
				System.out.print(literals[i]+", ");
			}
		}
		System.out.println("\n(at time of booking, rent of "+price+"$ a day)");
		System.out.println();
		return "";
	}
	
	public static double getTotal() {
		return total;
	}
	
	public static String[] getLiterals() {
		return literals;
	}
	
	public boolean[] getReserved() {
		return reserved;
	}

}
