package com.nisum.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nisum.domain.Project;
import com.nisum.repositories.ProjectRepository;

@RestController
@RequestMapping("/projects")
public class ProjectController {
	private ProjectRepository projectRepository;

	@Autowired
	public ProjectController(ProjectRepository projectRepository) {
		this.projectRepository = projectRepository;
	}

	@RequestMapping(method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
	public ResponseEntity<Project> create(@RequestBody Project Project) {
		Project project = projectRepository.save(Project);
		return new ResponseEntity<Project>(project, HttpStatus.CREATED);
	}

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<Project>> list() {
		List<Project> lstProject = this.projectRepository.findAll();
		return new ResponseEntity(lstProject, HttpStatus.ACCEPTED);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<List<Project>> get(@RequestParam("id") String id) {
		Project project = this.projectRepository.findOne(id);
		return new ResponseEntity(project, HttpStatus.ACCEPTED);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
	public ResponseEntity<Project> update(@RequestParam("id") long id, @RequestBody Project Project) {
		Project project = projectRepository.save(Project);
		return new ResponseEntity<Project>(project, HttpStatus.CREATED);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Boolean> delete(@RequestParam("id") String id) {
		this.projectRepository.delete(id);
		return new ResponseEntity<Boolean>(Boolean.TRUE, HttpStatus.OK);
	}
}
