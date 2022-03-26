package edu.fra.uas.service.fileUploadDb;

import java.io.IOException;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import edu.fra.uas.model.fileuploaddb.FileDB;
import edu.fra.uas.repository.fileuploaddb.FileDBRepository;

@Service
public class FileStorageServiceimpl implements FileStorageService {
	
	//use the FileDBRepository
	@Autowired
	private FileDBRepository fileDBRepository;

	
	//Store the catched file in Datebase by using .save
	@Override
	public FileDB store(MultipartFile file) throws IOException {
		String fileName = StringUtils.cleanPath(file.getOriginalFilename());
		FileDB FileDB = new FileDB(fileName, file.getContentType(), file.getBytes());
		return fileDBRepository.save(FileDB);
	}
//get the File by ID for example to Download 
	@Override 
	public FileDB getFile(String id) {
		return fileDBRepository.findById(id).get();
	}
//Get the Filefrom Database to show this in View
	@Override
	public Stream<FileDB> getAllFiles() {
		return fileDBRepository.findAll().stream();
	}
}
