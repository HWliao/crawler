package com.leyoujia.crawler.zone;

import com.leyoujia.crawler.service.impl.Zone;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.util.List;

/**
 * @author lhw
 * @date 2018/8/27
 */
public class CityZoneProcessor extends AbstructZonProcessor {
  public CityZoneProcessor(FileProcessor fileProcessor) {
    super(fileProcessor);
  }

  @Override
  public boolean isValidUrl(String url) {
    url = url.replaceAll("http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/", "");
    return !url.contains("index") && url.split("/").length == 2;
  }

  @Override
  public List<String> process(Document document) {
    return this.process(document, "tr.citytr", this::convert1);
  }
}
