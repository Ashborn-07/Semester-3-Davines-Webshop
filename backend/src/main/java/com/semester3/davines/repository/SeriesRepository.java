package com.semester3.davines.repository;

import com.semester3.davines.repository.entity.SeriesEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SeriesRepository extends JpaRepository<SeriesEntity, Long> {

}
