package com.laundry.moving.modal.repo;

import com.laundry.moving.modal.entity.HelloEntity;
import com.laundry.moving.modal.vo.Test;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface HelloEntityRepo extends JpaRepository<HelloEntity, Integer> {
    @Query(nativeQuery = true, value = "select a.id as id, a.name as name, a.userId as userId, b.username as username from hello a, user b where a.id = ?1 and a.userId = b.id")
    public List<Object[]> queryHelloAndUser(Integer id);
}
