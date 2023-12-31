package com.fundamentos.springboot.fundamentos;

import com.fundamentos.springboot.fundamentos.bean.MyBean;
import com.fundamentos.springboot.fundamentos.bean.MyBeanWithDependency;
import com.fundamentos.springboot.fundamentos.bean.MyBeanWithProperties;
import com.fundamentos.springboot.fundamentos.component.CompenentDependency;
import com.fundamentos.springboot.fundamentos.entity.Users;
import com.fundamentos.springboot.fundamentos.pojo.UserPojo;
import com.fundamentos.springboot.fundamentos.repository.UserRepository;
import com.fundamentos.springboot.fundamentos.service.UsersService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.domain.Sort;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@SpringBootApplication
public class FundamentosApplication implements CommandLineRunner {

	private final Log LOGGER = LogFactory.getLog(FundamentosApplication.class);
	private CompenentDependency compenentDependency;
	private MyBean myBean;
	private MyBeanWithDependency myBeanWithDependency;
	private MyBeanWithProperties myBeanWithProperties;
	private UserPojo userPojo;

	private UserRepository userRepository;

	private UsersService usersService;

	public FundamentosApplication (@Qualifier("componentTwoImpl") CompenentDependency compenentDependency,
								   MyBean myBean, MyBeanWithDependency myBeanWithDependency, MyBeanWithProperties
								    myBeanWithProperties, UserPojo userPojo,UserRepository userRepository,
								   UsersService usersService){
		this.compenentDependency=compenentDependency;
		this.myBean = myBean;
		this.myBeanWithDependency = myBeanWithDependency;
		this.myBeanWithProperties=myBeanWithProperties;
		this.userPojo=userPojo;
		this.userRepository=userRepository;
		this.usersService=usersService;
	}
	public static void main(String[] args) {
		SpringApplication.run(FundamentosApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		//ejemplosAnteriores();
		saveUserInDataBase();
		//getInformationJpqlFromUser();
		saveWithErrorTransaccional();
	}

	private void saveWithErrorTransaccional(){
		Users users1 = new Users("users1", "users1@domain.co",
				LocalDate.of(1995,01,02));
		Users users2 = new Users("users2", "users2@domain.co",
				LocalDate.of(1994,01,02));
		Users users3 = new Users("users3", "users1@domain.co",
				LocalDate.of(1993,01,02));
		Users users4 = new Users("users4", "users4@domain.co",
				LocalDate.of(1996,01,02));

		List<Users> usersList = Arrays.asList(users1,users2,users3,users4);

		try {

			usersService.saveTransactional(usersList);

		}catch (Exception e){
			LOGGER.error("Se generó un error en la inserción de datos en el transaccional");
		}

		usersService.getAll().stream().forEach(users -> LOGGER.info("Estos son los usuarios del getAll "
		+users));
	}

	private void getInformationJpqlFromUser(){
		LOGGER.info("El usuario encontrado es "+userRepository.findMyUserByEmail("john@domain.co")
				.orElseThrow(()->new RuntimeException("No se encontró")));

		userRepository.findByAndSort("J", Sort.by("id").ascending())
				.stream()
				.forEach(user->LOGGER.info("user con metodo sort "+user));

		userRepository.findByName("Jane").stream()
				.forEach(users -> LOGGER.info("user por name "+users ));

		LOGGER.info("User con query metod por nombre e email "+
				userRepository.findByNameAndEmail("Marie", "marie@domain.co")
				.orElseThrow(()-> new RuntimeException("No se encontró")));

		userRepository.findByNameLike("%J%").stream()
				.forEach(user->LOGGER.info("Lista de usarios con like "+user));

		userRepository.findByNameOrEmail(null,"john@domain.co").stream()
				.forEach(user->LOGGER.info("Lista de usarios con findByNameOrEmail "+user));

		userRepository.findByBirthdayBetween(
				LocalDate.of(1980,01,01), LocalDate.of(1990,01,01)
		).stream().forEach(user->LOGGER.info("Usuarios buscados por fecha "+user));

		userRepository.findByNameContainingOrderByIdDesc("a").stream()
				.forEach(users -> LOGGER.info("Buscado con containing y ordenado "+users));

		LOGGER.info("El usuario encontrado es "+userRepository
				.getAllByBirthdayAndEmail(LocalDate.of(1994,01,02),"john@domain.co")
		);
	}

	private void saveUserInDataBase(){
		Users user1 = new Users("John", "john@domain.co",
				LocalDate.of(1994,01,02));
		Users user2 = new Users("Jane", "jane@domain.co",
				LocalDate.of(1980,10,20));
		Users user3 = new Users("Marie", "marie@domain.co",
				LocalDate.of(1981,10,25));

		List<Users> list= Arrays.asList(user1,user2,user3);

		list.stream().forEach(userRepository::save);
	}

	private void ejemplosAnteriores(){
		compenentDependency.saludar();
		myBean.print();
		myBeanWithDependency.printWithDependency();
		System.out.println(myBeanWithProperties.function());
		System.out.println(userPojo.getEmail()+"-"+userPojo.getPassword());

		try{
			int value = 10/0;
			LOGGER.info("My valor "+value);
		}catch (Exception e){
			LOGGER.error("Esto es un error de la división por cero "+e.getMessage());
		}
	}
}
