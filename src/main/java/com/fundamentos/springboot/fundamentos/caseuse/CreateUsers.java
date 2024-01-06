package com.fundamentos.springboot.fundamentos.caseuse;

import com.fundamentos.springboot.fundamentos.entity.Users;
import com.fundamentos.springboot.fundamentos.service.UsersService;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Component;

@Component
public class CreateUsers {

    private UsersService usersService;

    public CreateUsers(UsersService usersService){
        this.usersService=usersService;
    }

    public Users save(Users users){
        return usersService.save(users);
    }
}
