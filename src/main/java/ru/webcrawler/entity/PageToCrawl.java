package ru.webcrawler.entity;


import javax.persistence.*;

/**
 * Created by Pavel on 26.01.2019.
 */
@Entity
@Table(name = "page_to_crawl")
public class PageToCrawl {
    @Id
    @GeneratedValue
    @Column(name = "id")
    private Integer id;

    @Column(name = "url")
    private String url;

    @Override
    public String toString() {
        return "PageToCrawl{" +
                "id=" + id +
                ", url='" + url + '\'' +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
