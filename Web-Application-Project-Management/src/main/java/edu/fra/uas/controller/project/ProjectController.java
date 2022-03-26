package edu.fra.uas.controller.project;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import edu.fra.uas.model.fileuploaddb.ResponseMessage;
import edu.fra.uas.model.project.Project;
import edu.fra.uas.service.fileUploadDb.FileStorageService;
import edu.fra.uas.service.project.ProjectService;

@Controller
public class ProjectController {

	@Autowired
	private ProjectService projectService;
	@Autowired
	private FileStorageService fileStorageService;

	// Catch the /login Mapping so the user can login(index.html is used as
	// Template)
	@GetMapping("/login")
	public String login() {

		return "login";

	}

	// "/pic" Mapping is catched so the user can use the Javascript in pic.html to
	// shot a Picture and download
	@GetMapping("/pic")
	public String picture() {

		return "/pic";

	}

	// ListProject Mapping is catched to the list of projects incl. search
	@GetMapping("/listProject")
	public String viewProject(Model model, String keyword) {

		if (keyword != null) {// if user entered a keyword show searched list

			model.addAttribute("listProject", projectService.findByKeyword(keyword));

		} else {// show all Projects
			model.addAttribute("listProject", projectService.getAllProject());

		}

		return "/listProject";

	}

	// Mapping to the form for adding new projects
	@GetMapping("/showNewProjectForm")
	public String showNewProjectForm(Model model) {
		Project project = new Project();
		model.addAttribute("project", project);
		return "new_Project";
	}

	// Save the entered value in to the Model
	@PostMapping("/saveProject")

	public String saveProject(@ModelAttribute("project") Project project) {

		project.setDate(projectService.currentDate());
		projectService.saveProject(project);
		return "redirect:/listProject";

	}

	// Update the project by id
	@GetMapping("/showFormForUpdate/{id}")
	public String showFormForUpdate(@PathVariable(value = "id") long id, Model model) {

		Project project = projectService.getProjectById(id);

		model.addAttribute("project", project);
		return "update_project";
	}

	// Delete a project by id
	@GetMapping("/deleteProject/{id}")
	public String deleteProject(@PathVariable(value = "id") long id) {
		this.projectService.deleteProjectById(id);

		return "redirect:/listProject";

	}

	// This Mapping is getting the current value of projects and send
	// it.(linechartdata will be used in javascript (HTML-->listProject)
	// getProjectNameAndCount is getting from Service and include the value from the
	// Repository(Query)
	@RequestMapping("/linechartdata")
	@ResponseBody
	public String getDataFromDB() {

		List<Project> dataList = projectService.getProjectNameAndCount();
		JsonArray jsonArrayPName = new JsonArray();
		JsonArray jsonArrayCount = new JsonArray();
		JsonObject jsonObject = new JsonObject();
		dataList.forEach(project -> {
			jsonArrayPName.add(project.getProjectName());// add the Value form getProjectName in to JsonArray
			jsonArrayCount.add(project.getCount());// add the Value form getCount in to JsonArray
		});
		jsonObject.add("categories", jsonArrayPName);// set value from JsonArray into Json Object so Js can get the
														// Value in Html
		jsonObject.add("series", jsonArrayCount);// set value from getCount() into Json Object so Js can get the Value
													// in Html
		return jsonObject.toString();
	}

	/*
	 * //select the folder it should save the uploaded data public static String
	 * uploadDirectory = System.getProperty("user.dir")+"/uploads";
	 * 
	 * //Mapping is used to get multiple files.The uploaded documents keeps the name
	 * and send it to the path that we selected before
	 * 
	 * @RequestMapping("/upload") public String upload(Model
	 * model,@RequestParam("files") MultipartFile[] files) { StringBuilder fileNames
	 * = new StringBuilder(); for (MultipartFile file : files) { Path
	 * fileNameAndPath = Paths.get(uploadDirectory, file.getOriginalFilename());
	 * fileNames.append(file.getOriginalFilename()+" "); try {
	 * Files.write(fileNameAndPath, file.getBytes()); } catch (IOException e) {
	 * e.printStackTrace(); } } model.addAttribute("msg",
	 * "Successfully uploaded files "+fileNames.toString()); return
	 * "uploadstatusview"; } //select the picture folder path. public static String
	 */

	// select the folder it should save the uploaded data
	public static String uploadDirectory2 = System.getProperty("user.dir") + "/picuploads";
	/*
	 * Mapping is used to get multiple files.The uploaded documents keeps the name
	 * and send it to the picture path that we selected before
	 */

	// catch "/upload2" Mapping with the Values/Files in "/picture" in Thymeleaf

	@RequestMapping("/upload2")
	public String uploadPic(Model model, @RequestParam("picture") MultipartFile[] files) {
		StringBuilder fileNames = new StringBuilder();
		for (MultipartFile file : files) {
			Path fileNameAndPath = Paths.get(uploadDirectory2, file.getOriginalFilename());// File name is setted in to
																							// original Filename
			fileNames.append(file.getOriginalFilename() + " ");
			try {
				Files.write(fileNameAndPath, file.getBytes()); // To save File in to folder
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		model.addAttribute("msg", "Successfully uploaded files " + fileNames.toString());
		return "uploadstatusview";// send msg in HTML format
	}

	// Catch "/uploadDb" Mapping "files" in Thymeleaf.Send success or fail msg
	@PostMapping("/uploadDb")
	public ResponseEntity<ResponseMessage> uploadFile(@RequestParam("files") MultipartFile file) {
		String message = "Please Upload the Document.";
		try {
			fileStorageService.store(file);
			message = "Uploaded the file successfully: " + file.getOriginalFilename();
			return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
		} catch (Exception e) {
			message = "Could not upload the file: " + file.getOriginalFilename() + "!";
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));// send
																											// success
																											// and fail
																											// message

		}
	}

}