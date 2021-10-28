package com.graduation.repo.solr;

import com.graduation.jcr.model.dto.JcrContentTreeDto;
import com.graduation.model.dto.gitee.request.AllRepoDto;
import com.graduation.repo.adapter.GiteeAdapter;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.solr.core.SolrTemplate;
import org.springframework.data.solr.core.query.Criteria;
import org.springframework.data.solr.core.query.FilterQuery;
import org.springframework.data.solr.core.query.Query;
import org.springframework.data.solr.core.query.SimpleQuery;
import org.springframework.data.solr.core.query.result.ScoredPage;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author shaoming
 * @Description 用于操作Solr的组件
 * @date 2021/10/28 13:18
 * @since 1.0
 */
@Component
public class SolrComponent {

    @Value("${solr.collection}")
    private String collection;

    @Autowired
    private SolrTemplate solrTemplate;

    public SolrComponent() {
        if (collection == null || StringUtils.isBlank(collection)) {
            collection = "collection1";
        }
    }

    /**
     * 将对象存入solr
     *
     * @param o 对象
     */
    @Async
    public void addObjectIntoSolr(Object o) {
        solrTemplate.saveBean(collection, o);
        solrTemplate.commit(collection);
    }

    public List<JcrContentTreeDto> queryByKeyWords(String repo,String keywords){
        Query query = new SimpleQuery("name:"+keywords);
        query.addFilterQuery(FilterQuery.filter(Criteria.where("repo").is(repo)));
        ScoredPage<JcrContentTreeDto> res = solrTemplate.queryForPage(collection, query, JcrContentTreeDto.class);
        return res.getContent();
    }

}