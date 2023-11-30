package springBootMVCShopping;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig  implements WebMvcConfigurer{
	@Autowired
	InteceptorConfig inteceptorConfig;
// 여기서 로그인 하지 않아도 되는 페이지와 로그인 하ㅓ지 않으면 안되는 페이지를 지정해 주겠습니다.
	@Override
		public void addInterceptors(InterceptorRegistry registry) {
			// TODO Auto-generated method stub
			List<String> excludeList = new ArrayList<String>();//로그인 하지 않아도 되는 페이지 저장
			excludeList.add("/register/**/*");
			excludeList.add("/checkRest/**/*");
			excludeList.add("/help/**/*");
			excludeList.add("/login/**/*");
			excludeList.add("/corner/**/*");
			excludeList.add("/review/**/*");
			excludeList.add("/inquire/**/*"); // 허용된 페이지 이외에는 다 차단되었습니다.
			registry.addInterceptor(inteceptorConfig)
					.addPathPatterns("/**/*") // 모든 페이지 차단, session이 없으면 로그인페이지로
					.excludePathPatterns(excludeList);/// session이 없어도 해당 페이지그대로 사용		
		}	
}
// 여기까지 마무리 하겠습니다.