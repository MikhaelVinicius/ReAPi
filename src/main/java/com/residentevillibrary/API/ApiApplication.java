package com.residentevillibrary.API;

import com.residentevillibrary.API.entity.Game; // Importe a entidade Game
import com.residentevillibrary.API.repository.GameRepository; // Importe o repositório
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Arrays;

@SpringBootApplication
public class ApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiApplication.class, args);
	}


@Bean
CommandLineRunner initDatabase(GameRepository gameRepository) {
    return args -> {
        gameRepository.saveAll(Arrays.asList(
             
                new Game("Resident Evil 4 Remake", "2023", "PS5, PS4, Xbox Series X/S, PC"),
                new Game("Resident Evil Village", "2021", "PS5, PS4, Xbox Series X/S, PC"),
                new Game("Resident Evil 3 Remake", "2020", "PS4, Xbox One, PC"),
                new Game("Resident Evil 2 Remake", "2019", "PS4, Xbox One, PC"),
                new Game("Resident Evil 7: Biohazard", "2017", "PS4, Xbox One, PC"),
                new Game("Resident Evil", "1996", "PlayStation, PC"),
                new Game("Resident Evil 4", "2005", "GameCube, PS2")
        ));
        System.out.println(">>> Banco de dados populado com jogos de exemplo! <<<");
    };
}
}