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

  /**
   * An one dimensional Fast Fourier Transform(FFT) implementation that works on PGM Image.
   * This code is adopted from the book <b>Numerical Recipes in C</b>.
   * @param data the image data
   * @param nn number of elements in data
   * @param isign if -1 than forward FFT, if 1 than inverse FFT
   */
  public static void FFTOneD(Double data[],int nn, int isign)
  {
    int n,mmax,m,j,istep,i;
    double wtemp,wr,wpr,wpi,wi,theta;
    double tempr,tempi;

    n=nn << 1;
    j=1;
    for (i=1;i<n;i+=2) {
      if (j > i) {
        double temp = data[j];
        data[j] = data[i];
        data[i] = temp;
        temp = data[j+1];
        data[j+1] = data[i+1];
        data[i+1] = temp;
      }
      m=n >> 1;
      while (m >= 2 && j > m) {
        j -= m;
        m >>= 1;
      }
      j += m;
    }
    mmax=2;
    while (n > mmax) {
      istep=mmax << 1;
      theta=isign*(6.28318530717959/mmax);
      wtemp=Math.sin(0.5 * theta);
      wpr = -2.0*wtemp*wtemp;
      wpi=Math.sin(theta);
      wr=1.0;
      wi=0.0;
      for (m=1;m<mmax;m+=2) {
        for (i=m;i<=n;i+=istep) {
          j=i+mmax;
          tempr=wr*data[j]-wi*data[j+1];
          tempi=wr*data[j+1]+wi*data[j];
          data[j]=data[i]-tempr;
          data[j+1]=data[i+1]-tempi;
          data[i] += tempr;
          data[i+1] += tempi;
        }
        wr=(wtemp=wr)*wpr-wi*wpi+wr;
        wi=wi*wpr+wtemp*wpi+wi;
      }
      mmax=istep;
    }

  }

  /**
   *  A two dimensional Fast Fourier Transform(FFT) implementation that using the One D Transformation.
   *  For forward transformation the realPart should contain the iage data and the imaginaryPart should be initialized
   *  to zero. In case of inverse transformation the realPart will contain the transformed data (the image).
   *
   * @param M number of rows
   * @param N number of columns
   * @param realPart the real part of the image
   * @param imaginaryPart the imaginary part of the image
   * @param iSign if -1 than forward FFT, if 1 than inverse FFT
   */
  public static void FFTTwoD(Integer M, Integer N,
                             Double[][] realPart,Double[][] imaginaryPart,Integer iSign){

    for(int i=0;i<M;i++){
      Double[] row = new Double[N*2 + 1];
      initializeArrayToZero(row);
      for(int j=0,x=1;j<N;j++,x+=2){
        row[x] = realPart[i][j];
        row[x+1] = imaginaryPart[i][j];
      }
      FFTOneD(row,N,iSign);
      for(int p=1,x=0;x<row.length/2 ;p+=2,x++){
        realPart[i][x] = row[p];
        imaginaryPart[i][x] = row[p+1];
      }

    }
    for(int i=0;i<N;i++){
      Double[] row = new Double[M*2 + 1];
      initializeArrayToZero(row);
      for(int j=0,x=1;j<M;j++,x+=2){
        row[x] = realPart[j][i];
        row[x+1] = imaginaryPart[j][i];
      }
      FFTOneD(row,M,iSign);
      for(int p=1,x=0;x<row.length/2 ;p+=2,x++){
        realPart[x][i] = row[p];
        imaginaryPart[x][i] = row[p+1];
      }
    }

  }

  private static void initializeArrayToZero(Double[] arr) {
    for(int i=0;i < arr.length;i++){
      arr[i] = 0.0;
    }
  }


}
