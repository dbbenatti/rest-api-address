package br.com.address.services;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import br.com.address.domain.Address;
import br.com.address.repositories.AddressRepository;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import javassist.tools.rmi.ObjectNotFoundException;

public class AddressServiceTest {

	@SuppressWarnings("deprecation")
	@Rule
	public ExpectedException exceptionRule = ExpectedException.none();
	@Mock
    AddressRepository addressRepository;
	@InjectMocks
	AddressService addressService;
	@Mock
	GetAddressByAPIService getAddressByAPIService;

	@Before
	public void setUp() {

		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void givenHasAddressOnRepository_WhenSearchAddressById_ThenAddressShouldReturn()
			throws ObjectNotFoundException {

		// Arrange
		Optional<Address> address = Optional.of(new Address(1, "Rua Boaventura do Amaral", 760, "AP 3", "Bosque",
				"Campinas", "SP", "Brasil", "13015191", "-22.9067672", "-47.0539181"));
		Integer id = 1;

		when(addressRepository.findById(id)).thenReturn(address);

		// ACT
		Address addressFound = addressService.searchAddressId(id);

		// ASSERT
		assertThat(address.get()).isEqualTo(addressFound);

	}

	@Test
	public void givenHasntAddressOnRepository_WhenSearchAddressById_ThenAddressShouldntReturn()
			throws ObjectNotFoundException {

		// Arrange
		Integer id = 1;

		when(addressRepository.findById(id)).thenReturn(null);
		
		exceptionRule.expect(ObjectNotFoundException.class);
		exceptionRule.expectMessage("Address was not found with this ID: " + id + ", Type: " + Address.class.getName());

		// ACT
		addressService.searchAddressId(id);
	}

	@Test
	public void givenHasListAddressWithoutLatNLng_WhenTrySaveOnRepository_ThenAllAddressShouldHasLatNLngNSaved() {

		// Arrange
		Address address = new Address(1, "Rua Boaventura do Amaral", 760, "AP 3", "Bosque", "Campinas", "SP", "Brasil",
				"13015191", null, null);
		Address expectedAddress = new Address(1, "Rua Boaventura do Amaral", 760, "AP 3", "Bosque", "Campinas", "SP",
				"Brasil", "13015191", "-22.9067672", "-47.0539181");
		ArrayList<Address> listAdress = new ArrayList<Address>();
		listAdress.add(address);

		ArrayList<Address> expectedListAddress = new ArrayList<Address>();
		expectedListAddress.add(expectedAddress);

		when(getAddressByAPIService.getLatLng(address)).thenReturn(expectedAddress);

		when(addressRepository.saveAll(expectedListAddress)).thenReturn(expectedListAddress);

		// ACT
		List<Address> returnedAddress = addressService.insertAddress(listAdress);
		// ASSERT

		assertEquals(expectedListAddress, returnedAddress);
	}

	@Test
	public void givenHasAddressWithoutLatNLng_WhenTryUpdateNSaveOnRepository_ThenAddressShouldHasLatNLngSaved()
			throws ObjectNotFoundException {

		// arrange
		Address address = new Address(1, "Rua Boaventura do Amaral", 760, "AP 3", "Bosque", "Campinas", "SP", "Brasil",
				"13015191", null, null);
		Address expectedAddress = new Address(1, "Rua Boaventura do Amaral", 760, "AP 3", "Bosque", "Campinas", "SP",
				"Brasil", "13015191", "-22.9067672", "-47.0539181");
		Optional<Address> optAddress = Optional.of(address);
		Integer id = 1;

		when(addressRepository.findById(id)).thenReturn(optAddress);

		when(getAddressByAPIService.getLatLng(address)).thenReturn(expectedAddress);

		when(addressRepository.save(expectedAddress)).thenReturn(expectedAddress);

		// act
		Address returnedAddress = addressService.updateAddress(address);

		// assert
		assertEquals(expectedAddress, returnedAddress);
	}

	@Test
	public void givenHasAddressId_WhenTryDeleteOnRepository_ThenAddressShouldDeleted() throws ObjectNotFoundException {

		// arrange
		Address address = new Address(1, "Rua Boaventura do Amaral", 760, "AP 3", "Bosque", "Campinas", "SP", "Brasil",
				"13015191", null, null);

		Optional<Address> optAddress = Optional.of(address);
		Integer id = 1;

		when(addressRepository.findById(id)).thenReturn(optAddress);

		doNothing().when(addressRepository).deleteById(address.getId());

		// act
		addressService.deleteAddress(address.getId());

		// assert
		verify(addressRepository, times(1)).deleteById(address.getId());

	}
	
	@Test
	public void givenHasAddress_WhenISearchByZipcode_ThenAListOfAddressShouldReturn() {
		Address address = new Address(1, "Rua Boaventura do Amaral", 760, "AP 3", "Bosque", "Campinas", "SP", "Brasil",
				"13015191", null, null);
		Address address2 = new Address(1, "Rua Boaventura do Amaral", 760, "AP 3", "Bosque", "Campinas", "SP",
				"Brasil", "13015191", "-22.9067672", "-47.0539181");
		Address address3 = new Address(1, "Rua Boaventura do Amaral", 760, "AP 3", "Bosque", "Campinas", "SP", "Brasil",
				"13015191", null, null);
		Address address4 = new Address(1, "Rua Boaventura do Amaral", 760, "AP 3", "Bosque", "Campinas", "SP",
				"Brasil", "13015191", "-22.9067672", "-47.0539181");
		Address address5 = new Address(1, "Rua Boaventura do Amaral", 760, "AP 3", "Bosque", "Campinas", "SP",
				"Brasil", "13015191", "-22.9067672", "-47.0539181");
		ArrayList<Address> listAddress = new ArrayList<Address>();
		
		listAddress.add(address);
		listAddress.add(address2);
		listAddress.add(address3);
		listAddress.add(address4);
		listAddress.add(address5);
		
		
		when(addressRepository.findAddressByZipcode(address.getZipcode())).thenReturn(listAddress);
		
		List<Address> returnedListAddress = addressService.getAddressByZipcode(address.getZipcode());
		
		
		assertEquals(listAddress,returnedListAddress);
		
	}

}
