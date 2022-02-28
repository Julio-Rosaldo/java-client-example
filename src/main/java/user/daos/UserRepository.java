package user.daos;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;

import user.entities.UserDTO;
import user.entities.UserDataDTO;
import user.entities.UsersDataDTO;

public class UserRepository {

	private static final String URI = "https://jsonplaceholder.typicode.com/users";
	private static final ObjectMapper objectMapper = new ObjectMapper();
	
	private String[] generateHeaders(Map<String, String> headers) {
		if (headers == null) {
			headers = new HashMap<String, String>();
		}

		int headersSize = headers.size() * 2;
		String[] arrayHeaders = new String[headersSize];

		int i = 0;
		for (Map.Entry<String, String> header : headers.entrySet()) {
			arrayHeaders[i] = header.getKey();
			arrayHeaders[i + 1] = header.getValue();
			i = i + 2;
		}
		
		return arrayHeaders;
	}

	private HttpRequest buildGetRequest(String uri, Map<String, String> headers) {
		HttpRequest request = null;

		String[] arrayHeaders = generateHeaders(headers);
		try {
			request = HttpRequest.newBuilder().uri(new URI(uri)).version(HttpClient.Version.HTTP_2)
					.headers(arrayHeaders).timeout(Duration.ofMillis(2000)).GET().build();
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}

		return request;
	}
	
	private HttpRequest buildPostRequest(String uri, String payload, Map<String, String> headers) {
		HttpRequest request = null;

		String[] arrayHeaders = generateHeaders(headers);
		try {
			request = HttpRequest.newBuilder().uri(new URI(uri)).version(HttpClient.Version.HTTP_2)
					.headers(arrayHeaders).timeout(Duration.ofMillis(2000))
					.POST(HttpRequest.BodyPublishers.ofString(payload)).build();
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}

		return request;
	}

	public List<UserDTO> listUsers() {
		List<UserDTO> users = null;

		Map<String, String> headers = new HashMap<String, String>();
		headers.put("Content-Type", "application/json;charset=UTF-8");

		try {
			HttpRequest request = buildGetRequest(URI, headers);
			HttpClient client = HttpClient.newHttpClient();
			HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
			int statusCode = response.statusCode();
			System.out.println(statusCode);

			if (statusCode == 200) {
				String responseBody = "{\"data\":".concat(response.body()).concat("}");
				UsersDataDTO usersData = objectMapper.readValue(responseBody, UsersDataDTO.class);
				users = usersData.getData();
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return users;
	}

	public UserDTO getUser(String id) {
		UserDTO user = null;

		Map<String, String> headers = new HashMap<String, String>();
		headers.put("Content-Type", "application/json;charset=UTF-8");

		try {
			HttpRequest request = buildGetRequest(URI.concat("/".concat(id)), headers);
			HttpClient client = HttpClient.newHttpClient();
			HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
			int statusCode = response.statusCode();
			System.out.println(statusCode);

			if (statusCode == 200) {
				String responseBody = "{\"data\":".concat(response.body()).concat("}");
				UserDataDTO userData = objectMapper.readValue(responseBody, UserDataDTO.class);
				user = userData.getData();
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return user;
	}
	
	public UserDTO createUser(UserDTO payload) {
		UserDTO user = null;

		Map<String, String> headers = new HashMap<String, String>();
		headers.put("Content-Type", "application/json;charset=UTF-8");

		try {
			String requestPayload = objectMapper.writeValueAsString(payload);
			System.out.println(requestPayload);
			
			HttpRequest request = buildPostRequest(URI, requestPayload, headers);
			HttpClient client = HttpClient.newHttpClient();
			HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
			int statusCode = response.statusCode();
			System.out.println(statusCode);

			if (statusCode == 201) {
				String responseBody = "{\"data\":".concat(response.body()).concat("}");
				UserDataDTO userData = objectMapper.readValue(responseBody, UserDataDTO.class);
				user = userData.getData();
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return user;
	}

}
