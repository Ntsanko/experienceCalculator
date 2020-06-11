package com.tonextlevel.repository;

import com.tonextlevel.model.History;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import javax.websocket.server.PathParam;
import java.util.List;

public interface HistoryRepository extends JpaRepository<History, Long> {

    @Query("SELECT h FROM History h WHERE h.mResultingRank = :rank")
    List<History> findByRank(@PathParam("rank") String rank);
}
