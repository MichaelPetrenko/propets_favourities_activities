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
import telran.favorites.service.interfaces.FavoritesManagement;

@RestController
public class FavoritesController {

	@Autowired
	FavoritesManagement messageService;
	
//	@PostMapping(value = FavoritesApiConstants.CREATE_POST)
//	ResponceMessagingDto createPost(@RequestBody RequestCreatePostDto dto, 
//			@PathVariable("login") String login, HttpServletRequest request) {
//		return messageService.createPost(dto, login);
//	};
//	
//	@PutMapping(value = FavoritesApiConstants.UPDATE_POST)
//	ResponceMessagingDto update(@RequestBody RequestCreatePostDto dto, @PathVariable("id") String id) {
//		return messageService.update(dto, id);
//	}
//	
//	@DeleteMapping(value = FavoritesApiConstants.DELETE_POST)
//	ResponceMessagingDto delete(@PathVariable("id") String id) { 
//		return messageService.delete(id);
//	}
//	
//	@GetMapping(value = FavoritesApiConstants.GET_POST_BY_ID)
//	ResponceMessagingDto getPostById(@PathVariable("idPost") String idPost) {	
//		return messageService.getPostById(idPost);
//	}
//	
//	@GetMapping(value = FavoritesApiConstants.VIEW_POST_PAGEABLE)
//	ResponcePageableDto viewPostPageable(@RequestParam("itemsOnPage") int itemsOnPage, @RequestParam("currentPage") int currentPage) {
//		return messageService.viewPostPageable(itemsOnPage, currentPage);
//	}
//	
//	@PostMapping(value = FavoritesApiConstants.GET_USER_DATA)
//	Object[] getUserData(@RequestBody String[] listID) {
//		return messageService.getUserData(listID);
//	}
	
}
