package ru.meow.pepeproject;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.util.FileSystemUtils;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@SpringBootApplication
public class PepeProjectApplication {

    @Value("${cleanOnStart}")
    private boolean preClean;

    public static void main(String[] args) {
        SpringApplication.run(PepeProjectApplication.class, args);
    }

    @PostConstruct
    private void clean() {
        if (preClean) {
            Path path = Path.of("./", "img");
            try {
                FileSystemUtils.deleteRecursively(path.toFile());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}
