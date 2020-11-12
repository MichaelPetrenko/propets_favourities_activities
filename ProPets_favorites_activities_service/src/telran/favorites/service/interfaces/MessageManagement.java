package telran.favorites.service.interfaces;

import telran.favorites.api.RequestCreatePostDto;
import telran.favorites.api.ResponceMessagingDto;
import telran.favorites.api.ResponcePageableDto;

public interface MessageManagement {
	
	ResponceMessagingDto createPost(RequestCreatePostDto dto, String userLogin); //get ret x-token
	ResponceMessagingDto update(RequestCreatePostDto dto, String id); //get ret x-token
	ResponceMessagingDto delete(String id); //get ret x-token
	ResponceMessagingDto getPostById(String id); //get ret x-token;
	ResponcePageableDto viewPostPageable(int items, int currentPage); //get ret x-token;
	Object[] getUserData(String[] listID);
	
}
