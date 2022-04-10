package com.service;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.dto.NewDTO;

public interface iNewService {
NewDTO save(NewDTO newDTO);
void delete(long[] ids);
List<NewDTO> findAll(Pageable pageable);
List<NewDTO> findAll();
int totalItem();
}
