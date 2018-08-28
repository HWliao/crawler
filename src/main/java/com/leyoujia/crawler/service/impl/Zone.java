package com.leyoujia.crawler.service.impl;

import java.io.Serializable;

/**
 * 区划实体
 *
 * @author lhw
 * @date 2018/8/27
 */
public class Zone implements Serializable {
  private static final long serialVersionUID = -5778788299766145365L;

  private String pageUrl;

  /**
   * 统计用区划代码
   */
  private String code;

  /**
   * 区划级别
   */
  private String level;

  /**
   * 名称
   */
  private String name;

  private String subUrl;

  private String streetCode;

  public String getStreetCode() {
    return streetCode;
  }

  public void setStreetCode(String streetCode) {
    this.streetCode = streetCode;
  }

  public String getSubUrl() {
    return subUrl;
  }

  public void setSubUrl(String subUrl) {
    this.subUrl = subUrl;
  }

  public String getPageUrl() {
    return pageUrl;
  }

  public void setPageUrl(String pageUrl) {
    this.pageUrl = pageUrl;
  }

  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }

  public String getLevel() {
    return level;
  }

  public void setLevel(String level) {
    this.level = level;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }
}
