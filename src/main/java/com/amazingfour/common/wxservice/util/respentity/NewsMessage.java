package com.amazingfour.common.wxservice.util.respentity;

import java.util.List;


/**
 * 
 * <文本消息>
 * 
 * @version 
 * <pre>
 * Author	Version		Date		Changes
 * TC 	1.0  		2015年8月9日 	Created
 *
 * </pre>
 * @since 1.
 */
public class NewsMessage extends BaseMessage {  
    
    // 图文消息个数,限制为10条以内
    private int ArticleCount;  
    
    // 多条图文消息信息,默认第一个item为大图
    private List<Article> Articles;  
  
    public int getArticleCount() {  
        return ArticleCount;  
    }  
  
    public void setArticleCount(int articleCount) {  
        ArticleCount = articleCount;  
    }  
  
    public List<Article> getArticles() {  
        return Articles;  
    }  
  
    public void setArticles(List<Article> articles) {  
        Articles = articles;  
    }  
}
