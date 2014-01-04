package org.rif.img;

/**
 * Author: Moinul Hossain
 * email: moinul.hossain@csebuet.org
 * Date: 1/3/14
 */

/**
 * Interface for a {@link GrayScaleImage} writer
 */
public interface ImageWriter {
  public void writeImage(String imagePath,GrayScaleImage image);
}
