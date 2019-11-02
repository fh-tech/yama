package org.fhtech.yama.controllers;
import org.fhtech.yama.domain.NewStudio;
import org.fhtech.yama.domain.Studio;
import org.fhtech.yama.domain.UpdateStudio;
import org.fhtech.yama.services.StudioService;
import org.hibernate.sql.Update;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("studio")
public class StudioController {

    private StudioService studioService;

    public StudioController(@Autowired StudioService studioService) {
        this.studioService = studioService;
    }

    @PostMapping
    public Studio createStudio(@Valid @RequestBody NewStudio newStudio) {
        return this.studioService.save(newStudio);
    }

    @GetMapping
    public Iterable<Studio> getStudios() {
        return this.studioService.findAll();
    }

    @GetMapping("/{id}")
    public Studio getStudio(@PathVariable long id) {
        return this.studioService.findById(id);
    }

    @PutMapping("/{id}")
    public Studio updateStudio(@PathVariable long id, @Valid @RequestBody UpdateStudio updateStudio) {
        return this.studioService.update(id, updateStudio);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteStudio(@PathVariable long id) {
        this.studioService.deleteById(id);
    }


}
