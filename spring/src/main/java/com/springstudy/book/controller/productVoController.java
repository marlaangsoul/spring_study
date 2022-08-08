package com.springstudy.book.controller;

import java.io.File;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.springstudy.book.service.productVoService;
import com.springstudy.book.utils.uploadFileUtils;
import com.springstudy.book.vo.cartVo;
import com.springstudy.book.vo.productVo;


@Controller
public class productVoController{

	
	@Autowired
	productVoService productVoService;

	public productVoController() {
		System.out.println("@proController 스프링 자동 생성");
	}
	@GetMapping("/")
	public String index() {
		return "index";
	}

	
	@RequestMapping(value = "/product")
	public ModelAndView list(@RequestParam Map<String, Object> map) {		
		// service
		List<productVo> list = productVoService.list(map);
		ModelAndView mav = new ModelAndView();
		mav.addObject("data", list); // request.setAttribute("data",list)
		if (map.containsKey("keyword")) {  
			mav.addObject("keyword", map.get("keyword"));  
		}
		mav.setViewName("product"); // list.jsp
		return mav;
	}
	
	@RequestMapping(value = "/userProduct")
	public ModelAndView proUserList(@RequestParam Map<String, Object> map) {		
		// service
		List<productVo> proUserList = productVoService.proUserList(map);
		ModelAndView mav = new ModelAndView();
		mav.addObject("data", proUserList); // request.setAttribute("data",list)
		if (map.containsKey("keyword")) {  
			mav.addObject("keyword", map.get("keyword"));  
		}  
		mav.setViewName("userProduct"); // list.jsp
		return mav;
	}
	
	
	
	@GetMapping("/addProduct")
	public String product() {
		return "addProduct";
	}




	@Resource(name="uploadPath")
	private String uploadPath;

//	 https://kuzuro.blogspot.com/2018/10/11.html

	@RequestMapping(value = "/addProduct", method = RequestMethod.POST)
	public String postaddPorduct(productVo productVo, MultipartFile file) throws Exception{

		System.out.println("addProduct param >>>>>>>" + productVo);
		
		String imgUploadPath = uploadPath + File.separator + "imgUpload";
		String ymdPath = uploadFileUtils.calcPath(imgUploadPath);
		String fileName = null;
		
		if(file != null) {
			fileName = uploadFileUtils.fileUpload(imgUploadPath, file.getOriginalFilename(), file.getBytes(), ymdPath); 
		} else {
			fileName = uploadPath + File.separator + "images" + File.separator + "none.jpg";
		}

		productVo.setProImg(File.separator + "imgUpload" + ymdPath + File.separator + fileName);
		productVo.setProThumbImg(File.separator + "imgUpload" + ymdPath + File.separator + "s" + File.separator + "s_" + fileName);

		productVoService.addProduct(productVo);

		return"redirect:/";
	}
	
	
	@RequestMapping(value = "/updateProduct", method = RequestMethod.GET)  
	public ModelAndView update(productVo productVo) {  
		System.out.println("param >>>" + productVo);// {bookId = 3}
		productVo detailMap = productVoService.detail(productVo);  

		ModelAndView mav = new ModelAndView();  
		mav.addObject("data", detailMap);  
		mav.setViewName("updateProduct");  
		return mav;  
	}  

	
	// 상품 수정
	@RequestMapping(value = "/updateVo", method = RequestMethod.POST)
	public ModelAndView postGoodsModify(productVo productVo, MultipartFile file, HttpServletRequest req) throws Exception {

		
		ModelAndView mav = new ModelAndView();  

		boolean isUpdateSuccess = productVoService.edit(productVo);  
		if (isUpdateSuccess) {  
			int proNum = productVo.getProNum();
			System.out.println("productId>>>" + proNum);
			mav.setViewName("redirect:/product");  
		}else {  
			int proNum = productVo.getProNum();
			System.out.println("proNum>>>" + proNum);
			mav = this.update(productVo);  
		}  

	 // 새로운 파일이 등록되었는지 확인
	 if(file.getOriginalFilename() != null && file.getOriginalFilename() != "") {
	  // 기존 파일을 삭제
	  new File(uploadPath + req.getParameter("proImg")).delete();
	  new File(uploadPath + req.getParameter("proThumbImg")).delete();
	  
	  // 새로 첨부한 파일을 등록
	  String imgUploadPath = uploadPath + File.separator + "imgUpload";
	  String ymdPath = uploadFileUtils.calcPath(imgUploadPath);
	  String fileName = uploadFileUtils.fileUpload(imgUploadPath, file.getOriginalFilename(), file.getBytes(), ymdPath);
	  
	  productVo.setProImg(File.separator + "imgUpload" + ymdPath + File.separator + fileName);
	  productVo.setProThumbImg(File.separator + "imgUpload" + ymdPath + File.separator + "s" + File.separator + "s_" + fileName);
	  
	 } else { // 새로운 파일이 등록되지 않았다면
	  // 기존 이미지를 그대로 사용
		 productVo.setProImg(req.getParameter("gdsImg"));
	 productVo.setProThumbImg(req.getParameter("gdsThumbImg"));
	  
	 }
	 
	 productVoService.edit(productVo);
	 
	 return mav;
	}
	
//	addProCartVo
//	
//	@PostMapping(value = "/addProCartVo")  
//	public ModelAndView addProCartPost(@RequestParam Map<String, String> map) {  
//
//		System.out.println(map);
//		
//		int rs = productVoService.addCart(map);
//		
//		ModelAndView mav = new ModelAndView();  
//
//		boolean isAddCartSuccess = productVoService.addCart(cartVo);  
//		
//		if (isAddCartSuccess) {  
//			mav.setViewName("");  
//		}else {  
//			int proNum = cartVo.getProNum();   
//			mav.setViewName("redirect:/detailProduct?proNum=" + proNum);  
//		}  
//
//		return mav;  
//	} 
	
//	@RequestMapping(value = "/deleteVo", method = RequestMethod.POST)  
//	public ModelAndView deletePost(productVo productVo) {  
//		ModelAndView mav = new ModelAndView();  
//
//		boolean isDeleteSuccess = productVoService.remove(productVo);  
//		if (isDeleteSuccess) {  
//			mav.setViewName("redirect:/product");  
//		}else {  
//			int proNum = productVo.getProNum();   
//			mav.setViewName("redirect:/detailProduct?proNum=" + proNum);  
//		}  
//
//		return mav;  
//	} 
	
