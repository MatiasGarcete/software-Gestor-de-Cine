package com.sgc.Controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sgc.Model.entity.Pelicula;
import com.sgc.Model.service.IPelicula;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.GetMapping;




@RestController
@RequestMapping("/api/v1")
public class PeliculaController {
    @Autowired
    private IPelicula peliculaService;

    @PostMapping("pelicula")
    public Pelicula create(@RequestBody Pelicula pelicula) {    
        return peliculaService.save(pelicula);
    }
    @PutMapping("pelicula")
    public Pelicula update(@RequestBody Pelicula pelicula) {
        return peliculaService.save(pelicula);
    }

    @DeleteMapping("pelicula/{id}")
    public void delete(@PathVariable Integer id){
        Pelicula peliculaDelete = peliculaService.findById(id);
        peliculaService.delete(peliculaDelete);
    }

    @GetMapping("pelicula/{id}")
    public Pelicula showById(@PathVariable Integer id) {
        return peliculaService.findById(id);
    }
    
    @GetMapping("peliculas")
    public Iterable<Pelicula> findAll() {
        return peliculaService.findAll();
    }
    
    
}
