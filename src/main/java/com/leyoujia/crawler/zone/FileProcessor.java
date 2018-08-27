package com.leyoujia.crawler.zone;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

/**
 * @author lhw
 * @date 2018/8/27
 */
public class FileProcessor {

  private PrintWriter pw;

  public FileProcessor(String folder) throws FileNotFoundException, UnsupportedEncodingException {
    String uuid = UUID.randomUUID().toString().replaceAll("-", "");
    String now = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));
    String fileName = uuid + now;
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

  public void destroy(){
    if(this.pw != null){
      this.pw.close();
    }
  }
}
