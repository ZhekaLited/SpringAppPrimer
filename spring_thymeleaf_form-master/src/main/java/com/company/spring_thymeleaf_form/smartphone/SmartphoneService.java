package com.company.spring_thymeleaf_form.smartphone;

import com.company.spring_thymeleaf_form.firms.Firm;
import com.company.spring_thymeleaf_form.firms.FirmRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
    public class SmartphoneService {
        @Autowired
        private SmartphoneRepository smartphoneRepository;

        public List<Smartphone> findAll() {
            return smartphoneRepository.findAll();
        }

        public Smartphone save(Smartphone smartphone) {
            return smartphoneRepository.save(smartphone);
        }

        public Smartphone findById(Long id) {
            return smartphoneRepository.findById(id).get();
        }

        public void deleteById(Long id) {
            smartphoneRepository.deleteById(id);
        }
    }
