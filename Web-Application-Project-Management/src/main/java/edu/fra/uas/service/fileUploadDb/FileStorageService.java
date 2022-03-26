package edu.fra.uas.service.fileUploadDb;

import java.io.IOException;
import java.util.stream.Stream;

import org.springframework.web.multipart.MultipartFile;

import edu.fra.uas.model.fileuploaddb.FileDB;
//interface is used to handle later changes on methods easier and faster.Dependency Injection and IOC can be realized .
public interface FileStorageService {
	
	
	 FileDB store(MultipartFile file) throws IOException;
	 FileDB getFile(String id);
	Stream<FileDB> getAllFiles();
}
