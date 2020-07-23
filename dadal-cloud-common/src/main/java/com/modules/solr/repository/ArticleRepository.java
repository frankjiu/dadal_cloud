package com.modules.solr.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.modules.solr.domain.Article;

/**
 * 持久层接口
 * 
 * @author: Frankjiu
 * @date: 2018年4月6日 下午8:00:49
 */
public interface ArticleRepository extends JpaRepository<Article, String>, JpaSpecificationExecutor<Article> {

	List<Article> findByTitleOrContent(String title, String content);

	/*@Query(value = " SELECT DISTINCT TB.ID ID, TB.PID PID, TB.MENU_NAME MENU_NAME, TB.INDEX_ORDER INDEX_ORDER, TB.UPDATE_TIME UPDATE_TIME "
			+ " FROM "
			+ " (SELECT M.ID ID, M.PID PID, M.MENU_NAME MENU_NAME, M.INDEX_ORDER INDEX_ORDER, M.UPDATE_TIME UPDATE_TIME " 
			+ " FROM   MENU M " 
			+ " where if(?1 !='', M.ID IN ?1,1=1) "
			+ " UNION "
			+ " SELECT A.ID ID, A.PID PID, A.TITLE MENU_NAME, TRUNC(A.TIMES*10000) INDEX_ORDER, A.UPDATE_TIME UPDATE_TIME " 
			+ " FROM   ARTICLE A " 
			+ " where if(?2 !='', M.ID IN ?1,1=1) "
			
			
			AND DBMS_LOB.INSTR(A.CONTENT,  '" + query.getKeyword() + "', 1, 1) > 0
			
			
			nativeQuery = true)*/
	//List<ArticleHelp> findTree(String sql, String menuDbStr, String keyword);

}