	// insert 방식
//	@RequestMapping(value = "/addProCartVo", method = RequestMethod.POST) 
//	public ModelAndView cartPost(cartVo cartVo) {
//		// 값 받기 : @RequestParam 스프링이 자동으로 넣어줌
//		ModelAndView mav = new ModelAndView();
//		
//		int rs = productVoService.addCart(cartVo);
//		System.out.println("addProCartVo param >>>>>>>" + cartVo);
//
//		// service
//		if(rs == 1) {
//			mav.setViewName("redirect:/productUser");			
//		}else {
//			mav.setViewName("redirect:/productUser");
//		}		
//
//		return mav;
//	}
	
	//delete 방식
	
	
	
	@RequestMapping(value = "/detailProduct")
	public ModelAndView detail(productVo productVo) {		
		System.out.println("detail_product productparam >>>>>>>" + productVo);
		
		// service
		productVo detail = productVoService.detail(productVo);
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("data", detail); // request.setAttribute("data",list)
		mav.setViewName("detailProduct"); // list.jsp

		return mav;
	}
	
//	@RequestMapping(value = "/deleteVo", method = RequestMethod.POST)  
//	public ModelAndView deletePost(productVo productVo) {  
//		ModelAndView mav = new ModelAndView();  
//
//		boolean isDeleteSuccess = productVoService.remove(productVo);  
//		if (isDeleteSuccess) {  
//			mav.setViewName("redirect:/product");  
//		}else {  
//			int proNum = productVo.getProNum();   
//			mav.setViewName("redirect:/detailProduct?proNum=" + proNum);  
//		}  
//
//		return mav;  
//	} 
	
//	@RequestMapping(value = "/addProCartVoPost", method = RequestMethod.POST)  
//	public ModelAndView addProCartVoPost(cartVo cartVo, productVo productVo) {  
//		// 값 받기 : @RequestParam 스프링이 자동으로 넣어줌
//		System.out.println("vo="+ cartVo);
//		productVo = new productVo();
//		int proNum = productVo.getProNum();
//		cartVo.setProNum(proNum);
//		int rs = productVoService.addCart(cartVo);
//		ModelAndView mav = new ModelAndView(); 
//		if (rs == 1) {  
//			System.out.println("장바구니 추가 완료");
//			mav.setViewName("redirect:/detailProduct"); 
//			
//		}else {  
//			System.out.println("장바구니 추가 실패!");
//			mav.setViewName("redirect:/detailProduct");
//		}  
//		return mav;  
//	} 
//	
//	@RequestMapping(value = "/addProCartVoPost", method = RequestMethod.POST)  
//	public ModelAndView addProCartVoPost(cartVo cartVo, productVo productVo) {  
//		
//		ModelAndView mav = new ModelAndView();  
//    	boolean isAddSuccess = productVoService.addCart(cartVo);  
//		
//    	int proNum;
//		cartVo.setProNum(proNum);
//    	
//    	
//		if (isAddSuccess) {  
//			mav.setViewName("redirect:/detailProduct");  
//		}else {  
//			int bookId = cartVo.getProNum();   
//			mav.setViewName("redirect:/detailProduct"); 
//		}  
//		return mav;  
//	} 
	
	@RequestMapping(value = "/addProCartVoPost", method = RequestMethod.POST)
	public @ResponseBody String addProCartVoPost(@PathVariable("proNum") int proNum, HttpSession session) {
		cartVo cartVo = new cartVo();
		System.out.println("addProCartVoPost productparam >>>>>>>" + cartVo);
		System.out.println();
//		MemberVo vo = (MemberVo) session.getAttribute("login");
//		String memId = vo.getMemId();
		/* 로그인 되어있는 정보를 이용해서 userid를 가져온다 */
		/* cart객체를 생성하고*/
		cartVo.setMemId("lbh");
		cartVo.setProNum(proNum);
		/* 객체 안에 userid와 productId를 set해준다 */

		boolean istAlreadyExisted = productVoService.findCartPro(cartVo);
        /* 이미 해당 상품이 카트에 존재하는지 여부를 판별해주는 메서드 */
		System.out.println("istAlreadyExisted : " + istAlreadyExisted);
		
		if (istAlreadyExisted) {
			return "already_existed";
            /* 만약 이미 카트에 저장되어있다면, already_existed를 리턴 */
		} else {
			productVoService.addProCartVoPost(cartVo);
			return "add_success";
             /* 카트에 없으면 카트에 저장 후, add_success를 리턴  */
		}
	}
	
	
	
}
