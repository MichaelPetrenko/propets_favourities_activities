package telran.favorites.service.impl;

import java.net.URI;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.function.ServerRequest.Headers;

import telran.favorites.api.Activ;
import telran.favorites.api.ResponceMessagingDto;
import telran.favorites.api.ResponseLostFoundDto;
import telran.favorites.api.codes.BadTokenException;
import telran.favorites.api.codes.NoContentException;
import telran.favorites.service.interfaces.FavoritesManagement;

@Service
public class FavoritesManagementImpl implements FavoritesManagement {
	
	@Autowired
	RestTemplate restTemplate;

	@Override
	public ResponceMessagingDto[] getFavoriteMessagesPosts(String email, String service) {
		System.out.println("============== email "+email);
		System.out.println("============== service "+service);
		
		boolean serviceType = false;
		if(service.equalsIgnoreCase("activities")) {
			serviceType=true;
		}
		if(service.equalsIgnoreCase("favorites")) {
			serviceType=false;
		}
		String servicePath = Boolean.toString(serviceType);
		
		String endPoint = "https://propets-me.herokuapp.com//account/en/v1/"+email+"?dataType="+servicePath;
		System.out.println("============= endpoint "+endPoint);
		RestTemplate restTemplate = new RestTemplate();

		URI uri;
		try {
			uri = new URI(endPoint);
		} catch (Exception e) {
			throw new NoContentException();
		}
		//headers
		HttpHeaders headers = new HttpHeaders();
		headers.set("X-Token", "eyJhbGciOiJIUzI1NiJ9.eyJsb2dpbiI6InJlaXptcDRAZ21haWwuY29tIiwicGFzc3dvcmQiOiIkMmEkMTAkejFvMXdBVnpzdXlvWlpPM0VXU1BrZVkxaEpEVk91N2VHT21FRHV1T1RNelhCNU9hZUpvYU8iLCJ0aW1lc3RhbXAiOjE2MDc4MTEzMzIwMTQsInJvbGUiOlsiVVNFUiIsIkFETUlOIl19.MGe2nuxhYV7NR7LRUdtMervVYFkYgWtx2n55xCcNocE");
		
		ResponseEntity<Activ> responceFromAccounting;
		System.out.println("========= i am try");
		try {
			RequestEntity<Void> requestToAccounting = RequestEntity.post(uri).build();
			//=================
			//new HttpEntity<>(headers);
			HttpEntity<String> entity = new HttpEntity<>(headers);
			Activ act = restTemplate.getForObject(uri, Activ.class);
			System.out.println(Arrays.toString(act.message.toArray()));
			
			
//			responceFromAccounting = restTemplate.exchange
//					(uri, HttpMethod.GET, requestToAccounting, Activ.class);
			System.out.println("======== after try");
		} catch (Exception e) {
			System.out.println("============== Benedict KimberCATCH");
			throw new BadTokenException();
		}
//
//		Object[] resFromAcc = responceFromAccounting.getBody().message.toArray();
//		System.out.println(Arrays.toString(resFromAcc));
		
		
		
		
		return null;
	}

	@Override
	public ResponseLostFoundDto[] getActivityLostFoundPosts(String email, String service) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
