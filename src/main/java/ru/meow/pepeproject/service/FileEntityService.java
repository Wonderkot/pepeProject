package ru.meow.pepeproject.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.meow.pepeproject.domain.FileEntity;
import ru.meow.pepeproject.repo.FileEntityRepository;
import ru.meow.pepeproject.utils.CheckSumUtil;

import java.io.IOException;
import java.io.InputStream;
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

    public void createFileEntity(InputStream inputStream, String filename, Long sum) throws IOException {
        FileEntity fe = new FileEntity();
        fe.setCheckSum(sum);
        Path path = Path.of("./", UUID.randomUUID().toString(), filename);
        fe.setPath(path.toString());
        repository.save(fe);
    }

    public FileEntity save(FileEntity fe) {
        return repository.save(fe);
    }
}
