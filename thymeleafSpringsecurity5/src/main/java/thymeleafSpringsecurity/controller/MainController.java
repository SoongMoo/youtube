package thymeleafSpringsecurity.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {
    // 홈 페이지 매핑
    @GetMapping("/")
    public String index() {
        return "thymeleaf/index";  // templates/index.html
    }
    // 로그인 페이지 매핑
    @GetMapping("/login")
    public String login() {
        return "thymeleaf/login";  // templates/login.html
    }	
    @GetMapping("/mypage")
    public String myPage(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName(); // 로그인한 사용자 ID
        
        model.addAttribute("username", username);
        return "thymeleaf/mypage";
    }
}
