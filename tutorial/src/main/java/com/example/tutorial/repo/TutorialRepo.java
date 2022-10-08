package com.example.tutorial.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.tutorial.model.Tutorial;

public interface TutorialRepo extends JpaRepository<Tutorial, Long>{

	List<Tutorial> findByPublished(boolean published);
}
