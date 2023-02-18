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
        Blog blog;
        try{
            blog = blogRepository2.findById(blogId).get();
        }catch (Exception e){
            return image;
        }
        image.setDescription(description);
        image.setDimensions(dimensions);

        image.setBlog(blog);

        List<Image> listOfImage = blog.getImageList();
        listOfImage.add(image);
        blogRepository2.save(blog);
        return image;
    }

    public void deleteImage(Integer id){
//    Image image = imageRepository2.findById(id).get();
//    Blog blog = image.getBlog();
//    List<Image> imageList = blog.getImageList();
//    imageList.remove(imageList.indexOf(image));

        imageRepository2.deleteById(id);

    }

    public int countImagesInScreen(Integer id, String screenDimensions) {
        //Find the number of images of given dimensions that can fit in a screen having `screenDimensions`
        Image image = imageRepository2.findById(id).get();
        String size = image.getDimensions();
        int i = 0;
        for(;i<screenDimensions.length();i++){
            if(screenDimensions.charAt(i)=='X'){
                break;
            }
        }

        int j = 0;
        for(;j<size.length();j++){
            if(size.charAt(j)=='X'){
                break;
            }
        }
        int imageDimention1 = Integer.parseInt(size.substring(0,j));
        int imageDimention2 = Integer.parseInt(size.substring(j+1));
        int ScreenDimention1 = Integer.parseInt(screenDimensions.substring(0,i));
        int ScreenDimention2 = Integer.parseInt(screenDimensions.substring(i+1));

        int dim1 = ScreenDimention1/imageDimention1;
        int dim2 = ScreenDimention2/imageDimention2;

        return dim1*dim2;
    }
}
