/*
 * The Printer Processing Library Implemented in Processing 4.3
 * Copyright (C) 2024  MaxDragon1209 (Max Yu)
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <https://www.gnu.org/licenses/>.
*/

package maxlib.javaclass;

import java.awt.image.BufferedImage;

import processing.core.PApplet;
import processing.core.PImage;

/**
 * BufferedImage for Processing is a bridge library for using {@code BufferedImage} 
 * in Java standard library in Processing. This class provided some simple utilities
 * for converting between {@code BufferedImage} and {@code PImage}, rotating 
 * BufferedImage, etc.
 * 
 * @author Max Yu
 * @version 1.0
*/
public class BufferedImageProcessing extends PApplet{

    /**
     * Converts image in {@code PImage} to {@code BufferedImage}
     * 
     * @param src   the image to be converted
     * @return      the result image in {@code BufferedImage} format
     */
    public static BufferedImage pImageToBufferedImage(PImage src) {

        // get the size of the image
        int w = src.width;
        int h = src.height;

        // create a new BufferedImage with the size of original image
        BufferedImage result;
        result = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);

        // get the RGB pixels arrary of original image
        src.loadPixels();
        int[] argb = new int[h * w];
        for (int i = 0; i < w * h; i++){
            argb[i] = src.pixels[i];
        }

        // set the RGB pixels arrary of original image into the result 
        // BufferedImage
        result.setRGB(0, 0, w, h, argb, 0, w);

        return result;
    }

    
    /**
     * Converts image in {@code BufferedImage} to {@code PImage}
     * 
     * @param src   the image to be converted
     * @return      the result image in {@code PImage} format
     */
    public static PImage bufferedImageToPImage(BufferedImage src) {

        // get the size of the image
        int w = src.getWidth();
        int h = src.getHeight();

        // create a new BufferedImage with the size of original image
        PImage result = new PApplet().createImage(w, h, ARGB);

        // get the RGB pixels arrary of original image
        int[] argb = src.getRGB(0, 0, w, h, null, 0, w);

        // set the RGB pixels arrary of original image into the result PImage
        result.loadPixels();
        for (int i = 0; i < w * h; i++)
            result.pixels[i] = argb[i];
        result.updatePixels();

        return result;
    }

    /**
     * Rotates the image in {@code BufferedImage} by 90 degrees in either clockwise
     * or anti clockwise
     * 
     * @param src           the image to be rotated
     * @param isClockwise   {@code true} for rotate in clockwise
     *                      {@code flase} for rotate in anti-clockwise
     * @return              the result image
     */
    public static BufferedImage rotate90(BufferedImage src, boolean isClockwise) {

        // get the size of the image
        int srcH = src.getHeight();
        int srcW = src.getWidth();

        // create a new BufferedImage with the size of original image
        // which swaped the height and width
        BufferedImage result;
        result = new BufferedImage(srcH, srcW, BufferedImage.TYPE_INT_ARGB);

        // get the RGB pixels arrary of original image
        int[] srcArgb = src.getRGB(0, 0, srcW, srcH, null, 0, srcW);

        // get the RGB pixel array of original image 
        // which rotate in user-defined direction
        int[] tarArgb = new int[srcArgb.length];
        for (int i = 0; i < srcW; i++)
            for (int j = 0; j < srcH; j++)
                if (isClockwise)
                    tarArgb[i * srcH + j] = srcArgb[(srcH - j - 1) * srcW + i];
                else
                    tarArgb[i * srcH + j] = srcArgb[j * srcW + (srcW - i - 1)];

        // set the RGB pixels arrary of original image into the result BufferedImage
        result.setRGB(0, 0, srcH, srcW, tarArgb, 0, srcH);

        return result;
    }

    /**
     * Rotates the image in {@code BufferedImage} by 180 degrees
     * 
     * @param src   the image to be rotated
     * @return      the result image
     */
    private static BufferedImage rotate180(BufferedImage src) {
        
        // get the size of the image
        int srcH = src.getHeight();
        int srcW = src.getWidth();
        int[] srcArgb = src.getRGB(0, 0, srcW, srcH, null, 0, srcW);

        // create a new BufferedImage with the size of original image
        BufferedImage result;
        result = new BufferedImage(srcW, srcH, BufferedImage.TYPE_INT_ARGB);

        // get the length of RGB pixels array
        int argbSize = srcArgb.length;

        // get the RGB pixel array of original image 
        // which rotate for 180 degree
        int[] tarArgb = new int[argbSize];
        for (int i = 0; i < argbSize; i++)
            tarArgb[i] = srcArgb[argbSize - i - 1];

        // set the RGB pixels arrary of original image into the result BufferedImage
        result.setRGB(0, 0, srcW, srcH, tarArgb, 0, srcW);

        return result;
    }

}
