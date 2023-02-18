package com.driver.services;

import com.driver.models.Blog;
import com.driver.models.Image;
import com.driver.models.User;
import com.driver.repositories.BlogRepository;
import com.driver.repositories.ImageRepository;
import com.driver.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class BlogService {
    @Autowired
    BlogRepository blogRepository1;

    @Autowired
    UserRepository userRepository1;
    @Autowired
    ImageRepository imageRepository;

    public Blog createAndReturnBlog(Integer userId, String title, String content) {
        //create a blog at the current time
        Blog blog = new Blog();

        if(userRepository1.findById(userId).isPresent()){
            User user = userRepository1.findById(userId).get();
            blog.setTitle(title);
            blog.setContent(content);

            blog.setUser(user);
            List<Blog> blogArrayList = user.getBlogList();
            blogArrayList.add(blog);
            userRepository1.save(user);
        }

        return blog;
    }

    public void deleteBlog(int blogId){
        //delete blog and corresponding images

        if(blogRepository1.findById(blogId).isPresent()){
            Blog blog = blogRepository1.findById(blogId).get();
            List<Image> imageList = blog.getImageList();
            for(Image i: imageList){
                imageRepository.deleteById(i.getId());
            }
            blogRepository1.deleteById(blogId);
        }


    }
}
