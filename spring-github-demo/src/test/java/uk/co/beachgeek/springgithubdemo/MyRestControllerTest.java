package uk.co.beachgeek.springgithubdemo;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@WebMvcTest(MyRestController.class)
public class MyRestControllerTest {

    @Autowired
    private MyRestController controller;

    @MockBean
    private RestTemplate restTemplate;

    private Repository[] mockRepositoryArray;

    @BeforeEach
    public void setUp() {
        // Setup mock data
        mockRepositoryArray = new Repository[10];
        for (int i = 0; i < mockRepositoryArray.length; i++) {
            Repository repo = new Repository();
            repo.setName("Repo " + i);
            repo.setUrl("http://example.com/" + i);
            mockRepositoryArray[i] = repo;
        }

        // Mock restTemplate behavior
        when(restTemplate.getForEntity(anyString(), eq(Repository[].class)))
                .thenReturn(ResponseEntity.ok(mockRepositoryArray));
    }

    @Test
    public void testLastUpdatedRepos() throws Exception {
        List<Repository> repos = controller.lastUpdatedRepos();

        // Verify the size of the returned list
        assertEquals(10, repos.size(), "The size of the returned repository list should be 10");

        // Optionally, verify the content of the returned list
        for (int i = 0; i < repos.size(); i++) {
            assertEquals("Repo " + i, repos.get(i).getName(), "Repository name mismatch at index " + i);
            assertEquals("http://example.com/" + i, repos.get(i).getUrl(), "Repository URL mismatch at index " + i);
        }

        // Verify that restTemplate was called with correct parameters
        verify(restTemplate).getForEntity("https://api.github.com/orgs/aws-samples/repos?sort=updated&direction=desc", Repository[].class);
    }
}
