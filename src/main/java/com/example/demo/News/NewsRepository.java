package com.example.demo.News;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NewsRepository extends JpaRepository<News, Long>  {
    @Query(value = "select * from news where username =:username", nativeQuery = true)
    public List<News> findAllByUsername(@Param("username")String username);
}
