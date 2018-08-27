package com.leyoujia.crawler.service.impl;

import com.leyoujia.crawler.service.CrawlService;
import edu.uci.ics.crawler4j.crawler.CrawlConfig;
import edu.uci.ics.crawler4j.crawler.CrawlController;
import edu.uci.ics.crawler4j.fetcher.PageFetcher;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtConfig;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtServer;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

/**
 * 爬虫服务
 *
 * @author lhw
 * @date 2018/8/27
 */
@Service
public class CrawlServiceImpl implements CrawlService {

  private CrawlController crawlCtrl;

  private final static int NUMBER_OF_CRAWLERS = 4;

  private final static String CRAWL_STORAGE_FOLDER = "C:\\Users\\lenovo\\Desktop\\tmp";

  private final static String ROOT_URL = "http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2017/index.html";

  @PostConstruct
  public void init() throws Exception {

    CrawlConfig config = new CrawlConfig();
    config.setCrawlStorageFolder(CRAWL_STORAGE_FOLDER);

    config.setPolitenessDelay(10);
    config.setMaxDepthOfCrawling(-1);
    config.setMaxPagesToFetch(-1);
    config.setIncludeBinaryContentInCrawling(false);

    config.setResumableCrawling(false);

    /*
     * Instantiate the controller for this crawl.
     */
    PageFetcher pageFetcher = new PageFetcher(config);
    RobotstxtConfig robotstxtConfig = new RobotstxtConfig();
    RobotstxtServer robotstxtServer = new RobotstxtServer(robotstxtConfig, pageFetcher);
    CrawlController controller = new CrawlController(config, pageFetcher, robotstxtServer);

    controller.addSeed(ROOT_URL);

    this.crawlCtrl = controller;
  }

  @Override
  public void start() {
    this.crawlCtrl.start(ZoneWebCrawler.class, NUMBER_OF_CRAWLERS);
  }

  @Override
  public void startNonBlocking() {
  }
}
