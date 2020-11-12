package telran.favorites.api;

import java.util.HashSet;

public class Activ {

	public HashSet<String> message;
	public HashSet<String> lostFound;
	public HashSet<String> hotels;
	
	public Activ() {}

	public Activ(HashSet<String> message, HashSet<String> lostFound, HashSet<String> hotels) {
		super();
		this.message = message;
		this.lostFound = lostFound;
		this.hotels = hotels;
	}
	
	
	
}
