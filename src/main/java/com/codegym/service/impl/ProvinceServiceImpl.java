package com.codegym.service.impl;

import com.codegym.model.Province;
import com.codegym.repository.ProvinceRepository;
import com.codegym.service.ProvinceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public class ProvinceServiceImpl implements ProvinceService {

    @Autowired
    private ProvinceRepository provinceRepository;
    @Override
    public Page<Province> findAll(Pageable pageable) {
        return provinceRepository.findAll(pageable);
    }

    @Override
    public void save(Province province) {
        provinceRepository.save(province);
    }

    @Override
    public void remove(Long id) {
        provinceRepository.delete(id);
    }

    @Override
    public Province findByName(String name) {
        return provinceRepository.findByName(name);
    }

    @Override
    public Province findById(Long id) {
        return provinceRepository.findOne(id);
    }
}
