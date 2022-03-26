package edu.fra.uas.repository.fileuploaddb;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import edu.fra.uas.model.fileuploaddb.FileDB;


//extends JpaRepository to use their function Methods
@Repository
public interface FileDBRepository extends JpaRepository<FileDB, String> {
}
