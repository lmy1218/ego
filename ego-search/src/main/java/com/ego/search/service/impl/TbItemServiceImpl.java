package com.ego.search.service.impl;


import com.alibaba.dubbo.config.annotation.Reference;
import com.ego.common.pojo.TbItemChild;
import com.ego.dubbo.service.TbItemCatDubboService;
import com.ego.dubbo.service.TbItemDescDubboService;
import com.ego.dubbo.service.TbItemDubboService;
import com.ego.pojo.TbItem;
import com.ego.pojo.TbItemCat;
import com.ego.pojo.TbItemDesc;
import com.ego.search.service.TbItemService;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.CloudSolrClient;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.client.solrj.response.UpdateResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class TbItemServiceImpl implements TbItemService {


    @Reference
    private TbItemDubboService tbItemDubboServiceImpl;

    @Reference
    private TbItemCatDubboService tbItemCatDubboServiceImpl;

    @Reference
    private TbItemDescDubboService tbItemDescDubboServiceImpl;

    @Resource
    private CloudSolrClient solrClient;


    public void init() throws IOException, SolrServerException {
        solrClient.deleteByQuery("*:*");
        solrClient.commit();
        //查询所有正常的商品
        List<TbItem> listItem = tbItemDubboServiceImpl.selAllByStatus((byte) 1);
        for (TbItem item : listItem){
            TbItemCat cat = tbItemCatDubboServiceImpl.selById(item.getCid());

            TbItemDesc desc = tbItemDescDubboServiceImpl.selByItemId(item.getId());
            SolrInputDocument doc = new SolrInputDocument();

            doc.setField("id", item.getId());
            doc.setField("item_title", item.getTitle());
            doc.setField("item_sell_point", item.getSellPoint());
            doc.setField("item_price", item.getPrice());
            doc.setField("item_image", item.getImage());
            doc.setField("item_category_name", cat.getName());
            doc.setField("item_desc", desc.getItemDesc());
            doc.setField("item_update", item.getUpdated());

            solrClient.add(doc);
        }

        solrClient.commit();
    }

    public Map<String, Object> selByQuery(String query, int page, int rows) throws IOException, SolrServerException {
        SolrQuery params = new SolrQuery();
        params.setStart(rows*(page-1));
        params.setRows(rows);
        params.setQuery("item_keywords:" + query);

        params.setHighlight(true);
        params.addHighlightField("item_title");
        params.setHighlightSimplePre("<span style='color:red;'>");
        params.setHighlightSimplePost("</span>");

        params.setSort("item_update", SolrQuery.ORDER.desc);
        QueryResponse response = solrClient.query(params);

        List<TbItemChild> listChild = new ArrayList<TbItemChild>();

        SolrDocumentList listSolr = response.getResults();
        Map<String, Map<String, List<String>>> hh = response.getHighlighting();
        for (SolrDocument doc : listSolr){
            TbItemChild child = new TbItemChild();
            child.setId(Long.parseLong(doc.getFieldValue("id").toString()));

            List<String> list = hh.get(doc.getFieldValue("id")).get("item_title");
            if(list!=null && list.size()>0){
                child.setTitle(list.get(0));
            }else{
                child.setTitle(doc.getFieldValue("item_title").toString());
            }
            child.setPrice((Long)doc.getFieldValue("item_price"));
            Object image = doc.getFieldValue("item_image");

            child.setImages(image==null||image.equals("")?new String[1]:image.toString().split(","));
            child.setSellPoint(doc.getFieldValue("item_sell_point").toString());


            listChild.add(child);
        }

        Map<String, Object> map = new HashMap<String, Object>();

        map.put("itemList", listChild);
        map.put("totalPages", listSolr.getNumFound()%rows==0?listSolr.getNumFound():listSolr.getNumFound()/rows +1);
        return map;
    }

    public int add(Map<String, Object> map, String desc) throws IOException, SolrServerException {
        SolrInputDocument doc = new SolrInputDocument();
        doc.setField("id", map.get("id"));
        doc.setField("item_title", map.get("title"));
        doc.setField("item_sell_point", map.get("sellPoint"));
        doc.setField("item_price", map.get("price"));
        doc.setField("item_image", map.get("image"));
        doc.setField("item_category_name", tbItemCatDubboServiceImpl.selById((Long)map.get("cid")).getName());
        doc.setField("item_desc", desc);

        UpdateResponse response = solrClient.add(doc);
        solrClient.commit();
        int status = response.getStatus();
        if(status == 0){
            return 1;
        }
        return 0;
    }
}
