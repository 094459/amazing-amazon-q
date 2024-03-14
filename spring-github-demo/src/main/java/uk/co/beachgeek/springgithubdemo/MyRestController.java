package uk.co.beachgeek.springgithubdemo;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**

This controller class handles REST requests related to GitHub repositories.
It contains a single endpoint /lastUpdatedRepos that retrieves the last 10
repositories updated from the GitHub API and returns them as a list of
Repository objects.

The Repository class models a GitHub repository with name and URL fields.
A RestTemplate is used to make the HTTP request to the GitHub API and
map the response to a Repository array. */


class Repository {

    private String name;
    private String url;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name; 
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}

@RestController
public class MyRestController {


    @Autowired
    private RestTemplate restTemplate;
  
    //@RequestMapping("/lastUpdatedRepos")
    @RequestMapping(value="/lastUpdatedRepos", method=RequestMethod.GET)
    public List<Repository> lastUpdatedRepos() throws IOException {

    List<Repository> repos = new ArrayList<>();

    // Make request to GitHub API
    String url = "https://api.github.com/orgs/aws-samples/repos?sort=updated&direction=desc"; 
    HttpHeaders headers = new HttpHeaders(); 
    headers.set("Accept", "application/vnd.github.v3+json"); 
    ResponseEntity<Repository[]> response = restTemplate.getForEntity(url, Repository[].class);

    // Get repositories from response
    Repository[] repositoryArray = response.getBody();

    // Add last 10 repositories to list
    for(int i=0; i<10 && i < repositoryArray.length; i++) {
        repos.add(repositoryArray[i]);
    }

    return repos;

}

}
    