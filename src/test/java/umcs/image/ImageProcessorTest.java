package umcs.image;

import org.junit.jupiter.api.Test;

public class ImageProcessorTest {

    @Test
    public void testImageProcessor() {

        ImageProcessor imp = new ImageProcessor();

        imp.read("/home/bartosz/IdeaProjects/spring/src/thunder.png");
        imp.changeBrightness(1250);
        imp.save("/home/bartosz/IdeaProjects/spring/src/thunder.png");
        imp.changeBrightnessWithSeveralThreads(1250);

    }




}
