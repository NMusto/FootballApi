package com.football.repositories;

import com.football.entities.Association;
import com.football.projections.associationProjections.IAssociationProjection;
import com.football.projections.clubProjections.IClubsList;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AssociationRepository extends JpaRepository<Association, Long> {

    @Query(value = "SELECT a.id, a.name, a.president " +
            "FROM association a " +
            "where a.id = :associationId", nativeQuery = true)
    public Optional<IAssociationProjection> findAssociationById(@Param("associationId") Long associationId);

    @Query(value = "select a.id, a.name, a.president from association a", nativeQuery = true)
    public Page<IAssociationProjection> findAllProjectedBy(Pageable pageable);

    @Query(value = "SELECT c.id AS clubId, c.name AS clubName " +
            "FROM club c " +
            "WHERE c.association_id = :associationId", nativeQuery = true)
    public List<IClubsList> findAllClubs(@Param("associationId") Long associationId);

    @Modifying
    @Query(value = "update club " +
            "set association_id = null " +
            "where association_id = :associationId", nativeQuery = true)
    public void deleteAssociationIdInClubs(@Param("associationId") Long associationId);
}
