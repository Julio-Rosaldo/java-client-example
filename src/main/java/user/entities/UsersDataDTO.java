package user.entities;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UsersDataDTO {

	private List<UserDTO> data;

	public List<UserDTO> getData() {
		return data;
	}

	public void setData(List<UserDTO> data) {
		this.data = data;
	}

	@Override
	public String toString() {
		return "UsersDataDTO [data=" + data + "]";
	}

}
