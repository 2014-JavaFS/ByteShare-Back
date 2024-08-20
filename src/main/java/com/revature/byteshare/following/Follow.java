package com.revature.byteshare.following;

import com.revature.byteshare.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "follows")
public class Follow {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int follow_id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User follower;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User following;
}
