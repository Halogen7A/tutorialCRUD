package com.example.tutorial.controller;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.example.tutorial.model.Tutorial;
import com.example.tutorial.service.TutorialServices;

@RestController
public class TutorialCon {

	@Autowired
	TutorialServices tutorialServices;

	@GetMapping("/tutorials")
	public List<Tutorial> getAllTutorials(@RequestParam(required = false) String title) {
		return tutorialServices.getAllTutorials();
	}
	
	@GetMapping("/tutorials/{id}")
	public Optional<Tutorial> getTutorialById(@PathVariable Long id) {
		return tutorialServices.getTutorialById(id);
	}

	@PostMapping("/tutorials")
	public void addTutorial(@RequestBody Tutorial tutorial) {
		tutorialServices.addTutorial(tutorial);
	}

	@PutMapping("/tutorials/{id}")
	public void updateTutorial(@PathVariable Long id, Tutorial tutorial) {
		Optional<Tutorial> tutorialData = tutorialServices.getTutorialById(id);

		if (tutorialData.isPresent()) {
			Tutorial newTutorial = tutorialData.get();
			newTutorial.setTitle(tutorial.getTitle());
			newTutorial.setDescription(tutorial.getDescription());
			newTutorial.setPublished(tutorial.isPublished());
			tutorialServices.addTutorial(newTutorial);
			;
		}
	}

	@DeleteMapping("/tutorials/{id}")
	public void deleteTutorial(Long id) {
		tutorialServices.deleteTutorial(id);
		;
	}
	
	@DeleteMapping("/tutorials")
	public void deleteAllTutorials() {
		tutorialServices.deleteAllTutorials();
		;
	}

	@GetMapping("/tutorials/published")
	public ResponseEntity<List<Tutorial>> findByPublished() {
		return tutorialServices.findByPublished();
	}
}
