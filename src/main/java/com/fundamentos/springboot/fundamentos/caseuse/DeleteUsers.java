package com.fundamentos.springboot.fundamentos.caseuse;

import com.fundamentos.springboot.fundamentos.service.UsersService;
import org.springframework.stereotype.Component;

@Component
public class DeleteUsers {

    private UsersService usersService;

    public DeleteUsers(UsersService usersService){
        this.usersService=usersService;
    }

    public void remove(Long id) {
        usersService.delete(id);
    }
}
