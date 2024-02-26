package survey.command;

import java.time.LocalDateTime;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class SurveySubjectCommand {
	String surveySubject;
	String surveyWriter;
	
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
	LocalDateTime surveyStart;
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
	LocalDateTime surveyEnd;
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
	public LocalDateTime getSurveyStart() {
		return surveyStart;
	}
	public void setSurveyStart(LocalDateTime surveyStart) {
		this.surveyStart = surveyStart;
	}
	public LocalDateTime getSurveyEnd() {
		return surveyEnd;
	}
	public void setSurveyEnd(LocalDateTime surveyEnd) {
		this.surveyEnd = surveyEnd;
	}
	
}
