package com.example.review.school;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface SchoolRepo extends JpaRepository<School,Long>{

}
