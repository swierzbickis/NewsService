package com.newssystem.server;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.newssystem.server.domain.News;
import com.newssystem.server.repository.NewsRepository;
import org.junit.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = NewsSystemApplication.class)
@WebAppConfiguration
public class NewsSystemApplicationTests {

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();
    private static final RestTemplate restTemplate = new RestTemplate();

    @Autowired
    private NewsRepository newsRepository;

    @BeforeClass
    public static void setUpBefore() throws Exception {
        //raz przed wszystkimi testami
    }

    @AfterClass
    public static void afterTest() throws Exception {
        //po wykonaniu testow
    }

    @Before
    public void setUp() throws Exception {
        //przed kazdym testem
    }

    @After
    public void after() throws Exception {
        //po kazdym tescie
    }

    @Test
    public void testCreateNews() throws IOException {
        Map<String, String> requestBody = new HashMap<>();
        requestBody.put("title","Tytul");
        requestBody.put("text","Tresc");
        requestBody.put("data","11.11.11");
        requestBody.put("author","Lukasz");
        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<String> httpEntity =
                new HttpEntity<>(OBJECT_MAPPER.writeValueAsString(requestBody),
                        requestHeaders);

        Map<String,Object> apiResponse = restTemplate.postForObject("http://localhost:8080/api/news/saveNews",
                httpEntity,Map.class, Collections.emptyMap());

        assertNotNull(apiResponse);

        //Sprawdzanie poprawności danych

        String message = apiResponse.get("message").toString();
        assertEquals("News created successfully",message);

        String newsID = ((Map<String,Object>)apiResponse.get("news")).get("id").toString();

        assertNotNull(newsID);

        News news = newsRepository.findOne(newsID);
        assertEquals("Tytul",news.getTitle());
        assertEquals("Tresc",news.getText());
        assertEquals("11.11.11",news.getData());
        assertEquals("Lukasz",news.getAuthor());
    }

}
