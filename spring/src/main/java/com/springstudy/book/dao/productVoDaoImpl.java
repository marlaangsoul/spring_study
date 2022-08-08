//package com.springstudy.book.dao;
//
//import javax.inject.Inject;
//
//import org.apache.ibatis.session.SqlSession;
//import org.springframework.stereotype.Repository;
//
//import com.springstudy.book.vo.cartVo;
//
//public class productVoDaoImpl implements productVoDao{
//
//	@Inject
//	private SqlSession sqlSession;
//	
//	private final String NS = "com.springstudy.book.controller.detailProduct";
//
//	public boolean findCartPro(cartVo cartVo) {
//		String result = sqlSession.selectOne(NS+".findCartPro", cartVo);
//		return Boolean.parseBoolean(result);
//	}
//
//	public void addProCartVoPost(cartVo cartVo) {
//		sqlSession.insert(NS+".addProCartVoPost", cartVo);
//	}
//
//
//}
