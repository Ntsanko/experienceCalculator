package com.tonextlevel.service;

import com.tonextlevel.model.RankLookup;
import com.tonextlevel.repository.RankLookupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RankLookupService {

    @Autowired
    RankLookupRepository rankLookupRepository;

    public List<RankLookup> getAllRanks(){
        List<RankLookup> ranks = new ArrayList<>();

        //retrieves all ranks and adds them to the above array list
        rankLookupRepository.findAll().forEach(rank -> ranks.add(rank));
        return ranks;
    }

    public List<RankLookup> getRanksInRange(Long from, Long to){

        List<RankLookup> ranksInRange = new ArrayList<>();

        rankLookupRepository.find(from, to)
                .forEach(rank -> ranksInRange.add(rank));
        return ranksInRange;
    }
}
