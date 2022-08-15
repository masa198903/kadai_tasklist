package models16;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@NamedQueries({  // 複数のNamedQueryをまとめたもので、NamedQueryをカンマで区切り指定
    @NamedQuery( // 主キー以外の項目などで検索し、複数件の結果を取得したい場合に定義する
        name = "getAllMessages",
        query = "SELECT m FROM Task AS m ORDER BY m.id DESC" // この SELECT文に getAllMessages という名前をつけるという記述
    )                                                                                                                                     // このJPQLでの "SELECT ｍ" は SQL での "SELECT *" のこと
})
@Table(name = "tasks")
public class Task {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)  // 主キーを自動採番する
    private Integer id;

    @Column(name = "title", length = 255, nullable = false)  // 必須入力
    private String title;

    @Column(name = "content", length = 255, nullable = false)
    private String content;

    @Column(name = "created_at", nullable = false)
    private Timestamp created_at;  // 日時型

    @Column(name = "updated_at", nullable = false)
    private Timestamp updated_at;

    // setter getter
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id;}

    public String getTitle() { return title;}
    public void setTitle(String title) { this.title = title;}

    public String getContent() { return content;}
    public void setContent(String content) { this.content = content;}

    public Timestamp getCreated_at() { return created_at;}
    public void setCreated_at(Timestamp created_at) { this.created_at = created_at; }

    public Timestamp getUpdated_at() { return updated_at; }
    public void setUpdated_at(Timestamp updated_at) { this.updated_at = updated_at; }
}