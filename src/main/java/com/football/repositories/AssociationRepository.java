package com.football.repositories;

import com.football.entities.Association;
import com.football.projections.IAssociationProjection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AssociationRepository extends JpaRepository<Association, Long> {

    public Optional<IAssociationProjection> findAssociationById(Long associationId);

    public Page<IAssociationProjection> findAllProjectedBy(Pageable pageable);
}
