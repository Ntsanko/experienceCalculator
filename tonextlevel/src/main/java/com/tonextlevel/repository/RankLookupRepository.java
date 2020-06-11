package com.tonextlevel.repository;

import com.tonextlevel.model.RankLookup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import javax.websocket.server.PathParam;
import java.util.List;

public interface RankLookupRepository extends JpaRepository<RankLookup, Long> {

    @Query("SELECT r FROM  RankLookup r WHERE r.mTo >= :min and r.mFrom <= :max")
    public List<RankLookup> find(@PathParam("min") Long min, @PathParam("max") Long max);
}
