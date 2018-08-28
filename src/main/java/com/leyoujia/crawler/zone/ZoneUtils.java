package com.leyoujia.crawler.zone;

/**
 * @author lhw
 * @date 2018/8/28
 */
public class ZoneUtils {

  public static String getLevelFromBaseUri(String baseUri) {
    if (baseUri == null) {
      return "";
    }
    baseUri = baseUri.replaceAll("http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/", "");
    String[] tmps = baseUri.split("\\.");
    String path = tmps[0];
    String[] paths = path.split("/");
    if (path.contains("index")) {
      return "1";
    }
    return String.valueOf(paths.length);
  }

  public static String toUrl(String baseUri, String to) {
    if (to.startsWith("http")) {
      return to;
    }
    if (to.startsWith("/")) {
      int index = baseUri.indexOf("/", 2);
      return baseUri.substring(0, index) + to;
    }
    if (to.startsWith("./")) {
      int index = baseUri.lastIndexOf("/");
      return baseUri.substring(0, index) + to.substring(1);
    }
    return baseUri.substring(0, baseUri.lastIndexOf("/")) + "/" + to;
  }

  public static String getCode(String to) {
    String codeUri = to.substring(to.lastIndexOf("/") + 1);
    return codeUri.split("\\.")[0];
  }
}
