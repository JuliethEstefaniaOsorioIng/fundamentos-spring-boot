package com.fundamentos.springboot.fundamentos.caseuse;

import com.fundamentos.springboot.fundamentos.entity.Users;
import com.fundamentos.springboot.fundamentos.service.UsersService;
import org.springframework.stereotype.Component;

@Component
public class UpdateUsers {

    private UsersService usersService;

    public UpdateUsers(UsersService usersService){
        this.usersService=usersService;
    }

    public Users update(Users users, Long id) {
        return usersService.update(users,id);
    }
}
