package ru.skypro.homework.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "ads")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Ad {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "author_id", nullable = false)
    private User author;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "image_id", referencedColumnName = "id")
    private Image image;

    @Column(name = "price", nullable = false)
    private int price;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "description", nullable = false)
    private String description;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ad ad = (Ad) o;
        return price == ad.price
                && Objects.equals(id, ad.id)
                && Objects.equals(author, ad.author)
                && Objects.equals(image, ad.image)
                && Objects.equals(title, ad.title)
                && Objects.equals(description, ad.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, author, image, price, title, description);
    }

    @Override
    public String toString() {
        return "Ad{" +
                "id=" + id +
                ", author=" + author +
                ", image='" + image + '\'' +
                ", price=" + price +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
