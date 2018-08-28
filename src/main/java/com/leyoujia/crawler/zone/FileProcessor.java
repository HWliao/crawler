package com.leyoujia.crawler.zone;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.text.DecimalFormat;

/**
 * @author lhw
 * @date 2018/8/27
 */
public class FileProcessor {

  private PrintWriter pw;

  public FileProcessor(String folder) throws FileNotFoundException, UnsupportedEncodingException {
    DecimalFormat df = new DecimalFormat("#.##");
    String fileName = df.format(System.currentTimeMillis());
    File file = new File(folder + "/" + fileName);
    this.pw = new PrintWriter(file, "UTF-8");
  }

  public void write(String line) {
    if (line == null) {
      return;
    }
    this.pw.println(line);
    this.pw.flush();
  }

  public void destroy() {
    if (this.pw != null) {
      this.pw.close();
    }
  }
}
