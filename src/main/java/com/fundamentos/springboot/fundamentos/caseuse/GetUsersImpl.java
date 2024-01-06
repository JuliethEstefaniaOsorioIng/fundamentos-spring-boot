package com.fundamentos.springboot.fundamentos.caseuse;

import com.fundamentos.springboot.fundamentos.entity.Users;
import com.fundamentos.springboot.fundamentos.service.UsersService;

import java.util.List;

public class GetUsersImpl implements GetUsers{

    private UsersService usersService;

    public GetUsersImpl(UsersService usersService) {
        this.usersService = usersService;
    }

    @Override
    public List<Users> getAll() {
        return usersService.getAll();
    }
}
