package com.leyoujia.crawler;

import com.alibaba.fastjson.JSON;
import com.leyoujia.crawler.service.CrawlService;
import com.leyoujia.crawler.service.DealDataToDbService;
import com.leyoujia.crawler.zone.ZoneProcessorFactory;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CrawlerApplicationTests {

  @Resource
  private CrawlService crawlService;
  @Resource
  private DealDataToDbService dealDataToDbService;

  @Test
  public void contextLoads() {
    crawlService.start();
  }

  @Test
  public void test() throws IOException {
    Connection connect = Jsoup.connect("http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2017/index.html");
    Document document = connect.get();
    Elements els = document.select("tr.provincetr a");
    List<String> xx = els.stream().map(Element::text).collect(Collectors.toList());
    System.out.println(JSON.toJSONString(xx));

  }

  @Test
  public void test1() throws FileNotFoundException, UnsupportedEncodingException {
    ZoneProcessorFactory.start();
  }

  @Test
  public void test2() throws IOException {
    ZoneProcessorFactory.dealFail1();
  }

  @Test
  public void test3() {
    ZoneProcessorFactory.dealFail2();
  }

  @Test
  public void test4() {
    List<File> files = new ArrayList<>();
    files.add(new File("C:\\Users\\lenovo\\Desktop\\省市区\\1535438137536"));
    files.add(new File("C:\\Users\\lenovo\\Desktop\\省市区\\1535443155341"));
    files.add(new File("C:\\Users\\lenovo\\Desktop\\省市区\\1535443431600"));
    this.dealDataToDbService.dealFile(files);
  }

}
