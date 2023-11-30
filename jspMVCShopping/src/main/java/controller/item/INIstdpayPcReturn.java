package controller.item;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.catalina.authenticator.DigestAuthenticator.AuthDigest;

import com.inicis.std.util.HttpUtil;
import com.inicis.std.util.ParseUtil;
import com.inicis.std.util.SignatureUtil;

import model.dao.ItemDAO;
import model.dto.AuthInfoDTO;
import model.dto.PaymentDTO;

public class INIstdpayPcReturn {
	public void execute(HttpServletRequest request) {
		// 아래 코드도 이니시스로 부터 받아온 코드입니다.
		Map<String, String> resultMap = new HashMap<String, String>();

		try{

			//#############################
			// 인증결과 파라미터 일괄 수신
			//#############################
			request.setCharacterEncoding("UTF-8");

			Map<String,String> paramMap = new Hashtable<String,String>();

			Enumeration elems = request.getParameterNames();

			String temp = "";

			while(elems.hasMoreElements())
			{
				temp = (String) elems.nextElement();
				paramMap.put(temp, request.getParameter(temp));
			}
			
			System.out.println("paramMap : "+ paramMap.toString());
			
			
			if("0000".equals(paramMap.get("resultCode"))){

				System.out.println("####인증성공/승인요청####");

				//############################################
				// 1.전문 필드 값 설정(***가맹점 개발수정***)
				//############################################
				
				String mid 		= paramMap.get("mid");
				String timestamp= SignatureUtil.getTimestamp();
				String charset 	= "UTF-8";
				String format 	= "JSON";
				String authToken= paramMap.get("authToken");
				String authUrl	= paramMap.get("authUrl");
				String netCancel= paramMap.get("netCancelUrl");	
				String merchantData = paramMap.get("merchantData");
				
				//#####################
				// 2.signature 생성
				//#####################
				Map<String, String> signParam = new HashMap<String, String>();

				signParam.put("authToken",	authToken);		// 필수
				signParam.put("timestamp",	timestamp);		// 필수

				// signature 데이터 생성 (모듈에서 자동으로 signParam을 알파벳 순으로 정렬후 NVP 방식으로 나열해 hash)
				String signature = SignatureUtil.makeSignature(signParam);


				//#####################
				// 3.API 요청 전문 생성
				//#####################
				Map<String, String> authMap = new Hashtable<String, String>();

				authMap.put("mid"			,mid);			// 필수
				authMap.put("authToken"		,authToken);	// 필수
				authMap.put("signature"		,signature);	// 필수
				authMap.put("timestamp"		,timestamp);	// 필수
				authMap.put("charset"		,charset);		// default=UTF-8
				authMap.put("format"		,format);	    // default=XML


				HttpUtil httpUtil = new HttpUtil();

				try{
					//#####################
					// 4.API 통신 시작
					//#####################

					String authResultString = "";

					authResultString = httpUtil.processHTTP(authMap, authUrl);
					
					//############################################################
					//5.API 통신결과 처리(***가맹점 개발수정***)
					//############################################################
					
					String test = authResultString.replace(",", "&").replace(":", "=")
												  .replace("\"", "").replace(" ","")
												  .replace("\n", "").replace("}", "")
												  .replace("{", "");
													
					resultMap = ParseUtil.parseStringToMap(test); //문자열을 MAP형식으로 파싱
					
					
				  // 수신결과를 파싱후 resultCode가 "0000"이면 승인성공 이외 실패

				  //throw new Exception("강제 Exception");
					// 여기에 결제 후 디비에 저장할 코드를 작성합니다.
					// resultMap에 이니시스로 부터 받은 값들이 있으므로 resultMap에서 필요한 값들을 가지고 옵니다.
					PaymentDTO dto = new PaymentDTO();
					dto.setApplDate(resultMap.get("applDate"));
					dto.setApplTime(resultMap.get("applTime"));
					dto.setCardNum(resultMap.get("CARD_Num"));
					dto.setConfirmNumber(resultMap.get("applNum"));
					dto.setPayMethod(resultMap.get("payMethod"));
					dto.setPurchaseNum(resultMap.get("MOID"));
					dto.setResultMessage(resultMap.get("resultMsg"));
					dto.setTid(resultMap.get("tid"));
					dto.setTotalPrice(resultMap.get("TotPrice"));
					dto.setPurchaseName(resultMap.get("goodsName"));
					// 결제 정보를 디비에 저장합니다.
					ItemDAO dao = new ItemDAO();
					dao.paymentInsert(dto);
					HttpSession session = request.getSession();
					AuthInfoDTO auth = (AuthInfoDTO)session.getAttribute("auth");
					request.setAttribute("userId", auth.getUserId());
					request.setAttribute("price", resultMap.get("TotPrice"));
					
				} catch (Exception ex) {

					//####################################
					// 실패시 처리(***가맹점 개발수정***)
					//####################################

					//---- db 저장 실패시 등 예외처리----//
					System.out.println(ex);

					//#####################
					// 망취소 API
					//#####################
					String netcancelResultString = httpUtil.processHTTP(authMap, netCancel);	// 망취소 요청 API url(고정, 임의 세팅 금지)

					//out.println("## 망취소 API 결과 ##");

					// 취소 결과 확인
					//out.println("<p>"+netcancelResultString.replaceAll("<", "&lt;").replaceAll(">", "&gt;")+"</p>");
				}

			}else{
			
				resultMap.put("resultCode", paramMap.get("resultCode"));
				resultMap.put("resultMsg", paramMap.get("resultMsg"));
			}
		}catch(Exception e){

			System.out.println(e);
		}
	}
}
