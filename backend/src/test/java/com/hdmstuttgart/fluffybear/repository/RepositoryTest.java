package com.hdmstuttgart.fluffybear.repository;


import com.hdmstuttgart.fluffybear.FluffyBearTestApplication;
import com.hdmstuttgart.fluffybear.model.Recipe;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest(classes = FluffyBearTestApplication.class)
@RunWith(SpringRunner.class)
@AutoConfigureTestDatabase
@Transactional
abstract class RepositoryTest {

    protected List<Recipe> queryResult;

    @Before
    public void setUp() {
        queryResult = new ArrayList<>();
    }
}
