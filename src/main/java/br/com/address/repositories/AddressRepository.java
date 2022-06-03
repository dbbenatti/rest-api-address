package br.com.address.repositories;



import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.address.domain.Address;

@Repository
public interface AddressRepository extends JpaRepository<Address,Integer> {
	
	@Query(value=
			"Select * from address where address.zipcode = :zipcode", nativeQuery = true
			)
	List<Address> findAddressByZipcode(@Param("zipcode") String zipcode);
	
}


