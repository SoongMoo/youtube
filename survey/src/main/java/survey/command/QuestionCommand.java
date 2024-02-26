package survey.command;

import java.util.List;

public class QuestionCommand {	
		int surveyNum;
		int questionNum;
		String surveyWriter;
		String question;
		List<String> options;
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
		public String getQuestion() {
			return question;
		}
		public void setQuestion(String question) {
			this.question = question;
		}
		public List<String> getOptions() {
			return options;
		}
		public void setOptions(List<String> options) {
			this.options = options;
		}
}