package com.leyoujia.crawler.service.impl;

import com.alibaba.fastjson.JSON;
import edu.uci.ics.crawler4j.crawler.Page;
import edu.uci.ics.crawler4j.crawler.WebCrawler;
import edu.uci.ics.crawler4j.parser.HtmlParseData;
import edu.uci.ics.crawler4j.url.WebURL;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * @author lhw
 * @date 2018/8/27
 */
public class ZoneWebCrawler extends WebCrawler {

  private static final Pattern IMAGE_EXTENSIONS = Pattern.compile(".*\\.(bmp|gif|jpg|png)$");

  /**
   * You should implement this function to specify whether the given url
   * should be crawled or not (based on your crawling logic).
   */
  @Override
  public boolean shouldVisit(Page referringPage, WebURL url) {
    String href = url.getURL().toLowerCase();
    // Ignore the url if it has an extension that matches our defined set of image extensions.
    if (IMAGE_EXTENSIONS.matcher(href).matches()) {
      return false;
    }

    // Only accept the url if it is in the "www.ics.uci.edu" domain and protocol is "http".
    return href.startsWith("http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2017");
  }

  /**
   * This function is called when a page is fetched and ready to be processed
   * by your program.
   */
  @Override
  public void visit(Page page) {
    String url = page.getWebURL().getURL();
    if (page.getParseData() instanceof HtmlParseData) {
      ByteArrayInputStream in = new ByteArrayInputStream(page.getContentData());
      Document document = null;
      try {
        document = Jsoup.parse(in, page.getContentCharset(), url);
        Elements els = document.select("tr.provincetr a");
        List<String> xx = els.stream().map(Element::text).collect(Collectors.toList());
        logger.info(JSON.toJSONString(xx));
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  }
}
