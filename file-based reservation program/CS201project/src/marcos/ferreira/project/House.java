package marcos.ferreira.project;

import java.io.Serializable;
import java.util.ArrayList;

public class House implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -1880285893965946883L;
	private int ID;
	String description;
	private double price;
	private boolean reserved[];
	private ArrayList<Reservation> reservations;
	
	
	public House(int ID, String description, double price, boolean fri, boolean sat, boolean sun) {
		this.ID = ID;
		this.description = description;
		this.price = price;
		reserved = new boolean[3];
		reservations = new ArrayList<Reservation>();
	}
	
	public void setPrice(double price) {
		this.price = price;
	}
	
	public double getPrice() {
		return price;
	}
	
	public String getDescription() {
		return description;
	}
	
	public String toString() {
		System.out.print("House no. "+ID+": "+"Rent: ");
		System.out.println(price+"$ a day");
		System.out.println(description);
		
		return"";
	}
	

	public int getID() {
		return ID;
	}
	
	public void addReservation(Reservation reservation) {
		reservations.add(reservation);
	}
	
	public ArrayList<Reservation> getReservations(){
		return reservations;
	}
	
	public boolean[] getReserved() {
		return reserved;
	}
}
