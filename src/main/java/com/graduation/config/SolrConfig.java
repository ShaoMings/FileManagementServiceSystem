package com.graduation.config;

import org.apache.solr.client.solrj.SolrClient;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.solr.core.SolrTemplate;

/**
 * @author shaoming
 * @Description TODO
 * @date 2021/10/28 13:13
 * @since 1.0
 */
@Configuration
public class SolrConfig {

    @Bean
    @ConditionalOnMissingBean
    public SolrTemplate solrTemplate(SolrClient solrClient){
        return new SolrTemplate(solrClient);
    }
}
