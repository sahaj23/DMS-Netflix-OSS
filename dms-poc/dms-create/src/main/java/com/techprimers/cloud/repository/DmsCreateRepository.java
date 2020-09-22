package com.techprimers.cloud.repository;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.techprimers.cloud.model.DmsCreateModel;

@Transactional
public interface DmsCreateRepository extends JpaRepository<DmsCreateModel, Long> {

	DmsCreateModel findByName(String name);
}
