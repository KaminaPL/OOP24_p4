package umcs.image;

import java.awt.image.BufferedImage;

public class ImageProcessorThread implements Runnable {

    public BufferedImage image;
    public int value;
    public int height;

    public ImageProcessorThread(BufferedImage image, int value,  int height) {
        this.image = image;
        this.value = value;
        this.height = height;
    }

  /*
    @Override
    public void run(){
        try{
            long startTime = System.currentTimeMillis();

            for(int x = 0; x < image.getWidth(); x++){
          for(int y = 0; y < image.getHeight(); y++){
              int rgb = image.getRGB(x, y);
              int alpha = (rgb >> 24) & 0xff;
              int red = (rgb >> 16) & 0xff;
              int green = (rgb >> 8) & 0xff;
              int blue = rgb & 0xff;

              alpha = alpha & (value & 0xff);
              red = red & (value & 0xff);
              green = green & (value & 0xff);
              blue = blue  & (value & 0xff);


              alpha = alpha & (0xff & value);
              int newRGB = alpha << 24 | red << 16 | green << 8 | blue;
              image.setRGB(x, y, newRGB);
             }
           }

            long endTime = System.currentTimeMillis();
            long elapsedTime = endTime - startTime;

            System.out.println("Elapsed time: " + elapsedTime + " ms");
        }catch(Exception e){
            e.printStackTrace();
        }
    }

   */

    @Override
    public void run(){
        try{
            long startTime = System.currentTimeMillis();

            for(int x = 0; x < image.getWidth(); x++){
                    int rgb = image.getRGB(x, height);
                    int alpha = (rgb >> 24) & 0xff;
                    int red = (rgb >> 16) & 0xff;
                    int green = (rgb >> 8) & 0xff;
                    int blue = rgb & 0xff;

                    alpha = alpha & (value & 0xff);
                    red = red & (value & 0xff);
                    green = green & (value & 0xff);
                    blue = blue  & (value & 0xff);


                alpha = alpha & (0xff & value);
                    int newRGB = alpha << 24 | red << 16 | green << 8 | blue;
                    image.setRGB(x, height, newRGB);
            }


            long endTime = System.currentTimeMillis();
            long elapsedTime = endTime - startTime;



            System.out.println("Elapsed time: " + elapsedTime + " ms");
        }catch(Exception e){
            e.printStackTrace();
        }
    }


}
