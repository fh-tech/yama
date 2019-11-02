package org.fhtech.yama.controllers;

import org.fhtech.yama.domain.Actor;
import org.fhtech.yama.domain.NewActor;
import org.fhtech.yama.domain.UpdateActor;
import org.fhtech.yama.services.ActorService;
import org.hibernate.sql.Update;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

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
