package telran.favorites.api;

import telran.favorites.api.Address;

public class ResponseLostFoundDto {
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
	public LocationDto location;

	public ResponseLostFoundDto() {
	}

	public ResponseLostFoundDto(String id, boolean typePost, String userLogin, String userName, String avatar,
			String datePost, String type, String sex, String breed, String[] tags, String[] photos, Address address,
			LocationDto location) {
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

	public ResponseLostFoundDto(ResponsePostDto dto) {
		this.id = dto.id;
		this.typePost = dto.typePost;
		this.userLogin = dto.userLogin;
		this.userName = dto.userName;
		this.avatar = dto.avatar;
		this.datePost = dto.datePost;
		this.type = dto.type;
		this.sex = dto.sex;
		this.breed = dto.breed;
		this.tags = dto.tags;
		this.photos = dto.photos;
		this.address = dto.address;
		this.location = new LocationDto(dto.location[0], dto.location[1]);
	}

}
