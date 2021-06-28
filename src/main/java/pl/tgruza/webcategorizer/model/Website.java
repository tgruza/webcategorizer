package pl.tgruza.webcategorizer.model;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@Entity
public class Website {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String url;

    @ManyToMany
    private Set<Category> categories;

    public Website(String url, Set<Category> categories) {
        this.id = id;
        this.url = url;
        this.categories = categories;
    }

    public Website() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Set<Category> getCategories() {
        return categories;
    }

    public void setCategories(Set<Category> categories) {
        this.categories = categories;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Website website = (Website) o;
        return id.equals(website.id) && url.equals(website.url) && categories.equals(website.categories);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, url, categories);
    }
}
