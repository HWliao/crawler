package com.leyoujia.crawler.service.impl;

import com.alibaba.fastjson.JSON;
import com.leyoujia.crawler.auto.dao.IZoneAutoDAO;
import com.leyoujia.crawler.auto.entity.ZoneEntity;
import com.leyoujia.crawler.service.DealDataToDbService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

/**
 * @author lhw
 * @date 2018/8/28
 */
@Service
public class DealDataToDbImpl implements DealDataToDbService {

  @Resource
  private IZoneAutoDAO iZoneAutoDAO;

  @Transactional(rollbackFor = Exception.class)
  @Override
  public void dealFile(List<File> files) {
    int i = 0;
    for (File file : files) {
      try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
        String s = null;
        while ((s = reader.readLine()) != null) {
          ZoneEntity zoneEntity = JSON.parseObject(s, ZoneEntity.class);
          this.iZoneAutoDAO.insertSelective(zoneEntity);
          i++;
          System.out.println(i);
        }
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
    this.iZoneAutoDAO.countByExample().build().execute();
  }
}
