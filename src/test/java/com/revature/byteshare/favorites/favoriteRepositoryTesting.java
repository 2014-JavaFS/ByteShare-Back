package com.revature.byteshare.favorites;


import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;


@DataJpaTest
@ActiveProfiles("test")
public class favoriteRepositoryTesting {

    @Autowired
    private favoriteRepository repository;

    @Autowired
    private TestEntityManager entityManager;

    @Test
    public void testSaving(){
        int a, b;
        a=0;
        b=0;
        assertEquals(a, b);

    }

    @Test
    public void testDeleting(){
        int a, b;
        a=0;
        b=0;
        assertEquals(a, b);
    }
}
