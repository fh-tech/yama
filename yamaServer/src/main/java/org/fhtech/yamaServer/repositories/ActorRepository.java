package org.fhtech.yamaServer.repositories;

import org.fhtech.yamaServer.domain.Actor;
import org.fhtech.yamaServer.domain.NewActor;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDate;
import java.util.Optional;

public interface ActorRepository extends CrudRepository<Actor, Long> {

    Iterable<Actor> findAllByFirstNameAndLastNameAndBirthDay(String firstName, String lastName, LocalDate birthDay);
}
