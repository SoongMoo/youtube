package survey.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import survey.domain.SurveySubjectDTO;
import survey.mapper.SurveyMapper;

@Service
public class SurveyListService {
	@Autowired
	SurveyMapper surveyMapper;
	public void execute(Model model) {
		List<SurveySubjectDTO> list = surveyMapper.selectSurvey();
		model.addAttribute("list", list);
	}
}
