package org.rif.img;

/**
 * Author: Moinul Hossain
 * email: moinul.hossain@csebuet.org
 * Date: 1/3/14
 */

/**
 * A general interface fpr representing a {@link GrayScaleImage}
 */
public interface GrayScaleImage {

  public Integer[][] getPixels();
  public void setPixels(Integer[][] pixels);
  public Integer getRows();
  public void setRows(Integer rows);
  public Integer getColumns();
  public void setColumns(Integer columns);
  public Integer getMaxGrayValue();
  public void setMaxGrayValue(Integer grayValue);

}