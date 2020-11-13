package telran.favorites.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import telran.favorites.api.FavoritesApiConstants;
import telran.favorites.api.ResponceMessagingDto;
import telran.favorites.api.ResponsePostDto;
import telran.favorites.service.interfaces.FavoritesManagement;

@RestController
public class FavoritesController {

	@Autowired
	FavoritesManagement favoritesService;
	
	@GetMapping(value = FavoritesApiConstants.GET_FA_MESSAGES_POSTS)
	ResponceMessagingDto[] getFavoriteMessagesPosts(@PathVariable("login") String login, @PathVariable("service") String service) {
		return favoritesService.getFavoriteMessagesPosts(login, service);
	}
	
	@GetMapping(value = FavoritesApiConstants.GET_FA_LOSTFOUND_POSTS)
	ResponsePostDto[] getActivityLostFoundPosts(@PathVariable("login") String login, @PathVariable("service") String service) {
		return favoritesService.getActivityLostFoundPosts(login, service);
	}
	
	//HERE WILL BE HOTELS GETTER
	
}
