package pl.tgruza.webcategorizer.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;
import pl.tgruza.webcategorizer.exception.UrlNotFoundException;
import pl.tgruza.webcategorizer.model.Category;
import pl.tgruza.webcategorizer.model.Website;
import pl.tgruza.webcategorizer.service.CategoryService;
import pl.tgruza.webcategorizer.service.WebsiteService;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

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
    public RedirectView checkWebsiteInDB(@ModelAttribute Website website) throws IOException {

        if (true) {
            Set<Category> categories = sendRequestGetCategories(website.getUrl());

            for (Category category : categories) {
                categoryService.saveCategory(category);
                website.setCategories(categories);
            }

            websiteService.saveWebsite(website);

            return new RedirectView("/checkUrl/" + website.getId());
        }
        return new RedirectView("/checkUrl/" + websiteService.getWebsiteIdByUrl(website.getUrl()));
    }

    private Set<Category> sendRequestGetCategories(String url) throws IOException {
        String payload = "{\"url\":\"" + url + "\"}\n";

        URL klazify = new URL("https://www.klazify.com/api/categorize");
        HttpURLConnection con = (HttpURLConnection) klazify.openConnection();
        con.setRequestMethod("POST");

        con.setRequestProperty("Accept", "application/json");
        con.setRequestProperty("Content-Type", "application/json");
        con.setRequestProperty("Authorization", "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiJ9.eyJhdWQiOiIxIiwianRpIjoiZmY0NDc1YzY2NTE5ODQwOTI3MWRkZjEyM2E0OTFiZTViNjY1ZWU1YWM2ZmFiMGJlYWYzNDgzNWE3ODg5MmYxMWFkYmJhZGZmYTUyOWNhOTAiLCJpYXQiOjE2MjQ1NTkyNzAsIm5iZiI6MTYyNDU1OTI3MCwiZXhwIjoxNjU2MDk1MjcwLCJzdWIiOiIyNTA0Iiwic2NvcGVzIjpbXX0.OGYoq8YgrG8aN-Jn-CesWpDPcHg5MjrQ4DisDhYgbDp5BIzUnlk9FuWmNYQxOlMWIpvEMqjeV1Q3HLB3jt1naw");
        con.setRequestProperty("cache-control", "no-cache");

        con.setDoOutput(true);
        PrintWriter out = new PrintWriter(new OutputStreamWriter(con.getOutputStream()));
        out.write(payload);
        out.flush();
        out.close();

        BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuilder content = new StringBuilder();
        while ((inputLine = in.readLine()) != null) {
            content.append(inputLine);
        }

        in.close();
        con.disconnect();

        return getCategories(content.toString());
    }

    private Set<Category> getCategories(String jsonString) {
        String[] temp = jsonString.split("\"");
        Set<Category> categories = new HashSet<>();

        for (int i = 0; i < temp.length; i++) {
            if (temp[i].equals("name")) {
                categories.add(new Category(temp[i + 2].substring(2), null));
            }
        }

        return categories;
    }

    //delete Website from database
    @DeleteMapping("/checkUrl/delete")
    public RedirectView deleteWebsite(@ModelAttribute Website website) {

        websiteService.deleteWebsite(website.getId());

        return new RedirectView("/allRestaurants/{id}");
    }
}
