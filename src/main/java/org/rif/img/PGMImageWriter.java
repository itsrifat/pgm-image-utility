package org.rif.img;

import org.apache.log4j.Logger;
import org.rif.img.utils.ImageUtils;

import java.io.DataOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Author: Moinul Hossain
 * email: moinul.hossain@csebuet.org
 * Date: 1/3/14
 */

/**
 * An implementation of {@link ImageWriter} that write a {@PGMImage} to a file specified
 *
 */
public class PGMImageWriter implements ImageWriter{


  private static final Logger LOGGER = Logger.getLogger(PGMImageWriter.class);

  @Override
  public void writeImage(String imagePath, GrayScaleImage grayScaleImage){
    try{
      LOGGER.debug("Trying to write PGMImage to "+imagePath);
      PGMImage image;
      if(grayScaleImage instanceof GrayScaleImage){
        image = (PGMImage)grayScaleImage;
        FileOutputStream fileOutputStream = new FileOutputStream(imagePath);
        DataOutputStream out=new DataOutputStream(fileOutputStream);
        Integer[][] imagePixels = image.getPixels();

        out.write(ImageUtils.createHeader(image).getBytes());
        for(int i = 0 ; i<image.getRows();i++){
          for(int j = 0 ; j<image.getColumns();j++){
            Integer val =  new Integer(imagePixels[i][j]);
            out.write(val);
          }
        }
        out.close();
      }
      else{
        LOGGER.error("Could not write image to file. Image provided is not a PGMImage");
      }

    }catch (FileNotFoundException e){
      LOGGER.error(e);
    }
    catch (IOException e){
      LOGGER.error(e);
    }
  }

}
