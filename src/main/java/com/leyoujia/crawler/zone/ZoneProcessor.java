package com.leyoujia.crawler.zone;

import org.jsoup.nodes.Document;

import java.util.List;

/**
 * @author lhw
 * @date 2018/8/27
 */
public interface ZoneProcessor {
  /**
   * 是否为当前区域有效url
   *
   * @param url 需要判断的url
   * @return 是否
   */
  public boolean isValidUrl(String url);

  /**
   * 处理处理处理数据
   *
   * @param document html文档
   * @return 需要处理的url集合
   */
  public List<String> process(Document document);
}
