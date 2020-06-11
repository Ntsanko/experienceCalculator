package com.tonextlevel.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Objects;

@Entity
@Table(name = "RANK_LOOKUP")
@Getter @Setter @NoArgsConstructor
public class RankLookup {

    @Id
    private Long id;

    public Long mFrom;
    public Long mTo;
    private Long mXpPerLevel;
    private String mName;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RankLookup that = (RankLookup) o;
        return id.equals(that.id) &&
                mFrom.equals(that.mFrom) &&
                mTo.equals(that.mTo) &&
                mXpPerLevel.equals(that.mXpPerLevel) &&
                mName.equals(that.mName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, mFrom, mTo, mXpPerLevel, mName);
    }

    @Override
    public String toString(){
        StringBuilder builder = new StringBuilder();
        builder.append("Rank_Lookup{id=").append(id).append(", mFrom=").append(mFrom)
                .append(", mTo=").append(mTo).append(", mXpPerLevel=").append(mXpPerLevel)
                .append(", mName=").append(mName)
                .append("}");
        return builder.toString();
    }
}
