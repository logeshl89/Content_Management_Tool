package com.example.fullstacktasktwo.Entiy;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;
import java.util.Base64;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class BlogPost {
    @Id
    @SequenceGenerator(name = "blogId",sequenceName = "blogId")
    @GeneratedValue(generator = "blogId",strategy = GenerationType.SEQUENCE)
    private Long id;
    private String title;
    private String body;
    private String tags;
    @Column(columnDefinition = "MEDIUMBLOB")
    @Lob
    private byte[] featuredImage;
    private String author;
    private LocalDateTime publishDate;


    @Override
    public String toString() {
        return "BlogPost{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", body='" + body + '\'' +
                ", tags=" + tags +
                ", featuredImage='" + featuredImage + '\'' +
                ", author='" + author + '\'' +
                ", publishDate=" + publishDate +
                '}';
    }
    @Transient
    private String base64Image;

    public void setBase64Image(String base64Image) {
        this.base64Image = base64Image;
    }

    public String getBase64Image() {
        return Base64.getEncoder().encodeToString(this.featuredImage);
    }
}




