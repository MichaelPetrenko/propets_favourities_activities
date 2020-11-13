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
import org.springframework.web.client.RestTemplate;
import telran.favorites.api.Activ;
import telran.favorites.api.ResponceMessagingDto;
import telran.favorites.api.ResponseLostFoundDto;
import telran.favorites.api.ResponsePostDto;
import telran.favorites.api.codes.NoContentException;
import telran.favorites.service.interfaces.FavoritesManagement;

@Service
public class FavoritesManagementImpl implements FavoritesManagement {
	
	@Autowired
	RestTemplate restTemplate;

	@Override
	public ResponceMessagingDto[] getFavoriteMessagesPosts(String email, String service) {

		String xToken = "eyJhbGciOiJIUzI1NiJ9.eyJsb2dpbiI6ImZvcmxpZHplbkBnbWFpbC5jb20iLCJwYXNzd29y"
				+ "ZCI6IiQyYSQxMCRid29IRWxrL2lieEJKb3hwRGt5UmMuYXJVQ2xjeGw1VnRYdkZiSzJ1UTdSUWt6Skt"
				+ "SQzdYMiIsInRpbWVzdGFtcCI6MTYwNzg3MjY4MzE0Miwicm9sZSI6WyJVU0VSIl19.XGkg_1-13Cfb7"
				+ "rHwSSQuH80u5qdGM3pu_yD5eS8D41I";

		String type = "message";

		HashSet<String> hashID = requestIDfromAccounting(email, xToken, service, type);
		
		ResponceMessagingDto[] result = requestPostsByIDFromMessaging(hashID);
		
		return result;
	}
	
	@Override
	public ResponseLostFoundDto[] getActivityLostFoundPosts(String email, String service) {
		
		String xToken = "eyJhbGciOiJIUzI1NiJ9.eyJsb2dpbiI6ImZvcmxpZHplbkBnbWFpbC5jb20iLCJwYXNzd29y"
				+ "ZCI6IiQyYSQxMCRid29IRWxrL2lieEJKb3hwRGt5UmMuYXJVQ2xjeGw1VnRYdkZiSzJ1UTdSUWt6Skt"
				+ "SQzdYMiIsInRpbWVzdGFtcCI6MTYwNzg3MjY4MzE0Miwicm9sZSI6WyJVU0VSIl19.XGkg_1-13Cfb7"
				+ "rHwSSQuH80u5qdGM3pu_yD5eS8D41I";

		String type = "lostfound";
		
		HashSet<String> hashID = requestIDfromAccounting(email, xToken, service, type);
		
		ResponseLostFoundDto[] result = requestPostsByIDfromLostfound(hashID);
		
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
		String endPoint = "https://propets-me.herokuapp.com/account/en/v1/"+email+"?dataType="+servicePath;
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
		String endPointGetUserData = "https://propets-mes.herokuapp.com/message/en/v1/userdata";
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
		String endPointGetUserData = "https://propets-lfs.herokuapp.com/lostfound/en/v1/userdata";
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
