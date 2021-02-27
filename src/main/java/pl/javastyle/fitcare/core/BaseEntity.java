package pl.javastyle.fitcare.core;

import javax.persistence.*;
import java.time.LocalDateTime;

@MappedSuperclass
public abstract class BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime auditCd;
    private LocalDateTime auditMd;

    @PrePersist
    public void prePersist() {
        setAuditCd(LocalDateTime.now());
    }

    @PreUpdate
    public void preUpdate() {
        setAuditMd(LocalDateTime.now());
    }

    public boolean isPersisted() {
        return this.getId() != null;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getAuditCd() {
        return auditCd;
    }

    public void setAuditCd(LocalDateTime auditCd) {
        this.auditCd = auditCd;
    }

    public LocalDateTime getAuditMd() {
        return auditMd;
    }

    public void setAuditMd(LocalDateTime auditMd) {
        this.auditMd = auditMd;
    }
}
