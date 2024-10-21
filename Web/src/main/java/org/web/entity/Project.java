package org.web.entity;

import jakarta.persistence.*;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long id;
    @Column
    private String cover;
    @Column
    private String title_ru;
    @Column
    private String title_en;
    @Column
    private String tittle_by;
    @Column
    private String subtitle;
    @Column
    private String image_type;
    @Column
    private String image_links;
    @Column
    private String category;
    @Column
    @ManyToMany(fetch = FetchType.EAGER)
    private List<Person> peoples;
    @Column
    @DateTimeFormat(pattern = "yyyy-mm-dd HH:mm:ss")
    private LocalDateTime created_at;
    @Column
    private String link;
    @Column
    private String description;
    @Column
    private String text;

    public Project() {

    }

    public Project(String cover, String title_ru, String title_en, String tittle_by, String subtitle, String image_type, String image_links, String category, List<Person> peoples, String link, String description, String text) {
        this.cover = cover;
        this.title_ru = title_ru;
        this.title_en = title_en;
        this.tittle_by = tittle_by;
        this.subtitle = subtitle;
        this.image_type = image_type;
        this.image_links = image_links;
        this.category = category;
        this.peoples = peoples;
        this.created_at = LocalDateTime.now();
        this.link = link;
        this.description = description;
        this.text = text;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public String getTitle_ru() {
        return title_ru;
    }

    public void setTitle_ru(String title_ru) {
        this.title_ru = title_ru;
    }

    public String getTitle_en() {
        return title_en;
    }

    public void setTitle_en(String title_en) {
        this.title_en = title_en;
    }

    public String getTittle_by() {
        return tittle_by;
    }

    public void setTittle_by(String tittle_by) {
        this.tittle_by = tittle_by;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public List<Person> getPeoples() {
        return peoples;
    }

    public void setPeoples(List<Person> peoples) {
        this.peoples = peoples;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public LocalDateTime getCreated_at() {
        return created_at;
    }

    public void setCreated_at(LocalDateTime created_at) {
        this.created_at = created_at;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public String getImage_type() {
        return image_type;
    }

    public void setImage_type(String image_type) {
        this.image_type = image_type;
    }

    public String getImage_links() {
        return image_links;
    }

    public void setImage_links(String image_links) {
        this.image_links = image_links;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
