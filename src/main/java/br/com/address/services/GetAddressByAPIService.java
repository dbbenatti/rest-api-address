package br.com.address.services;


import br.com.address.domain.Address;

public interface GetAddressByAPIService {
	

	Address getLatLng(Address address);
}
