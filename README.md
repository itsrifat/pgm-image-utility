pgm-image-utility
=================

Some Utility classes for reading and writing and playing with GrayScale PGM Images. Can be useful for playing with basic Image Processing techniques using PGM Images. I wrote it for doing my image processing course assignments. Just kept here for personal reference.

The module contains:

 
- ```PGMImage```: a class t represent a PGMImage
- ```PGMImageReader```: a class to read a PGM image from file
- ```PGMImageWriter```: a class to write a PGM image to a file
- ```ImageUtils```: contains some utility methods to manipulate PGM image. Includes functions are:
    - ```FFTOneD```: to calculate Fast Fourier Transform on an one dimensional image array
    - ```FFTTwoD```: to calculate Fast Fourier Transform on a two dimensional image array


Sample Usage
------------

```java
  
  ImageReader reader = new ImageReader();
  ImageWriter writer = new ImageWriter();
  GrayScaleImage image = rader.readImage("/imageDir/lenna.pgm");
  Integer[][] pixels = image.getPixels();
  Double[][] realPart = ImageUtils.toDoubleArray(pixels);
  
  Double[][] imaginaryPart = new Double[realpart.length][realpart[0].length];
  ImageUtils.initializeArrayToZero(imaginaryPart);
  
  // do two dimensional FFT
  // FFTwoD expects row and col to be power of 2 (e.g, rows=cols= 128,256,512 etc)
  ImageUtils.FFtwoD(image.getRows(),image.getColumns(),realPart,imaginaryPart,-1);
  // now realPart and imaginaryPart contains the real and imaginarypart of the transformed image.
    
  Integer[][] outputPixels = ImageUtils.toIntArray(realPart);
  
  GrayScaleImage outputImage = new PGMImage(outputPixels.length,outputPixels[0].length,
                                           image.getMaxGrayValue(),outputPixels);
  
  writer.writeImage("/tagetDir/lenna-after-fourier.pgm",outputImage);
  
  


```
