package org.fhtech.yamaServer.repositories;

import org.fhtech.yamaServer.domain.Studio;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface StudioRepository extends CrudRepository<Studio, Long> {

    List<Studio> findAllByNameAndCountryCodeAndPostCode(String name, String countryCode, String postCode);
}
