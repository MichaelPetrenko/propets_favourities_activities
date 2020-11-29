package telran.favorites.service.impl;

import java.net.URI;
import java.util.Collections;
import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException.BadRequest;
import org.springframework.web.client.HttpClientErrorException.Forbidden;
import org.springframework.web.client.HttpClientErrorException.Unauthorized;
import org.springframework.web.client.RestTemplate;
import telran.favorites.api.Activ;
import telran.favorites.api.ResponceMessagingDto;
import telran.favorites.api.ResponseLostFoundDto;
import telran.favorites.api.ResponsePostDto;
import telran.favorites.api.codes.BadRequestException;
import telran.favorites.api.codes.BadTokenException;
import telran.favorites.api.codes.ForbiddenException;
import telran.favorites.api.codes.NoContentException;
import telran.favorites.api.codes.NotExistsException;
import telran.favorites.service.interfaces.FavoritesManagement;

@Service
public class FavoritesManagementImpl implements FavoritesManagement {
	
	@Autowired
	RestTemplate restTemplate;

	@Override
	public ResponceMessagingDto[] getFavoriteMessagesPosts(String email, String service, String xToken) {

		String type = "message";

		ResponceMessagingDto[] result = null;
		try {
			HashSet<String> hashID = requestIDfromAccounting(email, xToken, service, type);
			result = requestPostsByIDFromMessaging(hashID);
		} catch (Exception e) {
			if (e instanceof Forbidden) {
				throw new ForbiddenException();
			}
			else if (e instanceof Unauthorized) {
				throw new BadTokenException();
			}
			else if (e instanceof BadRequest) {	
				throw new BadRequestException();
			}
			else throw new NotExistsException();
		}
		
		return result;
	}
	
	@Override
	public ResponseLostFoundDto[] getActivityLostFoundPosts(String email, String service, String xToken) {

		String type = "lostfound";
		
		ResponseLostFoundDto[] result;
		try {
			HashSet<String> hashID = requestIDfromAccounting(email, xToken, service, type);
			result = requestPostsByIDfromLostfound(hashID);
		} catch (Exception e) {
			if (e instanceof Forbidden) {
				throw new ForbiddenException();
			}
			else if (e instanceof Unauthorized) {
				throw new BadTokenException();
			}
			else if (e instanceof BadRequest) {	
				throw new BadRequestException();
			}
			else throw new NotExistsException();
		}
		
		return result;
	}

	//	TYPE may be message or lostfound or hotels
	private HashSet<String> requestIDfromAccounting(String email, String xToken, String service, String type) {
		
		boolean serviceType = false;
		if(service.equalsIgnoreCase("activities")) {
			serviceType=true;
		}
		if(service.equalsIgnoreCase("favorites")) {
			serviceType=false;
		}
		
		String servicePath = Boolean.toString(serviceType);
		String endPoint = "https://propets-me.herokuapp.com/en/v1/"+email+"?dataType="+servicePath;
		URI uri;
		try {
			uri = new URI(endPoint);
		} catch (Exception e) {
			throw new NoContentException();
		}
		
		HttpHeaders headers = new HttpHeaders();
		
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
		headers.set("X-Token", xToken);
		HttpEntity<String> request = new HttpEntity<>(headers);
		ResponseEntity<Activ> responceFromAccounting = restTemplate.exchange(uri, HttpMethod.GET, request, Activ.class);
		
		HashSet<String> resp = null;
		if(type.equalsIgnoreCase("message")) {
			resp = responceFromAccounting.getBody().message;
		}
		if(type.equalsIgnoreCase("lostfound")) {
			resp = responceFromAccounting.getBody().lostFound;
		}
		if(type.equalsIgnoreCase("hotels")) {
			resp = responceFromAccounting.getBody().hotels;
		}
		
		return resp;
		
	}
	
	private ResponceMessagingDto[] requestPostsByIDFromMessaging(HashSet<String> hashID) {
		String endPointGetUserData = "https://propets-mes.herokuapp.com/en/v1/userdata";
		URI uri;
		try {
			uri = new URI(endPointGetUserData);
		} catch (Exception e) {
			throw new NoContentException();
		}

		HttpEntity<HashSet<String>> requestToData = new HttpEntity<HashSet<String>>(hashID);
		ResponseEntity<ResponceMessagingDto[]> responceFromGetUserData = restTemplate.exchange(uri, 
				HttpMethod.POST, requestToData, ResponceMessagingDto[].class);

		ResponceMessagingDto[] result = responceFromGetUserData.getBody();

		return result;
	}
	
	private ResponseLostFoundDto[] requestPostsByIDfromLostfound(HashSet<String> hashID) {
		String endPointGetUserData = "https://propets-lfs.herokuapp.com/en/v1/userdata";
		URI uri;
		try {
			uri = new URI(endPointGetUserData);
		} catch (Exception e) {
			throw new NoContentException();
		}

		HttpEntity<HashSet<String>> requestToData = new HttpEntity<HashSet<String>>(hashID);
		ResponseEntity<ResponsePostDto[]> responceFromGetUserData = restTemplate.exchange(uri, 
				HttpMethod.POST, requestToData, ResponsePostDto[].class);
		
		ResponsePostDto[] result = responceFromGetUserData.getBody();
		ResponseLostFoundDto[] res = new ResponseLostFoundDto[result.length];
		for (int i = 0; i < result.length; i++) {
			res[i] = new ResponseLostFoundDto(result[i]);
		}
		
		return res;
	}
	
}
