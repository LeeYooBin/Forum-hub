package com.forum_hub.infra.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.forum_hub.domain.model.Course;

public interface CourseRepository extends JpaRepository<Course, Long> {
}
