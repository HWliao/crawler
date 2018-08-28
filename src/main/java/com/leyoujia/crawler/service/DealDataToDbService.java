package com.leyoujia.crawler.service;

import java.io.File;
import java.util.List;

/**
 * @author lhw
 * @date 2018/8/28
 */
public interface DealDataToDbService {

  /**
   * 处理文件
   *
   * @param files
   */
  void dealFile(List<File> files);
}
