package survey.domain;

import org.apache.ibatis.type.Alias;

@Alias("optionDTO")
public class OptionDTO {
	int surveyNum;
	String surveyWriter;
	int questionNum;
	int optionsNum;
	String optionsContent;
	
	public String getSurveyWriter() {
		return surveyWriter;
	}
	public void setSurveyWriter(String surveyWriter) {
		this.surveyWriter = surveyWriter;
	}
	public int getSurveyNum() {
		return surveyNum;
	}
	public void setSurveyNum(int surveyNum) {
		this.surveyNum = surveyNum;
	}
	public int getQuestionNum() {
		return questionNum;
	}
	public void setQuestionNum(int questionNum) {
		this.questionNum = questionNum;
	}
	public int getOptionsNum() {
		return optionsNum;
	}
	public void setOptionsNum(int optionsNum) {
		this.optionsNum = optionsNum;
	}
	public String getOptionsContent() {
		return optionsContent;
	}
	public void setOptionsContent(String optionsContent) {
		this.optionsContent = optionsContent;
	}
}
