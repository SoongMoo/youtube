package survey.domain;

import org.apache.ibatis.type.Alias;

@Alias("questionDTO")
public class QuestionDTO {
	int surveyNum;
	int questionNum;
	String questionTitle;
	String surveyWriter;
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
	public String getQuestionTitle() {
		return questionTitle;
	}
	public void setQuestionTitle(String questionTitle) {
		this.questionTitle = questionTitle;
	}
}
