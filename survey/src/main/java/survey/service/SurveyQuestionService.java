package survey.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import survey.mapper.SurveyMapper;

@Service
public class SurveyQuestionService {
	@Autowired
	SurveyMapper surveyMapper;
	public void execute(String surveyWriter,Model model, Integer surveyNum) {
		int questionNum = surveyMapper.questionNumSelect(surveyWriter, surveyNum);
		model.addAttribute("surveyWriter", surveyWriter);
		model.addAttribute("questionNum", questionNum);
	}
}
