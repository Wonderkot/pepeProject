package ru.meow.pepeproject.repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.meow.pepeproject.domain.FileEntity;

@Repository
@Transactional
public interface FileEntityRepository extends CrudRepository<FileEntity, Long> {
    FileEntity findByCheckSum(Long checkSum);
}