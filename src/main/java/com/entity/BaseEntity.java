package com.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

	@MappedSuperclass//để các entity kế thừa nó có thể mapping các feil như id,....
	@EntityListeners(AuditingEntityListener.class)//để sử dụng cơ chế JPAuditing hỗ trợ creatdate, modifidate...
	public abstract class BaseEntity {
		
		@Id
		//id chuc nang not null va primary key
		@GeneratedValue(strategy = GenerationType.IDENTITY)// cho gia tri id tu tang
		private Long id;
		//id ko có settre vi gia tri nó ta cho tự động tăng giảm
		
		@Column
		@CreatedBy//lấy người đang nhập thao tác dữ liệu
		private String createdBy;
		
		@Column
		@CreatedDate//lấy thời gian thao tác dữ liệu
		private Date createdDate;
		
		@Column
		@LastModifiedBy
		private String modifiedBy;
		
		@Column
		@LastModifiedDate
		private Date modifiedDate;
		
		public Long getId() {
			return id;
		}

		public String getCreatedBy() {
			return createdBy;
		}

		public void setCreatedBy(String createdBy) {
			this.createdBy = createdBy;
		}

		public Date getCreatedDate() {
			return createdDate;
		}

		public void setCreatedDate(Date createdDate) {
			this.createdDate = createdDate;
		}

		public String getModifiedBy() {
			return modifiedBy;
		}

		public void setModifiedBy(String modifiedBy) {
			this.modifiedBy = modifiedBy;
		}

		public Date getModifiedDate() {
			return modifiedDate;
		}

		public void setModifiedDate(Date modifiedDate) {
			this.modifiedDate = modifiedDate;
		}
	}