package com.fundamentos.springboot.fundamentos.controller;

import com.fundamentos.springboot.fundamentos.caseuse.CreateUsers;
import com.fundamentos.springboot.fundamentos.caseuse.DeleteUsers;
import com.fundamentos.springboot.fundamentos.caseuse.GetUsers;
import com.fundamentos.springboot.fundamentos.caseuse.UpdateUsers;
import com.fundamentos.springboot.fundamentos.entity.Users;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/users")
public class UsersRestController {

    private GetUsers getUsers;

    private CreateUsers createUsers;

    private DeleteUsers deleteUsers;

    private UpdateUsers updateUsers;

    public UsersRestController(GetUsers getUsers, CreateUsers createUsers, DeleteUsers deleteUsers,
                               UpdateUsers updateUsers) {

        this.getUsers = getUsers;
        this.createUsers=createUsers;
        this.deleteUsers=deleteUsers;
        this.updateUsers=updateUsers;
    }

    @GetMapping("/")
    List<Users> get(){
        return this.getUsers.getAll();
    }

    @PostMapping("/")
    ResponseEntity<Users> newUser(@RequestBody Users users){
        return new ResponseEntity<>(createUsers.save(users), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    ResponseEntity deleteUsers(@PathVariable Long id){

        deleteUsers.remove(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/{id}")
    ResponseEntity<Users> replaceUser(@RequestBody Users users, @PathVariable Long id){
        return new ResponseEntity<>(updateUsers.update(users, id), HttpStatus.OK);
    }
}
