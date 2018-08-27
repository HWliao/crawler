package com.leyoujia.crawler.zone;

/**
 * @author lhw
 * @date 2018/8/27
 */
public abstract class AbstructZonProcessor implements ZoneProcessor {
  private FileProcessor fileProcessor;

  public AbstructZonProcessor(FileProcessor fileProcessor) {
    this.fileProcessor = fileProcessor;
  }

  protected void wrieLine(String line) {
    this.fileProcessor.write(line);
  }
}
