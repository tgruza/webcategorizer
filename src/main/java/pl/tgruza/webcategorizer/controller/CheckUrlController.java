package pl.tgruza.webcategorizer.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;
import pl.tgruza.webcategorizer.exception.UrlNotFoundException;
import pl.tgruza.webcategorizer.model.Category;
import pl.tgruza.webcategorizer.model.Website;
import pl.tgruza.webcategorizer.model.WebsiteDto;
import pl.tgruza.webcategorizer.service.CategoryService;
import pl.tgruza.webcategorizer.service.WebsiteService;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.*;

@Controller
public class CheckUrlController {

    private final WebsiteService websiteService;
    private final CategoryService categoryService;

    public CheckUrlController(WebsiteService websiteService, CategoryService categoryService) {
        this.websiteService = websiteService;
        this.categoryService = categoryService;
    }

    //View of checkUrl Page
    @GetMapping("/checkUrl")
    public String checkUrl() {
        return "/checkUrl/checkUrl";
    }

    //View of page after url checking
    @GetMapping("/checkUrl/{id}")
    public String urlChecked(@PathVariable Long id, Model model) {
        Optional<Website> website = websiteService.getWebsiteById(id);

        model.addAttribute("website", website.orElseThrow(UrlNotFoundException::new));

        return "/checkUrl/urlChecked";
    }

    //Check if website is in database, if not send request to API
    @PostMapping("/checkWebsite")
    public RedirectView checkWebsiteInDB(@ModelAttribute Website website) throws IOException, URISyntaxException, InterruptedException {

        website.setUrl(checkUrl(website.getUrl()));

        if (website.getUrl().equals("https://")) {
            return new RedirectView("/url_problem");
        }

        if (!websiteService.existsByUrl(website.getUrl())) {
            List<Category> categories = sendRequestGetCategories(website.getUrl());

            if (categories.isEmpty()) {
                return new RedirectView("/url_problem");
            }

            for (int i = 0; i < categories.size() - 1; i++) {
                if (!categoryService.categoryExistsByName(categories.get(i).getName())) {
                    categoryService.saveCategory(categories.get(i));
                    continue;
                }
                categories.set(i, categoryService.getByCategory(categories.get(i).getName()));
            }

            website.setCategories(new HashSet<>(categories));
            websiteService.saveWebsite(website);

            categories.clear();
            return new RedirectView("/checkUrl/" + website.getId());
        }

        return new RedirectView("/checkUrl/" + websiteService.getWebsiteByUrl(website.getUrl()).getId());
    }

    //Method sends request to API and returns response to formatter method
    private List<Category> sendRequestGetCategories(String url) throws IOException, URISyntaxException, InterruptedException {

        HttpClient client = HttpClient.newHttpClient();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI("https://www.klazify.com/api/categorize"))
                .header("Accept", "application/json")
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiJ9.eyJhdWQiOiIxIiwianRpIjoiZmY0NDc1YzY2NTE5ODQwOTI3MWRkZjEyM2E0OTFiZTViNjY1ZWU1YWM2ZmFiMGJlYWYzNDgzNWE3ODg5MmYxMWFkYmJhZGZmYTUyOWNhOTAiLCJpYXQiOjE2MjQ1NTkyNzAsIm5iZiI6MTYyNDU1OTI3MCwiZXhwIjoxNjU2MDk1MjcwLCJzdWIiOiIyNTA0Iiwic2NvcGVzIjpbXX0.OGYoq8YgrG8aN-Jn-CesWpDPcHg5MjrQ4DisDhYgbDp5BIzUnlk9FuWmNYQxOlMWIpvEMqjeV1Q3HLB3jt1naw")
                .header("cache-control", "no-cache")
                .POST(HttpRequest.BodyPublishers.ofString("{\"url\":\"" + url + "\"}\n"))
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        return getCategories(response.body());
    }

    //Formatter method for JSON.toString parameter. Returns set of categories
    private List<Category> getCategories(String jsonString) {
        String[] temp = jsonString.split("\"");
        List<Category> categories = new ArrayList<>();

        for (int i = 0; i < temp.length; i++) {
            if (temp[i].equals("name")) {
                categories.add(new Category(temp[i + 2]
                        .substring(2)
                        .replace("\\", "")
                        , null));
            }
        }

        return categories;
    }

    //Format url to www.example.com version
    private String checkUrl(String url) {
        url = url.toLowerCase();
        String[] s = url.split("/");

        for (String word : s) {
            if (!word.equals("") && word.contains(".")) {
                url = word;
                break;
            }
        }

        return "https://" + url;
    }

    //delete Website from database
    @DeleteMapping("/checkUrl/delete/{id}")
    public RedirectView deleteWebsite(@PathVariable Long id) {

        Optional<Website> website = websiteService.getWebsiteById(id);
        website.ifPresent(value -> websiteService.deleteWebsite(value.getId()));

        return new RedirectView("/allRestaurants/{id}");
    }

    //updating website category
    @PatchMapping("/edit_url/{id}")
    public ResponseEntity<?> partialWebsiteUpdate(
            @RequestBody WebsiteDto websiteUpdated, @PathVariable("id") Long id) {

        Optional<Website> website = websiteService.getWebsiteById(id);

        if (website.isPresent() && !websiteUpdated.getCategories().isEmpty()) {
            website.get().setCategories(websiteUpdated.getCategories());
            websiteService.saveWebsite(website.get());
        }

        return ResponseEntity.ok("website category updated");
    }



//  Request to API using HttpURLConnection
//
//        String payload = "{\"url\":\"" + url + "\"}\n";
//
//        URL klazify = new URL("https://www.klazify.com/api/categorize");
//        HttpURLConnection con = (HttpURLConnection) klazify.openConnection();
//        con.setRequestMethod("POST");
//
//        con.setRequestProperty("Accept", "application/json");
//        con.setRequestProperty("Content-Type", "application/json");
//        con.setRequestProperty("Authorization", "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiJ9.eyJhdWQiOiIxIiwianRpIjoiZmY0NDc1YzY2NTE5ODQwOTI3MWRkZjEyM2E0OTFiZTViNjY1ZWU1YWM2ZmFiMGJlYWYzNDgzNWE3ODg5MmYxMWFkYmJhZGZmYTUyOWNhOTAiLCJpYXQiOjE2MjQ1NTkyNzAsIm5iZiI6MTYyNDU1OTI3MCwiZXhwIjoxNjU2MDk1MjcwLCJzdWIiOiIyNTA0Iiwic2NvcGVzIjpbXX0.OGYoq8YgrG8aN-Jn-CesWpDPcHg5MjrQ4DisDhYgbDp5BIzUnlk9FuWmNYQxOlMWIpvEMqjeV1Q3HLB3jt1naw");
//        con.setRequestProperty("cache-control", "no-cache");
//
//        con.setDoOutput(true);
//        PrintWriter out = new PrintWriter(new OutputStreamWriter(con.getOutputStream()));
//        out.write(payload);
//        out.flush();
//        out.close();
//
//
//        BufferedReader in = new BufferedReader(
//                new InputStreamReader(con.getInputStream()));
//        String inputLine;
//        StringBuilder content = new StringBuilder();
//        while ((inputLine = in.readLine()) != null) {
//            content.append(inputLine);
//        }
//
//        in.close();
//        con.disconnect();
}
