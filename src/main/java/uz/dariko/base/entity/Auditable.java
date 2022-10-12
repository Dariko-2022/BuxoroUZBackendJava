package uz.dariko.base.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@MappedSuperclass
@EntityListeners(EntityListeners.class)
public abstract class Auditable extends BaseEntityID {
    @Column(name = "is_deleted")
    private boolean isDeleted;

    private LocalDateTime deletedAt;
    @CreatedDate
    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    @CreatedBy
    @Column(name = "created_by", nullable = false, updatable = false)
    private Long createdBy=-1L;

    @LastModifiedDate
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @LastModifiedBy
    @Column(name = "updated_by")
    private Long updatedBy;

    public Auditable(UUID id) {
        super(id);
    }

    public Auditable(UUID id, Long createdBy) {
        super(id);
        this.createdBy = createdBy;
    }

    public Auditable(UUID id, boolean isDeleted, LocalDateTime createdAt, Long createdBy, LocalDateTime updatedAt, Long updatedBy) {
        super(id);
        this.isDeleted = isDeleted;
        this.createdAt = createdAt;
        this.createdBy = createdBy;
        this.updatedAt = updatedAt;
        this.updatedBy = updatedBy;
    }

    public Auditable(Long createdBy) {
        this.createdBy = createdBy;
    }
}