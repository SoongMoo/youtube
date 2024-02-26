package survey.domain;

import org.apache.ibatis.type.Alias;

@Alias("answerProbabilityDTO")
public class AnswerProbabilityDTO {
	private int questionNum;
    private int option1Count;
    private double option1Probability;
    private int option2Count;
    private double option2Probability;
    private int option3Count;
    private double option3Probability;
    private int option4Count;
    private double option4Probability;
	public int getQuestionNum() {
		return questionNum;
	}
	public void setQuestionNum(int questionNum) {
		this.questionNum = questionNum;
	}
	public int getOption1Count() {
		return option1Count;
	}
	public void setOption1Count(int option1Count) {
		this.option1Count = option1Count;
	}
	public double getOption1Probability() {
		return option1Probability;
	}
	public void setOption1Probability(double option1Probability) {
		this.option1Probability = option1Probability;
	}
	public int getOption2Count() {
		return option2Count;
	}
	public void setOption2Count(int option2Count) {
		this.option2Count = option2Count;
	}
	public double getOption2Probability() {
		return option2Probability;
	}
	public void setOption2Probability(double option2Probability) {
		this.option2Probability = option2Probability;
	}
	public int getOption3Count() {
		return option3Count;
	}
	public void setOption3Count(int option3Count) {
		this.option3Count = option3Count;
	}
	public double getOption3Probability() {
		return option3Probability;
	}
	public void setOption3Probability(double option3Probability) {
		this.option3Probability = option3Probability;
	}
	public int getOption4Count() {
		return option4Count;
	}
	public void setOption4Count(int option4Count) {
		this.option4Count = option4Count;
	}
	public double getOption4Probability() {
		return option4Probability;
	}
	public void setOption4Probability(double option4Probability) {
		this.option4Probability = option4Probability;
	}
}
