package pl.tgruza.webcategorizer.service;

import org.springframework.stereotype.Service;
import pl.tgruza.webcategorizer.model.Website;
import pl.tgruza.webcategorizer.repository.WebsiteRepository;

import java.util.Optional;

@Service
public class WebsiteService {

    private final WebsiteRepository websiteRepository;

    public WebsiteService(WebsiteRepository websiteRepository) {
        this.websiteRepository = websiteRepository;
    }

    public Website getWebsiteByUrl(String url) {
        return websiteRepository.findWebsiteByUrl(url);
    }

    public Optional<Website> getWebsiteById(Long id) {
        return websiteRepository.findById(id);
    }

    public Website saveWebsite(Website website) {
        return websiteRepository.save(website);
    }

    public void deleteWebsite(Long id) {
        websiteRepository.deleteById(id);
    }

    public boolean existsByUrl(String url) { return websiteRepository.existsByUrl(url); }
}
