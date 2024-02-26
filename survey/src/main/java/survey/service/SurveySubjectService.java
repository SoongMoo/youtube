package survey.service;

import java.sql.Timestamp;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import survey.command.SurveySubjectCommand;
import survey.domain.SurveySubjectDTO;
import survey.mapper.SurveyMapper;
@Service
public class SurveySubjectService {
	@Autowired
	SurveyMapper surveyMapper;
	public int execute(SurveySubjectCommand surveySubjectCommand) {
		SurveySubjectDTO dto = new SurveySubjectDTO();
		BeanUtils.copyProperties(surveySubjectCommand, dto);
		dto.setSurveyStart(Timestamp.valueOf(surveySubjectCommand.getSurveyStart()));
		dto.setSurveyEnd(Timestamp.valueOf(surveySubjectCommand.getSurveyEnd()));
		int num = surveyMapper.surveyNum(dto.getSurveyWriter());
		dto.setSurveyNum(num);
		surveyMapper.surveyInsert(dto);
		return num;
	}
}
