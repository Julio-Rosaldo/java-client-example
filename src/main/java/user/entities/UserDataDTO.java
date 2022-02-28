package user.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserDataDTO {

	private UserDTO data;

	public UserDTO getData() {
		return data;
	}

	public void setData(UserDTO data) {
		this.data = data;
	}

	@Override
	public String toString() {
		return "UserDataDTO [data=" + data + "]";
	}

}
