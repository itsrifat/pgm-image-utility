package org.rif.img.utils;

import org.rif.img.PGMImage;

import java.util.Date;

/**
 * Author: Moinul Hossain
 * email: moinul.hossain@csebuet.org
 * Date: 1/3/14
 */
public class ImageUtils {
  public static final String DEFAULT_COMMENT= "#Created on "+new Date().toString();
  private static final String NEW_LINE = "\n";

  public static String createHeader(PGMImage image){
    StringBuffer buffer = new StringBuffer();
    buffer.append(image.getMagicNumber());
    buffer.append(NEW_LINE);
    buffer.append(DEFAULT_COMMENT);
    buffer.append(NEW_LINE);
    buffer.append(image.getColumns());
    buffer.append(" ");
    buffer.append(image.getRows());
    buffer.append(NEW_LINE);
    buffer.append(image.getMaxGrayValue());
    buffer.append(NEW_LINE);


    return buffer.toString();
  }
}
