package telran.favorites.service.interfaces;

import telran.favorites.api.ResponceMessagingDto;
import telran.favorites.api.ResponseLostFoundDto;

public interface FavoritesManagement {
	
	ResponceMessagingDto[] getFavoriteMessagesPosts(String email);	//get ret x-token
	ResponseLostFoundDto[] getActivityLostFoundPosts(String email);	//get ret x-token
}
