package com.it.bananachipsbackend.bananachipsbackend.repositories;

import com.it.bananachipsbackend.bananachipsbackend.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, Long>{

}
