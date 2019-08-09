package com.codegym.service;

import com.codegym.model.Province;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProvinceService {
    Page<Province> findAll(Pageable pageable);
    void save(Province province);
    void remove(Long id);
    Province findByName(String name);
    Province findById(Long id);
}
