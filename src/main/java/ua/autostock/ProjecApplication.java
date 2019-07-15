package ua.autostock;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;
import ua.autostock.entity.CarEntity;
import ua.autostock.entity.RoleEntity;
import ua.autostock.entity.UserEntity;
import ua.autostock.repository.CarRepository;
import ua.autostock.repository.RoleRepository;
import ua.autostock.repository.UserRepository;

import java.time.LocalDate;
import java.util.Arrays;


@SpringBootApplication

public class ProjecApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(ProjecApplication.class, args);
    }

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CarRepository carRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public void run(String... args) throws Exception {

        if(roleRepository.count() == 0){
            RoleEntity roleAdmin = new RoleEntity();
            roleAdmin.setRole("ADMIN");

            RoleEntity roleUser = new RoleEntity();
            roleUser.setRole("USER");

            roleRepository.saveAll(Arrays.asList(roleAdmin, roleUser));
        }

        if (userRepository.count() == 0){

            RoleEntity roleAdmin = roleRepository.findByRoleIgnoreCase("ADMIN").get();
            RoleEntity roleUser = roleRepository.findByRoleIgnoreCase("USER").get();

            UserEntity userEntity1 = new UserEntity();
            userEntity1.setFirstName("Roma");
            userEntity1.setSurName("Amor");
            userEntity1.setTelephone("380635207411");
            userEntity1.setPassword(passwordEncoder.encode("123456"));
            userEntity1.setEmail("amor@gmail.com");
            userEntity1.setRegistryDate("2012-11-11");
            userEntity1.setGender("male");
            userEntity1.setRoles(Arrays.asList(roleAdmin));
            userEntity1.setAge(26);
            userRepository.save(userEntity1);


            for (int i = 0; i <= 50; i++){
                UserEntity userEntity = new UserEntity();
                userEntity.setFirstName("user" + i);
                userEntity.setSurName("Doe" + i);
                userEntity.setTelephone("380635207" + i);
                userEntity.setPassword(passwordEncoder.encode("123456"));
                userEntity.setEmail("user" + i + "@gmail.com");
                userEntity.setRegistryDate("2012-11-11");
                userEntity.setGender("male");
                userEntity.setRoles(Arrays.asList(roleUser));
                userEntity.setAge(i);
                userRepository.save(userEntity);
            }
        }

        if (carRepository.count() == 0){
            for (int i = 0; i <=250; i++){
                CarEntity carEntity = new CarEntity();
                carEntity.setCategory("category" + i);
                carEntity.setMake("make" + i);
                carEntity.setModel("model" + i);
                carEntity.setMileage(i);
                carEntity.setDateOfManufacture(LocalDate.of(2012,06,12));
                carEntity.setAddTime(LocalDate.now());
                carEntity.setVin("vin" + i);
                carEntity.setPrice(99 + i);
                carEntity.setVolume(2000 + i);
                carEntity.setDrive("AWD");
                carEntity.setTransmission("transmission");
                carEntity.setBody("sedan");
                carEntity.setFuel("diesel");
                carEntity.setColor("blue");
                carEntity.setNumberOfDoors(5);
                carEntity.setNumberOfSeats(5);
                carEntity.setComfort("yes");
                carEntity.setAdditionalEquipment("yes");
                carEntity.setDescription("sale my car");

                carRepository.save(carEntity);
            }
        }
    }


}

