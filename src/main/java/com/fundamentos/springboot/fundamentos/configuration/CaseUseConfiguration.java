package com.fundamentos.springboot.fundamentos.configuration;

import com.fundamentos.springboot.fundamentos.caseuse.GetUsers;
import com.fundamentos.springboot.fundamentos.caseuse.GetUsersImpl;
import com.fundamentos.springboot.fundamentos.service.UsersService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CaseUseConfiguration {

    @Bean
    GetUsers getUsers(UsersService usersService){
        return new GetUsersImpl(usersService);
    }
}
