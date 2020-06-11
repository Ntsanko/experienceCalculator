package com.tonextlevel.service;

import com.tonextlevel.model.History;
import com.tonextlevel.model.RankLookup;
import com.tonextlevel.repository.HistoryRepository;
import com.tonextlevel.util.ReportGenerator;
import com.tonextlevel.util.ReportRequest;
import com.tonextlevel.util.RequestBody;
import org.apache.commons.lang3.Range;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.util.List;

@Service
public class HistoryService {

    private static final Logger logger = LoggerFactory.getLogger(History.class);

    @Autowired
    HistoryRepository historyRepository;

    @Autowired
    RankLookupService rankLookupService;

    public List<History> findAll(){
        return historyRepository.findAll();
    }

    public String calculateXP(RequestBody body){

        long neededXp = 0;
        String lastRank = "";

        if((body.getFromLevel() < 1 || body.getToLevel() > 120)){

            logger.warn("User entered a number that is outside the desire level range.");

            return "One of your levels is outside the allowed range of 1-120";
        }

        if(body.getFromLevel() > body.getToLevel()){
            return "Your minimum level cannot be greater than your maximum level.";
        }
        long min = body.getFromLevel();
        long max = body.getToLevel();

        List<RankLookup> neededRanks = rankLookupService.getRanksInRange(body.getFromLevel(), body.getToLevel());
        for(long i = min; i <= max; i++){
            //loops through rank list looking for appropriate xp amount to increment
            for(RankLookup rank: neededRanks){
                Range<Long> myRange =   Range.between(rank.getMFrom(), rank.getMTo());
                if(myRange.contains(i)){
                    neededXp += rank.getMXpPerLevel();
                    lastRank = rank.getMName();
                    break;
                }
            }
        }

        History history = new History(min, max, neededXp, lastRank);

        //persists the data to history table
        historyRepository.save(history);
        return "You will need " + neededXp + " to gain your desired level.";
    }


    public ResponseEntity<InputStreamResource> generateReport(ReportRequest request) {
        List<History> records = historyRepository.findByRank(request.getRank());

        if(records.isEmpty()){
            return ResponseEntity.noContent().build();
        }

        ByteArrayInputStream bis = ReportGenerator.historicalReport(records);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "inline; filename = HistoricalReport.pdf");

        return ResponseEntity.ok().headers(headers)
                .contentType(MediaType.APPLICATION_PDF)
                .body(new InputStreamResource(bis));
    }
}
