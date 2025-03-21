package ru.skypro.homework.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Objects;

@Setter
@Getter
@Entity
@Table(name = "images")
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String filePath;

    private long fileSize;

    private String mediaType;

    @OneToOne
    @JoinColumn(name = "User_id")
    private User user;

    @OneToOne
    @JoinColumn(name = "Ad_id")
    private Ad ad;

    public Image() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Image image = (Image) o;
        return id == image.id
                && fileSize == image.fileSize
                && Objects.equals(filePath, image.filePath)
                && Objects.equals(mediaType, image.mediaType)
                && Objects.equals(user, image.user)
                && Objects.equals(ad, image.ad);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, filePath, fileSize, mediaType, user, ad);
    }

    @Override
    public String toString() {
        return "Image{" +
                "id=" + id +
                ", filePath='" + filePath + '\'' +
                ", fileSize=" + fileSize +
                ", mediaType='" + mediaType + '\'' +
                ", user=" + user +
                ", ad=" + ad +
                '}';
    }
}
