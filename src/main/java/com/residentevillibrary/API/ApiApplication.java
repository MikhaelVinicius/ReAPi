package com.residentevillibrary.API;

import com.residentevillibrary.API.entity.Game;
import com.residentevillibrary.API.entity.GameFile; // <-- Use a entidade correta
import com.residentevillibrary.API.repository.GameRepository;
import com.residentevillibrary.API.repository.GameFileRepository; // <-- Use o repositório correto
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Arrays;
import java.util.List;

@SpringBootApplication
public class ApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(ApiApplication.class, args);
    }

    @Bean
    CommandLineRunner initDatabase(GameRepository gameRepository, GameFileRepository gameFileRepository) { // <-- Nome corrigido aqui
        return args -> {
            // Limpa o banco de dados para evitar duplicatas a cada reinicialização
            gameFileRepository.deleteAll();
            gameRepository.deleteAll();

            // Cria e salva os jogos
            Game re4r = new Game("Resident Evil 4 Remake", "2023", "PS5, PS4, Xbox Series X/S, PC");
            Game re2r = new Game("Resident Evil 2 Remake", "2019", "PS4, Xbox One, PC");
            Game re7 = new Game("Resident Evil 7: Biohazard", "2017", "PS4, Xbox One, PC");
            
            gameRepository.saveAll(Arrays.asList(re4r, re2r, re7));

            // Cria e salva arquivos, associando-os aos jogos
            GameFile file1 = new GameFile();
            file1.setFileName("Jack's Memo");
            file1.setFileType("TXT");
            file1.setAuthor("Jack Baker");
            file1.setDescription("A note found in the main house.");
            file1.setGame(re7); // <-- Associa o arquivo ao jogo RE7

            GameFile file2 = new GameFile();
            file2.setFileName("R.P.D. Evacuation Plan");
            file2.setFileType("PDF");
            file2.setAuthor("R.P.D.");
            file2.setDescription("Emergency evacuation routes for police officers.");
            file2.setGame(re2r); // <-- Associa o arquivo ao jogo RE2 Remake

            gameFileRepository.saveAll(Arrays.asList(file1, file2));


            System.out.println(">>> Banco de dados populado com jogos e arquivos de exemplo! <<<");
        };
    }
}