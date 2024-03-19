package controller.goods;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import model.dao.EmployeeDAO;
import model.dao.GoodsDAO;
import model.dto.AuthInfoDTO;
import model.dto.GoodsDTO;

public class GoodsWriteService {
	public void execute(HttpServletRequest request) 
			throws UnsupportedEncodingException{
		request.setCharacterEncoding("utf-8");
		
		int fileSize = 1024 * 1024 * 100;
		String realPath 
		// webapp다음 부터의 경로를 가지고 온다.
			= request.getServletContext().getRealPath("/goods/images");
		System.out.println(realPath); // 배포폴더 경로가 된다.
		
		try {
			// 파일저장
			MultipartRequest multi = new MultipartRequest(request, realPath, fileSize,
						"utf-8", new DefaultFileRenamePolicy());
			
			String goodsNum = multi.getParameter("goodsNum");
			String goodsName = multi.getParameter("goodsName");
			String goodsPrice = multi.getParameter("goodsPrice");
			String goodscontent = multi.getParameter("goodsContent");
			String deliveryCost = multi.getParameter("deliveryCost");
			 			
			// 파일명만 받아옴
			String mainImageStoreName = multi.getFilesystemName("mainImage");
			String mainOriginalName = multi.getOriginalFileName("mainImage");
			                        // 저장된 파일명을 가져올 때
			String image1StoreName = multi.getFilesystemName("image1");
			                        // 전송된 파일명을 가져올 때
			String image1OriginalName = multi.getOriginalFileName("image1");
			
			String image2StoreName = multi.getFilesystemName("image2");
			String image2OriginalName = multi.getOriginalFileName("image2");
			String image3StoreName = multi.getFilesystemName("image3");
			String image3OriginalName = multi.getOriginalFileName("image3");
			
			String goodsImages = image1StoreName + "`"  // 문자열 붙이기
			                   + image2StoreName + "`"
			                   + image3StoreName;
			String goodsImagesImg = image1OriginalName + "`"
					              + image2OriginalName + "`"
					              + image3OriginalName;
			/*
			System.out.println(goodsImages);
			// 과정 안내서.hwp`sqljdbc4.jar`jakarta-taglibs-standard-1.1.2.zip
			String name[] = goodsImages.split("`");
			System.out.println(name[0]);
			System.out.println(name[1]);
			System.out.println(name[2]);
			*/
			
			GoodsDTO dto = new GoodsDTO();
			dto.setDeliveryCost(Integer.parseInt(deliveryCost));
			dto.setGoodsContent(goodscontent);
			dto.setGoodsName(goodsName);
			dto.setGoodsNum(goodsNum);
			dto.setGoodsPrice(Integer.parseInt(goodsPrice));
			dto.setGoodsMainStore(mainImageStoreName); //  파일명만 저장
			dto.setGoodsMainStoreImg(mainOriginalName);
			dto.setGoodsImages(goodsImages);
			dto.setGoodsImagesImg(goodsImagesImg);
			
			// empNum을 가져오기 위한 코드 시작
			HttpSession session = request.getSession();
			AuthInfoDTO auth = (AuthInfoDTO)session.getAttribute("auth");
			EmployeeDAO empDao = new EmployeeDAO();
			String empNum = empDao.getEmpNum(auth.getUserId());
			// empNum을 가져오기 위한 코드 끝
			dto.setEmpNum(empNum);		
			
			GoodsDAO dao = new GoodsDAO();		
			dao.goodsInsert(dto);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
		
	}
}
