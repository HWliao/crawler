package com.leyoujia.crawler.service;

import edu.uci.ics.crawler4j.crawler.CrawlController;

import javax.annotation.PostConstruct;

/**
 * @author lhw
 * @date 2018/8/27
 */
public interface CrawlService {

  /**
   * 启动爬虫
   */
  public void start();

  /**
   * 启动异步爬虫
   */
  public void startNonBlocking();

}
