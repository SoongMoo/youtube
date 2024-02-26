package survey.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import survey.command.AnsweredCommand;
import survey.domain.AnswerDTO;
import survey.domain.AnswerProbabilityDTO;
import survey.mapper.SurveyMapper;

@Service
public class SurveyAnswerService {
	@Autowired
	SurveyMapper surveyMapper;
	public void execute(AnsweredCommand data, Model model) {
		List<AnswerDTO> list = new ArrayList<AnswerDTO>();
		Map<Integer, String> responses = data.getResponses();
		
		responses.forEach((key, value) -> {
			AnswerDTO dto = new AnswerDTO();
			dto.setAnswerAge(data.getRes().getAge());
			dto.setAnswerLocation(data.getRes().getLocation());
			dto.setAnswerWriter(data.getRes().getUserName());
			dto.setQuestionNum(key);
			dto.setOptionsNum(value);
			dto.setSurveyNum(data.getSurveyNum());
			dto.setSurveyWriter(data.getSurveyWriter());
			list.add(dto);
        });
		surveyMapper.answerInsert(list);
		List<AnswerProbabilityDTO>  dtos = 
				surveyMapper.answerProbability(data.getSurveyNum(), data.getSurveyWriter());
		model.addAttribute("dtos", dtos);
	}
}
