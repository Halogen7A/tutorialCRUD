package com.example.tutorial.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.tutorial.model.Tutorial;
import com.example.tutorial.repo.TutorialRepo;

@Service
public class TutorialServices {

	@Autowired
	TutorialRepo tutorialRepo;
	
	
	public List<Tutorial> getAllTutorials() {
		return tutorialRepo.findAll();
	}
	
	public Optional<Tutorial> getTutorialById(Long id) {
		return tutorialRepo.findById(id);
	}
	
	public void addTutorial(Tutorial tutorial) {
		tutorialRepo.save(tutorial);
	}
	
	public void updateTutorial(Long id, Tutorial tutorial) {
		Optional<Tutorial> tutorialData = tutorialRepo.findById(id);
		
		if(tutorialData.isPresent()) {
			Tutorial newTutorial = tutorialData.get();
			newTutorial.setTitle(tutorial.getTitle());
			newTutorial.setDescription(tutorial.getDescription());
			newTutorial.setPublished(tutorial.isPublished());
			tutorialRepo.save(newTutorial);
		}
	}
	
	public void deleteTutorial(Long id) {
		tutorialRepo.deleteById(id);
	}
	
	public void deleteAllTutorials() {
		tutorialRepo.deleteAll();
	}
	
	public ResponseEntity<List<Tutorial>> findByPublished() {
		try {
			List<Tutorial> tutorials = tutorialRepo.findByPublished(true);
			
			if(tutorials.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			
			return new ResponseEntity<>(tutorials, HttpStatus.OK);
		}catch(Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	
	 
}
