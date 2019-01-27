package com.tianjian.data.service;

import com.tianjian.data.bean.RealtionFile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RealtionFileDao extends JpaRepository<RealtionFile, String> {
    List<RealtionFile> findByRealtionId(String relationId);
}
