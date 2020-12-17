package telran.favorites.api;

public interface FavoritesApiConstants {
	
	String GET_FA_MESSAGES_POSTS = 		"/en/v1/messages/{service}/{login}";
	String GET_FA_LOSTFOUND_POSTS = 	"/en/v1/lostfound/{service}/{login}";
	String GET_FA_HOTELS_POSTS = 		"/en/v1/hotels/{service}/{login}";
	String WAKEUP = 					"/wakeup";
	// Attention! favouritesactivities => fav
	// Attention! Added path var "service"
	//activities or favorites

}
