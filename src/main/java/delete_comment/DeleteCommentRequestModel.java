package delete_comment;

import entity.Review;

import java.util.ArrayList;

public class DeleteCommentRequestModel {

    private final ArrayList<Review> comments;

    private final String id;

    private final int access_level;

    public DeleteCommentRequestModel(ArrayList<Review> comments,
                                     String id,
                                     int access_level){
        this.comments = comments;
        this.id = id;
        this.access_level = access_level;
    }

    public ArrayList<Review> getcomments(){
        return this.comments;
    }

    public String getid(){
        return this.id;
    }

    public int getaccesslevel(){
        return this.access_level;
    }

}