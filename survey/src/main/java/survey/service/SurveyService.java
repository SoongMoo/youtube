package survey.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import survey.domain.OptionDTO;
import survey.domain.Question;
import survey.domain.QuestionDTO;
import survey.domain.QuestionOptionDTO;
import survey.mapper.SurveyMapper;

@Service
public class SurveyService {
	@Autowired
	SurveyMapper surveyMapper;
	
	public void execute(Integer surveyNum,String surveyWriter, Model model) {
		List<Question> questions = new ArrayList<Question>();

		List<QuestionOptionDTO> lists = surveyMapper.surveySelectAll(surveyNum,surveyWriter);
		model.addAttribute("questions", lists);
		
		String question = "";
		List<OptionDTO> list = null;
		Question q = null;
		if (lists != null) {
			for(QuestionOptionDTO dto : lists) {
				if(!question.equals(dto.getQuestion().getQuestionTitle())) {
					q = new Question();
					if(dto.getOption() != null && dto.getOption().getOptionsContent() != null)
						list = new ArrayList<OptionDTO>();
					else list = null;
					question = dto.getQuestion().getQuestionTitle();
					QuestionDTO qdto = new QuestionDTO();
					qdto.setQuestionNum(dto.getQuestion().getQuestionNum());
					qdto.setQuestionTitle(dto.getQuestion().getQuestionTitle());
					qdto.setSurveyNum(dto.getQuestion().getSurveyNum());
					qdto.setSurveyWriter(dto.getQuestion().getSurveyWriter());
					q.setQuestionDTO(qdto);
					q.setOptions(list);
					questions.add(q);
				}
				if(list != null) {
					OptionDTO odto = new OptionDTO();
					odto.setOptionsContent(dto.getOption().getOptionsContent());
					odto.setOptionsNum(dto.getOption().getOptionsNum());
					list.add(odto);
				}
			}
		}
		model.addAttribute("surveyNum", lists.get(0).getQuestion().getSurveyNum());
		model.addAttribute("surveyWriter", lists.get(0).getQuestion().getSurveyWriter());
		model.addAttribute("questions", questions);
	}
}