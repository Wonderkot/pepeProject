package ru.meow.pepeproject.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.meow.pepeproject.domain.FileEntity;
import ru.meow.pepeproject.repo.FileEntityRepository;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class FileEntityService {
    private final FileEntityRepository repository;

    public FileEntity checkEntity(Long checkSum) {
        FileEntity fe = repository.findByCheckSum(checkSum);
        if (fe == null) {
            System.out.println("File not found");
        }
        return fe;
    }

    public void createFileEntity(byte[] inputStream, String filename, Long sum) throws IOException {
        FileEntity fe = new FileEntity();
        fe.setCheckSum(sum);
        Path path = Path.of("./", "img");
        path.toFile().mkdir();
        path = Path.of(path.toString(), UUID.randomUUID().toString());
        path.toFile().mkdir();
        path = Path.of(path.toString(), filename);
        File file = path.toFile();
        Files.createFile(path);
        if (file.exists())
            Files.write(path, inputStream);

        fe.setPath(path.toString());


        repository.save(fe);
    }

    public FileEntity save(FileEntity fe) {
        return repository.save(fe);
    }

    public byte[] getRandomPepe() throws IOException {
        FileEntity fe = repository.getRandomRecord();
        File file = new File(fe.getPath());
        return Files.readAllBytes(Path.of(fe.getPath()));
    }
}
