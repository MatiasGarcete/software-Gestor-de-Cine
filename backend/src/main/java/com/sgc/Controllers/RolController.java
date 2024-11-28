package com.sgc.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sgc.Model.entity.Rol;
import com.sgc.Model.service.IRol;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;



@RestController
@RequestMapping("/api/v1")
public class RolController {
    @Autowired
    private IRol rolService;

    @PostMapping("rol")
    public Rol create(@RequestBody Rol rol){
        return rolService.save(rol);
    }

    @GetMapping("rolAll")
    public Iterable<Rol> findAll() {
        return rolService.findAll();
    }
    

    @DeleteMapping("rol/{id}")
    public void delete(@PathVariable Integer id){
        Rol rolDelete = rolService.findById(id);
        rolService.delete(rolDelete);
    }
}
