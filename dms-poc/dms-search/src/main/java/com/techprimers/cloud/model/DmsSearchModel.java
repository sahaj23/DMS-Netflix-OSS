package com.techprimers.cloud.model;

import java.sql.Timestamp;

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
public class DmsSearchModel  {

	@Id
    @GeneratedValue
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "mimetype")
    private String mimetype;

    @Column(name="created_at")
    private Timestamp createdAt;
    public Timestamp getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Timestamp createdAt) {
		this.createdAt = createdAt;
	}

	@Lob
    @Column(name = "pic")
    private byte[] pic;

    public DmsSearchModel() {
    }

    public String getMimetype() {
    	return this.mimetype;
    }
    public DmsSearchModel(String name, String mimetype, byte[] pic) {
        this.name = name;
        this.mimetype = mimetype;
        this.pic = pic;
    }

	public void setPic(byte[] bytes) {
		// TODO Auto-generated method stub
		this.pic=bytes;
	}

	public String getName() {
		// TODO Auto-generated method stub
		return this.name;
	}

	public byte[] getPic() {
		// TODO Auto-generated method stub
		return this.pic;
	}
}
