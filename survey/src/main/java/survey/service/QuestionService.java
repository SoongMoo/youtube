package survey.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import survey.command.QuestionCommand;
import survey.domain.OptionDTO;
import survey.domain.QuestionDTO;
import survey.mapper.SurveyMapper;
@Service
public class QuestionService {
	@Autowired
	SurveyMapper surveyMapper;
	public void execute(QuestionCommand questionCommand) {
		QuestionDTO dto = new QuestionDTO();
		dto.setSurveyNum(questionCommand.getSurveyNum());
		dto.setQuestionTitle(questionCommand.getQuestion());
		dto.setQuestionNum(questionCommand.getQuestionNum());
		dto.setSurveyWriter(questionCommand.getSurveyWriter());
		surveyMapper.questionInsert(dto);
		if(questionCommand.getOptions() != null) {
			int idx = 1;
			for(String option : questionCommand.getOptions()) {
				System.out.println(option);
				OptionDTO odto = new OptionDTO();
				odto.setSurveyNum(questionCommand.getSurveyNum());
				odto.setSurveyWriter(questionCommand.getSurveyWriter());
				odto.setQuestionNum(questionCommand.getQuestionNum());
				odto.setOptionsNum(idx++);
				odto.setOptionsContent(option);
				surveyMapper.optionsInsert(odto);
				System.out.println(idx);
			}
		}
	}
}
