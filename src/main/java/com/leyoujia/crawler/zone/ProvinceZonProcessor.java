package com.leyoujia.crawler.zone;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
    return url != null && url.startsWith("http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2017");
  }

  @Override
  public List<String> process(Document document) {
    if (document == null) {
      return new ArrayList<>();
    }

    List<String> collect = document
      .select("tr.provincetr a")
      .stream()
      .map(Element::text)
      .peek(this::wrieLine)
      .collect(Collectors.toList());
    return new ArrayList<>();
  }
}
