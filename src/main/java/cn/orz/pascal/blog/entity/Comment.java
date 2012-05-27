package cn.orz.pascal.blog.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

/**
 *
 * @author hiro
 */
@Entity
public class Comment implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String Contents;
    private Long articleId;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date createdAt;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date updatedAt;

    public Comment() {
    }

    public Comment(Long id, String name, String Contents) {
        this.id = id;
        this.name = name;
        this.Contents = Contents;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContents() {
        return Contents;
    }

    public void setContents(String Contents) {
        this.Contents = Contents;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getArticleId() {
        return articleId;
    }

    public void setArticleId(Long articleId) {
        this.articleId = articleId;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Comment other = (Comment) obj;
        if (this.id != other.id
                && (this.id == null || !this.id.equals(other.id))) {
            return false;
        }
        if ((this.name == null) ? (other.name != null) : !this.name.equals(other.name)) {
            return false;
        }
        if ((this.Contents == null) ? (other.Contents != null) : !this.Contents.equals(other.Contents)) {
            return false;
        }
        if (this.createdAt != other.createdAt
                && (this.createdAt == null || !this.createdAt.equals(other.createdAt))) {
            return false;
        }
        if (this.updatedAt != other.updatedAt
                && (this.updatedAt == null || !this.updatedAt.equals(other.updatedAt))) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 43 * hash + (this.id != null ? this.id.hashCode() : 0);
        hash = 43 * hash + (this.name != null ? this.name.hashCode() : 0);
        hash = 43 * hash
                + (this.Contents != null ? this.Contents.hashCode() : 0);
        hash = 43 * hash
                + (this.createdAt != null ? this.createdAt.hashCode() : 0);
        hash = 43 * hash
                + (this.updatedAt != null ? this.updatedAt.hashCode() : 0);
        return hash;
    }

    @Override
    public String toString() {
        return "cn.orz.pascal.javaee_example.entity.Comment[ id=" + id + " ]";
    }

    @PrePersist
    protected void onCreate() {
        if (this.createdAt == null) {
            this.createdAt = new Date(System.currentTimeMillis());
        }

        if (this.updatedAt == null) {
            this.updatedAt = new Date(System.currentTimeMillis());
        }
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = new Date(System.currentTimeMillis());
    }
}
