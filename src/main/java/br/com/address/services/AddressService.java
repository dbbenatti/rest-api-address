package br.com.address.services;



import java.util.List;
import java.util.Optional;

import br.com.address.repositories.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.address.domain.Address;
import javassist.tools.rmi.ObjectNotFoundException;

@Service
public class AddressService {
	
	@Autowired
    AddressRepository repo;
	
	@Autowired
	GetAddressByAPIService getAddressByAPIService ;
	
	public List<Address> getAddressByZipcode(String zipcode){
		List<Address> address = repo.findAddressByZipcode(zipcode);
		
		return address;
		
	}
	
	public Address searchAddressId(Integer id) throws ObjectNotFoundException {
		Optional<Address> address = repo.findById(id);
		if(address == null) {
			throw new ObjectNotFoundException("Address was not found with this ID: " + id +", Type: " +Address.class.getName());
		}
		return address.get();
	}	

	
	public List<Address> insertAddress(List<Address> address) {	
		
		for(Address a : address) {
			a = getLatNLng(a);
		}
		
		return repo.saveAll(address);
	}

	public Address updateAddress(Address address) throws ObjectNotFoundException {	
		searchAddressId(address.getId());
		address = getLatNLng(address);	
		return repo.save(address);
	}

	public void deleteAddress(Integer id) throws ObjectNotFoundException {
		searchAddressId(id);
		repo.deleteById(id);
		// TODO Auto-generated method stub
		
	}
	
	private Address getLatNLng(Address address) {
		if(address.getLatitude()==null || address.getLongitude()==null ) {
			 address = getAddressByAPIService.getLatLng(address);
		}
		return address;
	}
}
