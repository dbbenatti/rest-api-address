package br.com.address.resources;





import java.util.List;

import br.com.address.services.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.address.domain.Address;
import javassist.tools.rmi.ObjectNotFoundException;

@RestController
@RequestMapping(value="/address")
public class AddressResources {
	
	@Autowired
	private AddressService service;

	//Requisição GET que busca um endereço  ou mais d eum através do zipcode
	@RequestMapping(value="/zipcode/{zipCode}", method=RequestMethod.GET)
	public ResponseEntity<?> findAddress(@PathVariable String zipCode) {
		List<Address> address = service.getAddressByZipcode(zipCode);
		
		return ResponseEntity.ok().body(address);
	}	
	//Requisição GET que busca um endereço através de uma ID
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<?> find(@PathVariable Integer id) throws ObjectNotFoundException {
		Address ad = service.searchAddressId(id);
		
		return ResponseEntity.ok().body(ad);
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<Void> insertAddress(@RequestBody List<Address> address){
		address = service.insertAddress(address);
		
		return ResponseEntity.status(201).build();
	}	
	
	
	@RequestMapping(value="/{id}",method=RequestMethod.PUT)
	public ResponseEntity<Void> updateAddress(@RequestBody Address address, @PathVariable Integer id) throws ObjectNotFoundException{
		address.setId(id);
		address = service.updateAddress(address);		
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(value="/{id}",method=RequestMethod.DELETE)
	public ResponseEntity<Void> deleteAddress(@PathVariable Integer id) throws ObjectNotFoundException {		
		service.deleteAddress(id);		
		return ResponseEntity.noContent().build();
	}
	
	
	
	
	
}
