package com.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.converter.Converter;
import com.dto.NewDTO;
import com.entity.CategoryEntity;
import com.entity.NewEntity;
import com.repository.CategoryRepository;
import com.repository.NewRepository;
import com.service.iNewService;

@Service
public class NewService implements iNewService {

	@Autowired // nhúng class NewRepository vào, cơ chế dêpndency
	private NewRepository newRepository;

	@Autowired
	private CategoryRepository categoryRepository;

	@Autowired
	private Converter newConverter;

	@Override
	public NewDTO save(NewDTO newDTO) {
		NewEntity newEntity = new NewEntity();
		if (newDTO.getId() != null) {
			NewEntity oldNewEntity = newRepository.findOne(newDTO.getId());
			newEntity = newConverter.toEntity(newDTO, oldNewEntity);// chuyên từ entity cũ sang entity mới theo DTO
		} else {
			newEntity = newConverter.toEntity(newDTO);// chuyên từ đầu vào dto sang entity để luu database
		}
		CategoryEntity categoryEntity = categoryRepository.findOneByCode(newDTO.getCategoryCode());
		newEntity.setCategory(categoryEntity);
		newEntity = newRepository.save(newEntity);// lưu xuống database
		return newConverter.toDTO(newEntity);// tra dữ liệu DTO về cho giao diện
	}

	@Override
	public void delete(long[] ids) {
		for (long item : ids) {
			newRepository.delete(item);

		}
	}

	@Override
	public int totalItem() {//lấy tổng số item của bảng entity
		return (int) newRepository.count();//newRepository chứa dữ liệu bảng newEntity ta thao tác
	}

	@Override
	public List<NewDTO> findAll(Pageable pageable) {
		List<NewDTO> results = new ArrayList<>();
		List<NewEntity> entities = newRepository.findAll(pageable).getContent();
		for (NewEntity item : entities) {//kiểm tra từng phâng tử của mảng: vong lặp for rút gọn
			NewDTO newDTO = newConverter.toDTO(item);
			results.add(newDTO);
		}
		return results;
	}
	
	@Override
	public List<NewDTO> findAll() {
		List<NewDTO> results = new ArrayList<>();
		List<NewEntity> entities = newRepository.findAll();
		for (NewEntity item: entities) {
			NewDTO newDTO = newConverter.toDTO(item);
			results.add(newDTO);
		}
		return results;
	}

}
