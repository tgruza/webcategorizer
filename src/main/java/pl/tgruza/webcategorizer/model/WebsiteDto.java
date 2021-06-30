package pl.tgruza.webcategorizer.model;

import java.util.Set;

public class WebsiteDto {
    private Set<Category> categories;

    public Set<Category> getCategories() {
        return categories;
    }

    public void setCategories(Set<Category> categories) {
        this.categories = categories;
    }
}
