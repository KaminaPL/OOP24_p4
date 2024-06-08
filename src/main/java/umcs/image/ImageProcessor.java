package umcs.image;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ImageProcessor {

    protected BufferedImage image;

    public void read(String path){
        try{
             image = ImageIO.read(new File(path));
        }catch (IOException e){
            System.out.println("Image was not loaded");
        }
    }
    public void save(String path){
        try{
            ImageIO.write(image, "png", new File(path));
        }catch(IOException e){
            System.out.println("Image was not saved");
        }
    }
    public void changeBrightness(int value){
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


              int newRGB = alpha << 24 | red << 16 | green << 8 | blue;
              image.setRGB(x, y, newRGB);
          }
      }
    }

   /* public void changeBrightnessOnMultiThreads(int value){
        int threads = Runtime.getRuntime().availableProcessors();
        ExecutorService ex = Executors.newFixedThreadPool(threads);

        for(int i = 0; i < threads; i++){
                ex.execute(new ImageProcessorThread(image, value,  i));

        }
        ex.shutdown();
    }

    */
    public void changeBrightnessWithSeveralThreads(int value){
        int threads = Runtime.getRuntime().availableProcessors();
        ExecutorService ex = Executors.newFixedThreadPool(threads);

        for(int i = 0; i < threads; i++){
            ex.execute(new ImageProcessorThread(image,value, i));
        }
        ex.shutdown();
    }



}
