package com.leyoujia.crawler.zone;

import com.alibaba.fastjson.JSON;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 区划处理器工厂
 *
 * @author lhw
 * @date 2018/8/27
 */
public class ZoneProcessorFactory {

  private static Logger logger = LoggerFactory.getLogger(ZoneProcessorFactory.class);

  private final static String BASE_FOLDER = "C:\\Users\\lenovo\\Desktop\\tmp";
  private final static String BASE_URL = "http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2017/index.html";

  private static AtomicInteger total = new AtomicInteger(0);
  private static AtomicInteger success = new AtomicInteger(0);
  private static AtomicInteger fail = new AtomicInteger(0);
  private static List<String> failUrls = new CopyOnWriteArrayList<>();

  private static FileProcessor fileProcessor;
  private static List<ZoneProcessor> processors = new ArrayList<>();

  static {
    try {
      fileProcessor = new FileProcessor(BASE_FOLDER);
      processors.add(new ProvinceZonProcessor(fileProcessor));
      processors.add(new CityZoneProcessor(fileProcessor));
      processors.add(new AreaZoneProcessor(fileProcessor));
      processors.add(new LeafAreaZoneProcessor(fileProcessor));
      processors.add(new StreetZoneProcessor(fileProcessor));
    } catch (FileNotFoundException | UnsupportedEncodingException e) {
      e.printStackTrace();
    }
  }

  public static void start() throws FileNotFoundException, UnsupportedEncodingException {
    List<String> urls = new ArrayList<>();
    urls.add("http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2017/index.html");
    try {
      process(urls);
    } finally {
      logger.info("total:" + total.get() + ",success:" + success.get() + ",fail:" + fail.get() + ",failUls:" + JSON.toJSONString(failUrls));
    }
    fileProcessor.destroy();
  }


  private static void process(List<String> urls) {
    if (urls == null || urls.size() == 0) {
      return;
    }
    for (String url : urls) {
      logger.info(url);
      total.getAndIncrement();
      Optional<ZoneProcessor> zoneProcessor = createProcessor(url);

      try {
        if (!zoneProcessor.isPresent()) {
          throw new RuntimeException("无效url!");
        }
        Document document = Jsoup.connect(url).get();
        List<String> subUrls = zoneProcessor.get().process(document);
        success.getAndIncrement();

        process(subUrls);
      } catch (Exception e) {
        logger.error("@process:" + url, e);
        fail.getAndIncrement();
        failUrls.add(url);
      }
    }
  }

  private static Optional<ZoneProcessor> createProcessor(String url) {
    return processors.stream().filter(zoneProcessor -> zoneProcessor.isValidUrl(url)).findFirst();
  }

}


