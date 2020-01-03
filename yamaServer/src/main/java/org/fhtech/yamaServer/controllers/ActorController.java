package org.fhtech.yamaServer.controllers;

import org.fhtech.yamaServer.domain.Actor;
import org.fhtech.yamaServer.domain.NewActor;
import org.fhtech.yamaServer.domain.UpdateActor;
import org.fhtech.yamaServer.services.ActorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.support.SecurityContextProvider;
import org.springframework.context.annotation.Role;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import javax.validation.Valid;

@RestController
@RequestMapping("actor")
public class ActorController {

    private ActorService actorService;

    public ActorController(@Autowired ActorService actorService) {
        this.actorService = actorService;
    }

    @PostMapping
    public Actor createActor(@Valid @RequestBody NewActor newActor) {
        return this.actorService.save(newActor);
    }

    @GetMapping
    public Iterable<Actor> getActors() {
        var user = SecurityContextHolder.getContext().getAuthentication();
        return this.actorService.findAll();
    }

    @GetMapping("/{id}")
    public Actor getActor(@PathVariable long id) {
        return this.actorService.findById(id);
    }

    @PutMapping("/{id}")
    public Actor updateActor(@PathVariable long id, @Valid @RequestBody UpdateActor updateActor) {
        return this.actorService.update(id, updateActor);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteActor(@PathVariable long id) {
        this.actorService.deleteById(id);
    }

}
