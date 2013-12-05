package com.example.geologicalapp;

import java.util.ArrayList;

public class Questions {
	
	String questions;
	String answers;
	int images;
	int qid;
	ArrayList<String> allanswers;

	public int getQId() {
		return qid;
	}
	public void setQId(int qid) {
		this.qid = qid;
	}
	public String getQuestions() {
		return questions;
	}
	public void setQuestions(String questions) {
		this.questions = questions;
	}
	public String getAnswers() {
		return answers;
	}
	public void setAnswers(String answers) {
		this.answers = answers;
	}
	public int getImages() {
		return images;
	}
	public void setImages(int images) {
		this.images = images;
	}
	public ArrayList<String> getAllAnswers() {
		return allanswers;
	}
	public void setAllAanswers(ArrayList<String> allanswers) {
		this.allanswers = allanswers;
	}
	
	
}
