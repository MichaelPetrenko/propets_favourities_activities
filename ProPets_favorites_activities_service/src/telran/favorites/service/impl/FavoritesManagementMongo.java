package telran.favorites.service.impl;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import telran.favorites.api.ResponceMessagingDto;
import telran.favorites.api.ResponseLostFoundDto;
import telran.favorites.api.codes.NoContentException;
import telran.favorites.api.codes.NotExistsException;
import telran.favorites.service.interfaces.FavoritesManagement;

@Service
public class FavoritesManagementMongo implements FavoritesManagement {

	@Override
	public ResponceMessagingDto[] getFavoriteMessagesPosts(String email) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseLostFoundDto[] getActivityLostFoundPosts(String email) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
