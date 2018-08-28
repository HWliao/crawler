package com.leyoujia.crawler.zone;

import org.jsoup.nodes.Document;

import java.util.List;

/**
 * @author lhw
 * @date 2018/8/27
 */
public class ProvinceZonProcessor extends AbstructZonProcessor {

  public ProvinceZonProcessor(FileProcessor fileProcessor) {
    super(fileProcessor);
  }

  @Override
  public boolean isValidUrl(String url) {
    return "http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2017/index.html".equals(url);
  }

  @Override
  public List<String> process(Document document) {
    return this.process(
      document,
      "tr.provincetr a",
      this::convert
    );
  }
}
