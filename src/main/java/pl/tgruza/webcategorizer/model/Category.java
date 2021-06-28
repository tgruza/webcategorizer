package pl.tgruza.webcategorizer.model;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@Entity
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;


    @ManyToMany(cascade = { CascadeType.ALL })
    @JoinTable(
            name = "category_website",
            joinColumns = { @JoinColumn(name = "category_id") },
            inverseJoinColumns = { @JoinColumn(name = "website_id") }
    )
    private Set<Website> websites;

    public Category() {
    }

    public Category(String name, Set<Website> websites) {
        this.id = id;
        this.name = name;
        this.websites = websites;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

//    public String getCategory() {
//        return category;
//    }
//
//    public void setCategory(String category) {
//        this.category = category;
//    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Website> getWebsites() {
        return websites;
    }

    public void setWebsites(Set<Website> websites) {
        this.websites = websites;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Category category1 = (Category) o;
        return id.equals(category1.id) && name.equals(category1.name) && Objects.equals(websites, category1.websites);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, websites);
    }
}
