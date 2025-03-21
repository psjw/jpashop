package jpabook.jpashop.domain;


import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;

import java.time.LocalDateTime;

@MappedSuperclass //매핑정보만 받는 부모(수퍼)클래스
public class BaseEntity {

    @Column(name = "INSERT_MEMBER")
    private String createBy;
    private LocalDateTime createDate;
    @Column(name = "UPDATE_MEMBER")
    private String lasModifiedBy;
    private LocalDateTime lastModifiedDate;

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public LocalDateTime getCreateDate() {
        return createDate;
    }

    public void setCreateDate(LocalDateTime createDate) {
        this.createDate = createDate;
    }

    public String getLasModifiedBy() {
        return lasModifiedBy;
    }

    public void setLasModifiedBy(String lasModifiedBy) {
        this.lasModifiedBy = lasModifiedBy;
    }

    public LocalDateTime getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(LocalDateTime lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }
}