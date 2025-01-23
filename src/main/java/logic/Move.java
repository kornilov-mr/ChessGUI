package logic;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import java.util.Objects;

/**
 * Class which holds information about the move
 */
@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        property = "type"
)
@JsonSubTypes({
        @JsonSubTypes.Type(value = PromotionMove.class, name = "PromotionMove"),
})
public class Move {
    private final int xEnd;
    private final int yEnd;
    private final int xStart;
    private final int yStart;

    @JsonCreator
    public Move(@JsonProperty("xstart") int xStart, @JsonProperty("ystart") int yStart,
                @JsonProperty("xend") int xEnd, @JsonProperty("yend") int yEnd) {
        this.xEnd = xEnd;
        this.yEnd = yEnd;
        this.xStart = xStart;
        this.yStart = yStart;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Move move)) return false;
        return xEnd == move.xEnd && yEnd == move.yEnd && xStart == move.xStart && yStart == move.yStart;
    }


    @Override
    public int hashCode() {
        return Objects.hash(xEnd, yEnd, xStart, yStart);
    }

    public int getXEnd() {
        return xEnd;
    }

    public int getYEnd() {
        return yEnd;
    }


    public int getXStart() {
        return xStart;
    }

    public int getYStart() {
        return yStart;
    }


    @Override
    public String toString() {
        return "Move{" +
                "xStart=" + xStart +
                ", yStart=" + yStart +
                ", xEnd=" + xEnd +
                ", yEnd=" + yEnd +
                '}';
    }



}
