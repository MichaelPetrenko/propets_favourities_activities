package telran.favorites.api;

import java.util.Arrays;

import telran.favorites.api.Address;

public class ResponsePostDto {
	
	public String id;
	public boolean typePost;
	public String userLogin;
	public String userName;
	public String avatar;
	public String datePost;	
	public String type;
	public String sex;
	public String breed;
	public String[] tags;
	public String[] photos;
	public Address address;
	public double[] location;
	
	public ResponsePostDto(String id, boolean typePost, String userLogin, String userName, String avatar,
			String datePost, String type, String sex, String breed, String[] tags, String[] photos,
			Address address, double[] location) {
		super();
		this.id = id;
		this.typePost = typePost;
		this.userLogin = userLogin;
		this.userName = userName;
		this.avatar = avatar;
		this.datePost = datePost;
		this.type = type;
		this.sex = sex;
		this.breed = breed;
		this.tags = tags;
		this.photos = photos;
		this.address = address;
		this.location = location;
	}

	public ResponsePostDto() {
		super();
	}

	@Override
	public String toString() {
		return "ResponsePostDto [id=" + id + ", typePost=" + typePost + ", userLogin=" + userLogin + ", userName="
				+ userName + ", avatar=" + avatar + ", datePost=" + datePost + ", type=" + type + ", sex=" + sex
				+ ", breed=" + breed + ", tags=" + Arrays.toString(tags) + ", photos=" + Arrays.toString(photos)
				+ ", address=" + address + ", location=" + Arrays.toString(location) + "]";
	}

}
