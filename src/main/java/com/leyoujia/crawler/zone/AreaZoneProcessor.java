package com.leyoujia.crawler.zone;

import org.jsoup.nodes.Document;

import java.util.List;

/**
 * @author lhw
 * @date 2018/8/27
 */
public class AreaZoneProcessor extends AbstructZonProcessor {
  public AreaZoneProcessor(FileProcessor fileProcessor) {
    super(fileProcessor);
  }

  @Override
  public boolean isValidUrl(String url) {
    return false;
  }

  @Override
  public List<String> process(Document document) {
    return null;
  }
}
