package com.revature.byteshare.userfeedback;

import com.revature.byteshare.User.User;
import com.revature.byteshare.recipe.Recipe;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Range;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor

@Entity
public class UserFeedback {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int ratingId;

    @ManyToOne(targetEntity= Recipe.class, cascade = CascadeType.REMOVE, optional = false)
    @JoinColumn(name="recipe_id")
    private int recipeId;

    @ManyToOne(targetEntity = User.class, cascade = CascadeType.REMOVE, optional = false)
    @JoinColumn(name="user_id")
    private int authorId;

    @Range(min = 0, max=5)
    private int rating;

    @CreatedDate
    @Temporal(TemporalType.DATE)
    private Date datePosted;

    @LastModifiedDate
    @Temporal(TemporalType.DATE)
    private Date dateUpdated;

    @Column(length=5000)
    private String commentText;

    @Override
    public String toString(){
        return "User " + this.authorId + " gave recipe " + this.rating + " stars out of 5 and said: " + this.commentText;
    }

    public UserFeedback(int authorId, String commentText){
        this.authorId = authorId;
        this.commentText = commentText;
        this.datePosted = new Date();
        this.dateUpdated = new Date();
    }
}