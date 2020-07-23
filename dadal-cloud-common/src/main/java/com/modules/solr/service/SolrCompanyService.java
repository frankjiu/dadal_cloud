package com.modules.solr.service;

import java.util.List;

import com.modules.solr.domain.SolrCompany;
import com.utils.PageResult;

public interface SolrCompanyService {

	public SolrCompany importAll();

	public void findAll();

	public PageResult pageQuery(String queryString, String name, String c_guard_machine, String sort, Integer page);

	public List<SolrCompany> findByName(String name);
}
