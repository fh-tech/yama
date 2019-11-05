package org.fhtech.yamaServer.services;

import org.fhtech.yamaServer.domain.NewStudio;
import org.fhtech.yamaServer.domain.Studio;
import org.fhtech.yamaServer.domain.UpdateStudio;
import org.fhtech.yamaServer.repositories.StudioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

@Component
public class StudioService {

    private StudioRepository studioRepository;

    public StudioService(@Autowired StudioRepository studioRepository) {
        this.studioRepository = studioRepository;
    }

    public Iterable<Studio> findAll() {
        return studioRepository.findAll();
    }

    public Studio save(NewStudio newStudio) {
        Studio studio = new Studio(
                null,
                newStudio.getName(),
                newStudio.getPostCode(),
                newStudio.getCountryCode(),
                null
        );
        return studioRepository.save(studio);
    }

    public Studio findById(long id) {
        return studioRepository
                .findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Actor not found"));
    }

    public void deleteById(long id) {
        if (!studioRepository.existsById(id)) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Studio not found");
        studioRepository.deleteById(id);
    }

    public Studio update(long id, UpdateStudio updateStudio) {
        var studio = findById(id);
        studio.setName(updateStudio.getName());
        studio.setPostCode(updateStudio.getPostCode());
        studio.setCountryCode(updateStudio.getCountryCode());
        return studioRepository.save(studio);
    }

}
