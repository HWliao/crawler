package com.leyoujia.crawler.zone;

import com.alibaba.fastjson.JSON;
import com.leyoujia.crawler.service.impl.Zone;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author lhw
 * @date 2018/8/27
 */
public abstract class AbstructZonProcessor implements ZoneProcessor {
  private FileProcessor fileProcessor;

  public AbstructZonProcessor(FileProcessor fileProcessor) {
    this.fileProcessor = fileProcessor;
  }

  protected void wrieLine(Object line) {
    this.fileProcessor.write(JSON.toJSONString(line));
  }

  protected List<String> process(Document document, String selector, Function<Element, Zone> map) {
    if (document == null) {
      return new ArrayList<>();
    }
    return document
      .select(selector)
      .stream()
      .map(map)
      .peek(this::wrieLine)
      .map(Zone::getSubUrl)
      .filter(url -> url != null && !"".equals(url))
      .distinct()
      .collect(Collectors.toList());
  }

  protected Zone convert(Element element) {
    String name = element.text();
    String baseUri = element.baseUri();
    String href = element.attr("href");
    String level = ZoneUtils.getLevelFromBaseUri(baseUri);
    String to = ZoneUtils.toUrl(baseUri, href);
    String code = ZoneUtils.getCode(to);
    Zone zone = new Zone();
    zone.setCode(code);
    zone.setLevel(level);
    zone.setPageUrl(baseUri);
    zone.setName(name);
    zone.setSubUrl(to);
    return zone;
  }

  protected Zone convert1(Element element) {
    Elements as = element.select("a");
    if (as.size() > 0) {
      element = as.get(1);
      String name = element.text();
      String baseUri = element.baseUri();
      String href = element.attr("href");
      String level = ZoneUtils.getLevelFromBaseUri(baseUri);
      String to = ZoneUtils.toUrl(baseUri, href);
      String code = ZoneUtils.getCode(to);
      Zone zone = new Zone();
      zone.setCode(code);
      zone.setLevel(level);
      zone.setPageUrl(baseUri);
      zone.setName(name);
      zone.setSubUrl(to);
      return zone;
    } else {
      Elements tds = element.select("td");
      String name = tds.get(1).text();
      String baseUri = element.baseUri();
      String level = ZoneUtils.getLevelFromBaseUri(baseUri);
      String code = tds.get(0).text();
      Zone zone = new Zone();
      zone.setCode(code);
      zone.setLevel(level);
      zone.setPageUrl(baseUri);
      zone.setName(name);
      return zone;
    }
  }

  protected Zone convert2(Element element) {
    Elements tds = element.select("td");
    String name = tds.get(2).text();
    String baseUri = element.baseUri();
    String level = ZoneUtils.getLevelFromBaseUri(baseUri);
    String code = ZoneUtils.getCode(tds.get(0).text());
    Zone zone = new Zone();
    zone.setCode(code);
    zone.setLevel(level);
    zone.setPageUrl(baseUri);
    zone.setName(name);
    zone.setStreetCode(tds.get(1).text());
    return zone;
  }
}
