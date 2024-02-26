package survey.controller;

import java.net.URLEncoder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import survey.command.AnsweredCommand;
import survey.command.QuestionCommand;
import survey.command.SurveySubjectCommand;
import survey.service.QuestionService;
import survey.service.SurveyAnswerService;
import survey.service.SurveyListService;
import survey.service.SurveyQuestionService;
import survey.service.SurveyService;
import survey.service.SurveySubjectService;

@Controller
public class SurveyController {
	@Autowired
	SurveyService surveyService;
	@Autowired
	QuestionService questionService;
	@Autowired
	SurveySubjectService surveySubjectService;
	@Autowired
	SurveyQuestionService surveyQuestionService;
	@Autowired
	SurveyListService surveyListService;
	@Autowired
	SurveyAnswerService surveyAnswerService;
	
	
	@RequestMapping("surveyList")
	public String list(Model model) {
		surveyListService.execute(model);
		return "surveyList";
	}
	@GetMapping("surveyRegist")
	public String regist() {
		return "surveySubject";
	}
	@PostMapping("surveySubject")
	public String regist(SurveySubjectCommand surveySubjectCommand) {
		try {
		String encodedSurveyWriter = 
				URLEncoder.encode(surveySubjectCommand.getSurveyWriter(), "UTF-8");
		int num = surveySubjectService.execute(surveySubjectCommand);
		return "redirect:surveyQuestion?num=" + num +"&surveyWriter=" 
										  + encodedSurveyWriter;
		}catch(Exception e) {
			return "redirect:/error";
		}
	}
	@GetMapping("surveyQuestion")
	public String surveyQuestion(@RequestParam(value="num") Integer surveyNum,
								 @RequestParam(value = "surveyWriter") String surveyWriter,
			Model model) {
		surveyQuestionService.execute(surveyWriter , model , surveyNum);
		model.addAttribute("surveyNum", surveyNum);
		return "questionForm";
	}
	@PostMapping("questionRegiste")
	public String registe(QuestionCommand questionCommand  ) {
		questionService.execute(questionCommand);
		try {
		String encodedSurveyWriter = 
				URLEncoder.encode(questionCommand.getSurveyWriter(), "UTF-8");
		return "redirect:surveyQuestion?num="+ questionCommand.getSurveyNum() 
						   +"&surveyWriter=" 
				  					+ encodedSurveyWriter;
		}catch(Exception e) {
			return "redirect:/error";
		}
	}
	
	@RequestMapping(value = "survey", method = RequestMethod.GET)
	public String form(@RequestParam(value="num") Integer surveyNum,
			 @RequestParam(value = "surveyWriter") String surveyWriter,
			 Model model) {
		surveyService.execute(surveyNum, surveyWriter, model);
		return "surveyForm";
	}
	@RequestMapping(value = "survey" , method = RequestMethod.POST)
	public String submit(@ModelAttribute("ansData") AnsweredCommand data,
			Model model) {
		surveyAnswerService.execute(data, model);
		return "submitted";
	}
}
