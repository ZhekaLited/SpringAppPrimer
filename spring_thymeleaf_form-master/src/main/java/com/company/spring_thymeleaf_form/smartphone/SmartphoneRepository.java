package com.company.spring_thymeleaf_form.smartphone;

import com.company.spring_thymeleaf_form.firms.Firm;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SmartphoneRepository extends JpaRepository<Smartphone, Long> {
}


