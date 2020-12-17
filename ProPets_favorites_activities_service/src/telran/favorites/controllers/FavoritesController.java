package telran.favorites.controllers;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import telran.favorites.api.FavoritesApiConstants;
import telran.favorites.api.ResponceMessagingDto;
import telran.favorites.api.ResponseLostFoundDto;
import telran.favorites.service.interfaces.FavoritesManagement;

@RestController
public class FavoritesController {

	@Autowired
	FavoritesManagement favoritesService;

	@GetMapping(value = FavoritesApiConstants.GET_FA_MESSAGES_POSTS)
	ResponceMessagingDto[] getFavoriteMessagesPosts(@PathVariable("login") String login,
			@PathVariable("service") String service, HttpServletRequest request) {
		String xToken = request.getHeader("X-Token");
		return favoritesService.getFavoriteMessagesPosts(login, service, xToken);
	}

	@GetMapping(value = FavoritesApiConstants.GET_FA_LOSTFOUND_POSTS)
	ResponseLostFoundDto[] getActivityLostFoundPosts(@PathVariable("login") String login,
			@PathVariable("service") String service, HttpServletRequest request) {
		String xToken = request.getHeader("X-Token");
		return favoritesService.getActivityLostFoundPosts(login, service, xToken);
	}
	// HERE WILL BE HOTELS GETTER

}
