package br.com.address.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.lang.Nullable;

@Entity
public class Address implements Serializable {

	/**
	 * INSERT INTO ADDRESS ( CITY , COMPLEMENT , COUNTRY , LATITUDE , LONGITUDE , NEIGHBOURHOOD , NUMBER , STATE , STREETNAME , ZIPCODE ) VALUES ('Campinas', 'AP 3','Brasil',-3.7218021,-38.5168132 ,'Bosque',760, 'SP', 'Rua Boaventura do Amaral' ,'13015191')


	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;	
	
	private String streetname;	
	private int number;	
	@Nullable
	private String complement;	
	private String neighbourhood;	
	private String city;	
	private String state;	
	private String country;	
	private String zipcode;	
	@Nullable
	private String latitude;
	@Nullable
	private String longitude;
	
	public Address() {
		
	}

	public Address(Integer id, String streetName, int number, String complement, String neighbourhood, String city,
			String state, String country, String zipcode, String latitude, String longitude) {
		super();
		this.id = id;
		this.streetname = streetName;
		this.number = number;
		this.complement = complement;
		this.neighbourhood = neighbourhood;
		this.city = city;
		this.state = state;
		this.country = country;
		this.zipcode = zipcode;
		this.latitude = latitude;
		this.longitude = longitude;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getStreetName() {
		return streetname;
	}

	public void setStreetName(String streetName) {
		this.streetname = streetName;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public String getComplement() {
		return complement;
	}

	public void setComplement(String complement) {
		this.complement = complement;
	}

	public String getNeighbourhood() {
		return neighbourhood;
	}

	public void setNeighbourhood(String neighbourhood) {
		this.neighbourhood = neighbourhood;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getZipcode() {
		return zipcode;
	}

	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
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
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Address other = (Address) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	
	
	
}
