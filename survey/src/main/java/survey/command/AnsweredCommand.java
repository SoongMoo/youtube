package survey.command;

import java.util.Map;

public class AnsweredCommand {
	int surveyNum;
	String surveyWriter;
	Map<Integer , String> responses;
	Respondent res;
	public int getSurveyNum() {
		return surveyNum;
	}
	public void setSurveyNum(int surveyNum) {
		this.surveyNum = surveyNum;
	}
	public String getSurveyWriter() {
		return surveyWriter;
	}
	public void setSurveyWriter(String surveyWriter) {
		this.surveyWriter = surveyWriter;
	}
	public Map<Integer, String> getResponses() {
		return responses;
	}
	public void setResponses(Map<Integer, String> responses) {
		this.responses = responses;
	}
	public Respondent getRes() {
		return res;
	}
	public void setRes(Respondent res) {
		this.res = res;
	}
}
