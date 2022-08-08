package com.springstudy.book.service;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springstudy.book.dao.productVoDao;
import com.springstudy.book.vo.cartVo;
import com.springstudy.book.vo.productVo;

@Service
public class productVoServiceImpl implements productVoService{
	
	@Autowired@Inject
	productVoDao dao;
	

	public productVoServiceImpl() {
		System.out.println("@Service 스프링 자동 생성");
	}
	
	@Override
	public List<productVo> list(Map<String, Object> map) {
		return dao.selectList(map);
	}
	
	@Override
	public int addProduct(productVo productVo) {
		return dao.insert(productVo);
	}
	
	@Override
	public productVo detail(productVo productVo) {
		return dao.selectDetail(productVo);
	}

	@Override
	public boolean remove(productVo productVo) {
		return dao.delete(productVo) == 1;
	}

	@Override
	public boolean edit(productVo productVo) {
		return dao.update(productVo) == 1;
	}


//	@Override
//	public List<productVo> listCriteria(Criteria cri) {
//		return dao.listCriteria(cri);
//	}
//
//	@Override
//	public int listCountCriteria(Criteria cri) {
//		return dao.countPaging(cri);
//	}

//	@Override
//	public boolean addCart(cartVo cartVo) {
//		return dao.insert(cartVo)  == 1;
//	}

	// -----
	@Override
	public boolean findCartPro(cartVo cartVo) {
		return dao.findCartPro(cartVo);
	}

	@Override
	public void addProCartVoPost(cartVo cartVo) {
		dao.addProCartVoPost(cartVo);
	}

	// ----
	
	
	@Override
	public List<productVo> proUserList(Map<String, Object> map) {
		return dao.selectList(map);
	}



//	@Override
//	public List<productVo> listSearchCriteria(CriteriaSum cri) {
//		cri.setPerPageNum(10);
//		return dao.listSearch(cri);
//	}
//
//	@Override
//	public int listSearchCount(CriteriaSum cri) {
//		return dao.listSearchCount(cri);
//	}
	
}
