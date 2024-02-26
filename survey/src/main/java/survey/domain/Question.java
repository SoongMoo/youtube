package survey.domain;

import java.util.List;

public class Question {
	private QuestionDTO questionDTO;
	private List<OptionDTO> options;
	public Question() {}
	public Question(QuestionDTO questionDTO, List<OptionDTO> options) {
		this.questionDTO = questionDTO;
		this.options = options;
	}
	public Question(QuestionDTO questionDTO) {
		this.questionDTO = questionDTO;
	}
	public QuestionDTO getQuestionDTO() {
		return questionDTO;
	}
	public void setQuestionDTO(QuestionDTO questionDTO) {
		this.questionDTO = questionDTO;
	}
	public List<OptionDTO> getOptions() {
		return options;
	}
	public void setOptions(List<OptionDTO> options) {
		this.options = options;
	}	
}
