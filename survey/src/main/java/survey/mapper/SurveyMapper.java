package survey.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import survey.domain.AnswerDTO;
import survey.domain.AnswerProbabilityDTO;
import survey.domain.OptionDTO;
import survey.domain.QuestionDTO;
import survey.domain.QuestionOptionDTO;
import survey.domain.SurveySubjectDTO;
@Mapper
public interface SurveyMapper {
	public int surveyNum(String surveyWriter);
	public int surveyInsert(SurveySubjectDTO dto);
	public int questionNumSelect(
			@Param(value = "surveyWriter") String  surveyWriter, 
			@Param(value = "surveyNum") Integer surveyNum);
	public int questionInsert(QuestionDTO dto);
	public void optionsInsert(OptionDTO odto);
	public List<SurveySubjectDTO> selectSurvey();
	public List<QuestionOptionDTO> surveySelectAll( @Param(value = "surveyNum") Integer  surveyNum,
													@Param(value = "surveyWriter") String surveyWriter);
	public int answerInsert(List<AnswerDTO> list);
	public List<AnswerProbabilityDTO> answerProbability(@Param(value = "surveyNum") Integer  surveyNum,
												  @Param(value = "surveyWriter") String surveyWriter);
}