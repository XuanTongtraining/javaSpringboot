package com.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.entity.NewEntity;

public interface NewRepository extends JpaRepository<NewEntity, Long>{
	// dang tais su dung tinhs nang cua jpaRepository
	//tham số 1: Lấy dữ liệu bảng ta thao tác lên
	//tham số 2:kiểu dữ liệu của khóa chính: id Long

}
