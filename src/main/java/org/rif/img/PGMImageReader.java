package org.rif.img;

import org.apache.log4j.Logger;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Scanner;

/**
 * Author: Moinul Hossain
 * email: moinul.hossain@csebuet.org
 * Date: 1/3/14
 */
public class PGMImageReader implements ImageReader {
  public static final String COMMENT_MARKER = "#";
  private static final Logger LOGGER = Logger.getLogger(PGMImageReader.class);

  @Override
  public GrayScaleImage readImage(String imagePath) {
    GrayScaleImage image = null;
    try{
      LOGGER.debug("Trying to read PGM image located at "+imagePath);
      FileInputStream fileInputStream = new FileInputStream(imagePath);
      BufferedReader d = new BufferedReader(new InputStreamReader(fileInputStream));

      //get the magic number
      String magicNumber = d.readLine();

      //discard comments if any
      String line = d.readLine();
      while (line.startsWith(COMMENT_MARKER)) {
        line = d.readLine();
      }
      Scanner s = new Scanner(line);

      // Read numfColums, numOfRows and maxGrayValue
      int columns = s.nextInt();
      int rows = s.nextInt();
      line = d.readLine();
      s = new Scanner(line);
      int maxGrayValue = s.nextInt();
      fileInputStream.close();

      //OK Now Lets try to get the pixel data
      fileInputStream = new FileInputStream(imagePath);
      DataInputStream dis = new DataInputStream(fileInputStream);

      // discard the header
      int headerSize = 4;
      while (headerSize > 0) {
        char c;
        do {
          c = (char) (dis.readUnsignedByte());
        } while (c != '\n');
        headerSize--;
      }


      Integer[][] pixels = new Integer[rows][columns];
      for (int row = 0; row < rows; row++) {
        for (int col = 0; col < columns; col++) {
          pixels[row][col] = dis.readUnsignedByte();// read the byte as an unsigned byte
        }
      }
      image = new PGMImage(rows,columns,maxGrayValue,pixels);
    }catch (Exception e){
      LOGGER.error("Could not read image "+imagePath+". Possible Explanation is below:");
      LOGGER.error(e);
    }
    return image;
  }

}
