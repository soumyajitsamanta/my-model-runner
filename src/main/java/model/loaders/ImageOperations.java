package model.loaders;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public class ImageOperations {
    public BufferedImage[] breakImageIntoTiles(BufferedImage image, int maxWidthSize,
            int maxHeightSize) {
        if(image==null) {
            return new BufferedImage[0];
        }
        int width = image.getWidth();
        int height = image.getHeight();

//        if(width<maxWidthSize && height<maxHeightSize) {
//            return new BufferedImage[] {image};
//        }
        List<BufferedImage> images = new ArrayList<>((1+(width/maxWidthSize))*(1+(height/maxHeightSize)));
        for(int h=0;h*maxHeightSize<height;h++) {
            for(int w=0;w*maxWidthSize<width;w++) {
                int startWidth = w*maxWidthSize;
                int startHeight = h*maxHeightSize;
                int endWidth = Math.min((w+1)*maxWidthSize, width);
                int endHeight = Math.min((h+1)*maxHeightSize, height);
                images.add(new BufferedImage(
                        endWidth-startWidth, 
                        endHeight-startHeight,
                        BufferedImage.TYPE_INT_ARGB));
            }
        }
        return images.toArray(new BufferedImage[images.size()]);
    }
}
