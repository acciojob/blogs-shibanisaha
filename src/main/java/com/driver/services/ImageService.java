package com.driver.services;

import com.driver.models.*;
import com.driver.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ImageService {

    @Autowired
    BlogRepository blogRepository2;
    @Autowired
    ImageRepository imageRepository2;

    public Image addImage(Integer blogId, String description, String dimensions){
        //add an image to the blog
        Image image = new Image();
        image.setDescription(description);
        image.setDimensions(dimensions);
        Blog blog = blogRepository2.findById(blogId).get();
        image.setBlog(blog);

        List<Image> listOfImage = blog.getImageList();
        listOfImage.add(image);
        blogRepository2.save(blog);
        return image;
    }

    public void deleteImage(Integer id){
    Image image = imageRepository2.findById(id).get();
    Blog blog = image.getBlog();
    List<Image> imageList = blog.getImageList();
    imageList.remove(imageList.indexOf(image));

        imageRepository2.deleteById(id);

    }

    public int countImagesInScreen(Integer id, String screenDimensions) {
        //Find the number of images of given dimensions that can fit in a screen having `screenDimensions`
        Image image = imageRepository2.findById(id).get();
        String size = image.getDimensions();
        int imageDimention1 = Integer.parseInt(size.substring(0,1));
        int imageDimention2 = Integer.parseInt(size.substring(2));
        int ScreenDimention1 = Integer.parseInt(screenDimensions.substring(0,1));
        int ScreenDimention2 = Integer.parseInt(screenDimensions.substring(2));
        int imageSize = imageDimention1*imageDimention2;
        int totalSize = ScreenDimention1*ScreenDimention2;

        return totalSize/imageSize;
    }
}
