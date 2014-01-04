package org.rif.img;

import org.rif.img.utils.ImageUtils;

/**
 * Author: Moinul Hossain
 * email: moinul.hossain@csebuet.org
 * Date: 1/3/14
 */
public class PGMImage implements GrayScaleImage {
  private Integer rows;
  private Integer columns;
  private Integer[][] pixels;
  private Integer maxGrayValue;
  private String magicNumber;

  public PGMImage() {

  }

  public PGMImage(Integer rows, Integer columns,Integer maxGrayValue,Integer[][] pixels) {
    this.magicNumber = "P5";
    this.rows = rows;
    this.columns = columns;
    this.pixels = pixels;
    this.maxGrayValue=maxGrayValue;
  }

  public String getMagicNumber() {
    return magicNumber;
  }

  public void setMagicNumber(String magicNumber) {
    this.magicNumber = magicNumber;
  }

  public Integer getRows() {
    return rows;
  }

  public void setRows(Integer rows) {
    this.rows = rows;
  }

  public Integer getColumns() {
    return columns;
  }

  public void setColumns(Integer columns) {
    this.columns = columns;
  }

  public Integer getMaxGrayValue() {
    return maxGrayValue;
  }

  public void setMaxGrayValue(Integer maxGrayValue) {
    this.maxGrayValue = maxGrayValue;
  }

  @Override
  public Integer[][] getPixels() {
    return this.pixels;
  }

  @Override
  public void setPixels(Integer[][] pixels) {
    this.pixels = pixels;
  }

  @Override
  public String toString() {
    StringBuffer buffer = new StringBuffer();

    buffer.append(ImageUtils.createHeader(this));

    for(int i=0;i<pixels.length;i++){
      for(int j=0;j<pixels[i].length;j++){
        buffer.append(pixels[i][j] + " ");
      }
      buffer.append("\n");
    }
    return buffer.toString();
  }
}
