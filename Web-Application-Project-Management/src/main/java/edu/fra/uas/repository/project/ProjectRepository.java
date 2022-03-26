package edu.fra.uas.repository.project;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import edu.fra.uas.model.project.Project;

//extends JpaRepository to use their function Methods
@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {

	//@Query is used when the function of the JpaRepository is not enough
	//This Query is filtering the Data by Keyword and set the selected Data in to a new List
	@Query(value = "select * from Project e where e.Projekt_Name like %:keyword% or e.Projekt_Jahr like %:keyword% or e.Name like %:keyword% or e.Nachname like %:keyword% ", nativeQuery = true)
	List<Project> findByKeyword(@Param("keyword") String keyword);

	//This Query is filtering by Name an Count 
	@Query(value = " select e.Projekt_Name,count(e.Projekt_Name)as count,e.Id,e.Projekt_Jahr,e.Name,e.Nachname,e.Datum from Project e Group by e.Projekt_Name", nativeQuery = true)
	List<Project> getProjectNameAndCount();
	
}
