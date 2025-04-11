package puiiiokiq.collegelask.model;

import jakarta.persistence.*;
import java.util.List;

@Entity
public class SiteInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String description;
    private String about;

    @ElementCollection
    private List<String> news;

    public SiteInfo() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getAbout() { return about; }
    public void setAbout(String about) { this.about = about; }

    public List<String> getNews() { return news; }
    public void setNews(List<String> news) { this.news = news; }
}