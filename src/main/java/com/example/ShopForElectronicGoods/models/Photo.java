package com.example.ShopForElectronicGoods.models;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "photo")
public class Photo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "photo_id", nullable = false)
    private Integer photo_id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "article_id", nullable = false)
    private Article article;

    @Column(name = "image_path", nullable= false)
    @Size(min = 2, max = 128)
    private String image_path;

}
