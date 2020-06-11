package com.tonextlevel.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "HISTORY")
@Getter @Setter @NoArgsConstructor
public class History {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long mFromLevel;
    private Long mToLevel;
    private Long mExperienceNeeded;
    private String mResultingRank;

    public History(Long fromLevel, Long toLevel, Long experience, String rank){
        this.mFromLevel = fromLevel;
        this.mToLevel = toLevel;
        this.mExperienceNeeded = experience;
        this.mResultingRank = rank;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        History history = (History) o;
        return id.equals(history.id) &&
                mFromLevel.equals(history.mFromLevel) &&
                mToLevel.equals(history.mToLevel) &&
                mExperienceNeeded.equals(history.mExperienceNeeded) &&
                mResultingRank.equals(history.mResultingRank);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, mFromLevel, mToLevel, mExperienceNeeded, mResultingRank);
    }

    @Override
    public String toString(){
        StringBuilder builder = new StringBuilder();
        builder.append("History{id=").append(id).append(", mFromLevel=").append(mFromLevel)
                .append(", mToLevel=").append(mToLevel).append(", mExperienceNeeded=").append(mExperienceNeeded)
                .append(", mResultingRank=").append(mResultingRank).append("}");
        return builder.toString();
    }
}
