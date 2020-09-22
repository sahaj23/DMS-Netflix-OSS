package com.techprimers.cloud.repository;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.techprimers.cloud.model.DmsSearchModel;

@Transactional
public interface DmsSearchRepository extends JpaRepository<DmsSearchModel, Long> {

	DmsSearchModel findByName(String name);
}
