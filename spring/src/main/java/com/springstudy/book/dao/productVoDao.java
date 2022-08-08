package com.springstudy.book.dao;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.springstudy.book.vo.cartVo;
import com.springstudy.book.vo.productVo;

@Repository
public class productVoDao {

	@Autowired
	SqlSessionTemplate sqlSessionTemplate;

//	private final String NS = "com.springstudy.book.controller.detailProduct";

	public productVoDao() {
		System.out.println("@Repository 스프링 자동생성");
	}

	public List<productVo> selectList(Map<String, Object> map) {
		return sqlSessionTemplate.selectList("productVo.select_list", map);
	}
	public int insert(productVo productVo) {		
		return sqlSessionTemplate.insert("productVo.insert",productVo);		
	}

	public productVo selectDetail(productVo productVo) {
		return sqlSessionTemplate.selectOne("productVo.select_detail", productVo);
	}


	public int delete(productVo productVo) {
		return this.sqlSessionTemplate.delete("productVo.delete", productVo);
	}

	public int update(productVo productVo) {
		return sqlSessionTemplate.update("productVo.update", productVo);
	}

	//	내가 하는 것. 
	public int insert(cartVo cartVo){
		return this.sqlSessionTemplate.insert("cartVo.addCartSql", cartVo);	
	}

	public boolean findCartPro(cartVo cartVo) {
		String result = sqlSessionTemplate.selectOne("cartVo.findCartPro", cartVo);
		return Boolean.parseBoolean(result);
	}

	public void addProCartVoPost(cartVo cartVo) {
		sqlSessionTemplate.insert("cartVo.addProCartVoPost", cartVo);
	}







}
