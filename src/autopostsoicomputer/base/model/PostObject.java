package autopostsoicomputer.base.model;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Admin
 */
public class PostObject {

    public String postTitle;
    public String postCategory;
    public String postContent;
    public String postImageFeature;

    public PostObject(String postTitle, String postCategory, String postContent, String postImageFeature) {
        this.postTitle = postTitle;
        this.postCategory = postCategory;
        this.postContent = postContent;
        this.postImageFeature = postImageFeature;
    }

    public PostObject() {
    }

    public String getPostTitle() {
        return postTitle;
    }

    public void setPostTitle(String postTitle) {
        this.postTitle = postTitle;
    }

    public String getPostCategory() {
        return postCategory;
    }

    public void setPostCategory(String postCategory) {
        this.postCategory = postCategory;
    }

    public String getPostContent() {
        return postContent;
    }

    public void setPostContent(String postContent) {
        this.postContent = postContent;
    }

    public String getPostImageFeature() {
        return postImageFeature;
    }

    public void setPostImageFeature(String postImageFeature) {
        this.postImageFeature = postImageFeature;
    }

}
