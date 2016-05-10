package com.nisum.repositories;

import com.nisum.domain.*;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReportRepository extends PagingAndSortingRepository<Report, String> {

}
