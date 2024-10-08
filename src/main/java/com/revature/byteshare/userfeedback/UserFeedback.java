package com.revature.byteshare.userfeedback;

import com.revature.byteshare.user.User;
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
    private Recipe recipe;

    @ManyToOne(targetEntity = User.class, cascade = CascadeType.REMOVE, optional = false)
    @JoinColumn(name="user_id")
    private User user;

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

    public UserFeedback(User user, Recipe recipe, int rating, String commentText){
        this.user = user;
        this.recipe = recipe;
        this.rating = rating;
        this.commentText = commentText;
        this.datePosted = new Date();
        this.dateUpdated = new Date();
    }

    public int getRating() { return this.rating;}

    public String getCommentText() { return this.commentText;}
}