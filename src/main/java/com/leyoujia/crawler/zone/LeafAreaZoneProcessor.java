package com.leyoujia.crawler.zone;

import org.jsoup.nodes.Document;

import java.util.List;

/**
 * @author lhw
 * @date 2018/8/27
 */
public class LeafAreaZoneProcessor extends AbstructZonProcessor {
  public LeafAreaZoneProcessor(FileProcessor fileProcessor) {
    super(fileProcessor);
  }

  @Override
  public boolean isValidUrl(String url) {
    url = url.replaceAll("http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/", "");
    return !url.contains("index") && url.split("/").length == 4;
  }

  @Override
  public List<String> process(Document document) {
    return this.process(document, "tr.towntr", this::convert1);
  }
}
