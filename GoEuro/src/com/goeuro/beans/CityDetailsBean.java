package com.goeuro.beans;

public class CityDetailsBean extends BaseDetailsBean{
	private String _id;
	private String name;
	private String type;
	private String latitude;
	private String longitude;
	
	public String get_id() {
		return _id;
	}
	public void set_id(String _id) {
		this._id = _id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getLatitude() {
		return latitude;
	}
	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}
	public String getLongitude() {
		return longitude;
	}
	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}
	@Override
	public String toString() {
		return "CityDetailsBean [_id=" + _id + ", name=" + name + ", type=" + type + ", latitude=" + latitude
				+ ", longitude=" + longitude + "]";
	}
	
	
}
