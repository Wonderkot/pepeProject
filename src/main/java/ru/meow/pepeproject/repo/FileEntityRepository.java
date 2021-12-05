package ru.meow.pepeproject.repo;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.meow.pepeproject.domain.FileEntity;

@Repository
@Transactional
public interface FileEntityRepository extends CrudRepository<FileEntity, Long> {
    FileEntity findByCheckSum(Long checkSum);

    @Query(value = "select * from file_entity ORDER BY RANDOM() LIMIT 1", nativeQuery = true)
    FileEntity getRandomRecord();
}