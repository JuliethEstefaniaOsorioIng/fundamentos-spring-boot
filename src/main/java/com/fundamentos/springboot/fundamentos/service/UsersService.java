package com.fundamentos.springboot.fundamentos.service;

import com.fundamentos.springboot.fundamentos.entity.Users;
import com.fundamentos.springboot.fundamentos.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.apache.commons.logging.Log;


import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsersService {

    private final Log LOGGER = LogFactory.getLog(UsersService.class);

    private UserRepository userRepository;

    public UsersService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional
    public void saveTransactional(List<Users> users){
        users.stream().peek(user-> LOGGER.info("Usuario insertado "+user)).forEach(userRepository::save);
    }

    public List<Users> getAll(){
        return userRepository.findAll();
    }

    public Users save(Users users) {
        return userRepository.save(users);
    }

    public void delete(Long id) {
        userRepository.deleteById(id);
    }

    public Users update(Users users, Long id) {

        return userRepository.findById(id)
                .map(
                        user->{
                            user.setName(users.getName());
                            user.setEmail(users.getEmail());
                            user.setBirthday(users.getBirthday());
                            return userRepository.save(user);
                        }
                ).get();
    }
}
