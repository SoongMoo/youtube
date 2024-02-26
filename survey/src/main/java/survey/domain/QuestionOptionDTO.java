package survey.domain;

import org.apache.ibatis.type.Alias;

@Alias("questions")
public class QuestionOptionDTO {
	QuestionDTO question;
	OptionDTO option;
	public QuestionDTO getQuestion() {
		return question;
	}
	public void setQuestion(QuestionDTO question) {
		this.question = question;
	}
	public OptionDTO getOption() {
		return option;
	}
	public void setOption(OptionDTO option) {
		this.option = option;
	}
}
