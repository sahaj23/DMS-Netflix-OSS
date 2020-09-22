package com.techprimers.cloud.model;

import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "documents")
@Data
public class DmsCreateModel {

	@Id
    @GeneratedValue
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "mimetype")
    private String mimetype;

    @Lob
    @Column(name = "pic")
    private byte[] pic;
    
    
    @Column(name="created_at")
    private Timestamp createdAt;

    public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Timestamp createdAt) {
		this.createdAt = createdAt;
	}

	public DmsCreateModel() {
    }

    public String getMimetype() {
    	return this.mimetype;
    }
    public DmsCreateModel(String name, String mimetype, byte[] pic,Timestamp createdAt) {
        this.name = name;
        this.mimetype = mimetype;
        this.pic = pic;
        this.createdAt=createdAt;
    }

	public void setPic(byte[] bytes) {
		this.pic=bytes;
	}

	public String getName() {
		return this.name;
	}

	public byte[] getPic() {
		return this.pic;
	}
}
