package survey.domain;

public class AnswerDTO {
	String surveyWriter;
	int surveyNum;
	int questionNum;
	String answerWriter;
	String optionsNum;
	String answerLocation;
	int answerAge;
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
	public String getAnswerWriter() {
		return answerWriter;
	}
	public void setAnswerWriter(String answerWriter) {
		this.answerWriter = answerWriter;
	}
	public String getOptionsNum() {
		return optionsNum;
	}
	public void setOptionsNum(String optionsNum) {
		this.optionsNum = optionsNum;
	}
	public String getAnswerLocation() {
		return answerLocation;
	}
	public void setAnswerLocation(String answerLocation) {
		this.answerLocation = answerLocation;
	}
	public int getAnswerAge() {
		return answerAge;
	}
	public void setAnswerAge(int answerAge) {
		this.answerAge = answerAge;
	}
}
