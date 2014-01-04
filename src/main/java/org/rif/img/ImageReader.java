package org.rif.img;

/**
 * Author: Moinul Hossain
 * email: moinul.hossain@csebuet.org
 * Date: 1/3/14
 */
public interface ImageReader {
  public GrayScaleImage readImage(String imagePath);
}