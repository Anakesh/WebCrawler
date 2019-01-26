package ru.webcrawler.entity;

import javax.persistence.*;

/**
 * Created by Pavel on 26.01.2019.
 */
@Entity
@Table(name = "page")
public class Page {
    @Id
    @GeneratedValue
    @Column(name = "id")
    private Integer id;
    @Column(name = "url")
    private String url;
    @Column(name = "text")
    private String text;
    @Column(name = "CRC")
    private String CRC;
    @Column(name = "indexed")
    private Boolean indexed;

    @Override
    public String toString() {
        return "Page{" +
                "id=" + id +
                ", url='" + url + '\'' +
                ", text='" + text + '\'' +
                ", CRC='" + CRC + '\'' +
                ", indexed=" + indexed +
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

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getCRC() {
        return CRC;
    }

    public void setCRC(String CRC) {
        this.CRC = CRC;
    }

    public Boolean getIndexed() {
        return indexed;
    }

    public void setIndexed(Boolean indexed) {
        this.indexed = indexed;
    }
}
