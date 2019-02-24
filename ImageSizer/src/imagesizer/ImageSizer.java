/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package imagesizer;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import javax.imageio.ImageIO;

/**
 *
 * @author Danin
 */
public class ImageSizer {

    /**
     * @param args the command line arguments
     */
    public static void NewSize(String inputImgPath, String outputImgPath, int newWidth, int newHeight)
            throws IOException {
        //File direc = new File("C:\\Users\\Danin\\OneDrive\\Pictures");
        // array of extensions(use a List if you prefer)
        String[] EXTENSIONS = new String[]{
            "jpg", "jpeg", "png"
        };
        // reads input image
        File inputFile = new File(inputImgPath);
        BufferedImage inputImg = ImageIO.read(inputFile);

        // creates output image
        BufferedImage outputImg = new BufferedImage(newWidth,
                newHeight, inputImg.getType());

        // scales the input image to the output image
        Graphics2D gr2d = outputImg.createGraphics();
        gr2d.drawImage(inputImg, 0, 0, newWidth, newHeight, null);
        gr2d.dispose();

        // extracts extension of output file
        String formatName = outputImgPath.substring(outputImgPath
                .lastIndexOf(".") + 1);

        // writes to output file
        ImageIO.write(outputImg, formatName, new File(outputImgPath));

    }

    public static String FolderFiles(final File fold) {
        
        //empty string
        String name = "";
        
        //for loop seperately reads image files
        for (final File fileEntry : fold.listFiles()) {
            
            //bool checks to make sure it is a directory
            if (fileEntry.isDirectory()) {
                FolderFiles(fileEntry);
            } 
            //recursively gets the path of each image
            else {
                name = name + (fileEntry.getAbsolutePath()) + "\n";
            }
        }
        return name;
    }

    public static void main(String[] args) {
        //replace with your own directory here
        final File folder = new File("C:\\Users\\Danin\\OneDrive\\Pictures\\safe sleep");
        FolderFiles(folder);
        //gets name of images in folder. eg- kitty.jpg
        String names = FolderFiles(folder);
        // names them & splits them but doesn't print
        String[] array = names.split("\n");
        String inputImgPath;
        String outputImgPath;

        //for loop to duplicate & rename images 
        for (int i = 0; i < array.length; ++i) {
            //load input into an array 
            inputImgPath  = array[i];
            //sep images 
            outputImgPath = array[i].replace(".jpg", i + ".jpg");
            //outputImgPath = array[i].replace(".png", x +".png");
            try {
                //get org width & height from images
                BufferedImage bIMG = ImageIO.read(new File(inputImgPath));
                int orgWidth  = bIMG.getWidth();
                int orgHeight = bIMG.getHeight();

                // resize to a fixed width & height
                int newWidth = 320;
                int newHeight = 240;
                ImageSizer.NewSize(inputImgPath, outputImgPath, newWidth, newHeight);
                
                //check to see if org width & height is smaller than given width & height
                // if so, prints error message & the path of said image
                if (orgWidth < newWidth && orgHeight < newHeight) {
                    System.out.println("Original image is smaller than given size.");
                    System.out.println(array[i]);
                }

                //other error handling
            } catch (IOException ex) {
                System.out.println("Error resizing the image.");
                ex.printStackTrace();

            }

        }

    }
}
