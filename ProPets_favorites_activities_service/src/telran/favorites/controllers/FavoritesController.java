package telran.favorites.controllers;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import telran.favorites.api.FavoritesApiConstants;
import telran.favorites.api.ResponceMessagingDto;
import telran.favorites.api.ResponseLostFoundDto;
import telran.favorites.service.interfaces.FavoritesManagement;

@RestController
public class FavoritesController {

	@Autowired
	FavoritesManagement favoritesService;
	
	@GetMapping(value = FavoritesApiConstants.GET_FAVORITE_MESSAGES_POST)
	ResponceMessagingDto[] getFavoriteMessagesPosts(@PathVariable("login") String login) {
		return favoritesService.getFavoriteMessagesPosts(login);
	}
	
	@GetMapping(value = FavoritesApiConstants.GET_ACTIVITY_LOSTFOUND_POSTS)
	ResponseLostFoundDto[] getActivityLostFoundPosts(@PathVariable("login") String login) {
		return favoritesService.getActivityLostFoundPosts(login);
	}
	
}
