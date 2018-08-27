package com.leyoujia.crawler.config;


import edu.uci.ics.crawler4j.crawler.CrawlConfig;
import edu.uci.ics.crawler4j.crawler.CrawlController;
import edu.uci.ics.crawler4j.fetcher.PageFetcher;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtConfig;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtServer;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;

/**
 * @author lhw
 * @date 2018/8/27
 */
@Configurable
public class Configuration {

//  @Bean
//  public CrawlController getCrawlerCtrl() throws Exception {
//    String crawlStorageFolder = "/data/crawl/root";
//    int numberOfCrawlers = 7;
//
//    CrawlConfig config = new CrawlConfig();
//    config.setCrawlStorageFolder(crawlStorageFolder);
//
//    /*
//     * Instantiate the controller for this crawl.
//     */
//    PageFetcher pageFetcher = new PageFetcher(config);
//    RobotstxtConfig robotstxtConfig = new RobotstxtConfig();
//    RobotstxtServer robotstxtServer = new RobotstxtServer(robotstxtConfig, pageFetcher);
//    CrawlController controller = new CrawlController(config, pageFetcher, robotstxtServer);
//
//    /*
//     * For each crawl, you need to add some seed urls. These are the first
//     * URLs that are fetched and then the crawler starts following links
//     * which are found in these pages
//     */
//    controller.addSeed("http://www.ics.uci.edu/~lopes/");
//    controller.addSeed("http://www.ics.uci.edu/~welling/");
//    controller.addSeed("http://www.ics.uci.edu/");
//
//    return controller;
//  }
}
