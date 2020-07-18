package com.ego.search.service;

import org.apache.solr.client.solrj.SolrServerException;

import java.io.IOException;
import java.util.Map;

public interface TbItemService {

    /**
     * 数据初始化
     */
    void init() throws IOException, SolrServerException;


    /**
     * 分页查询
     * @param query
     * @param page
     * @param rows
     * @return
     */
    Map<String, Object> selByQuery(String query, int page, int rows) throws IOException, SolrServerException;


    /**
     * 新增
     * @param map
     * @param desc
     * @return
     */
    int add(Map<String, Object> map, String desc) throws IOException, SolrServerException;
}
