package survey.domain;

import java.sql.Timestamp;

import org.apache.ibatis.type.Alias;

@Alias("survey")
public class SurveySubjectDTO {
	int  surveyNum;
	String surveySubject;
	String surveyWriter;
	Timestamp surveyStart;
	Timestamp surveyEnd;
	String surveyStatus;
	
	public String getSurveyStatus() {
		return surveyStatus;
	}
	public void setSurveyStatus(String surveyStatus) {
		this.surveyStatus = surveyStatus;
	}
	public int getSurveyNum() {
		return surveyNum;
	}
	public void setSurveyNum(int surveyNum) {
		this.surveyNum = surveyNum;
	}
	public String getSurveySubject() {
		return surveySubject;
	}
	public void setSurveySubject(String surveySubject) {
		this.surveySubject = surveySubject;
	}
	public String getSurveyWriter() {
		return surveyWriter;
	}
	public void setSurveyWriter(String surveyWriter) {
		this.surveyWriter = surveyWriter;
	}
	public Timestamp getSurveyStart() {
		return surveyStart;
	}
	public void setSurveyStart(Timestamp surveyStart) {
		this.surveyStart = surveyStart;
	}
	public Timestamp getSurveyEnd() {
		return surveyEnd;
	}
	public void setSurveyEnd(Timestamp surveyEnd) {
		this.surveyEnd = surveyEnd;
	}
}
