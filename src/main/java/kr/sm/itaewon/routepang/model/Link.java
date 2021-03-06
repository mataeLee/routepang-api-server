package kr.sm.itaewon.routepang.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import javax.persistence.*;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@SequenceGenerator(name = "Id_Generator", sequenceName = "Id", initialValue = 1, allocationSize = 1)
public class Link {

    /**
     *  id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "Id")
    @Column(name="id")
    private long linkId;

    /**
     *  링크할 url
     */
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="link_url", length = 1000)
    private String linkUrl;

    /**
     *  아이콘
     */
    @Column(name="favicon_url", length = 1000)
    @ColumnDefault("'no favicon'")
    private String favicon;

    /**
     *  대표 이미지
     */
    @Column(name="image_url", length = 1000)
    @ColumnDefault("'no image'")
    private String image;

    /**
     *  요약 context
     */
    @Column(name="summary", length = 1000)
    @ColumnDefault("'no summary'")
    private String summary;

//    @OneToMany(mappedBy = "link", cascade = CascadeType.ALL, orphanRemoval = true)
//    @JsonIgnore
//    private List<Article> articles = new ArrayList<>();

    @CreationTimestamp
    private Timestamp regDate;

    @UpdateTimestamp
    private Timestamp updateDate;

    @Override
    public String toString() {
        return ToStringBuilder
                .reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }
}