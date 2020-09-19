
package travel.guide.Api.OpenTripMap;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "xid",
    "name",
    "dist",
    "rate",
    "wikidata",
    "kinds",
    "point",
    "osm"
})
public class CityJson {

    @JsonProperty("xid")
    private String xid;
    @JsonProperty("name")
    private String name;
    @JsonProperty("dist")
    private Double dist;
    @JsonProperty("rate")
    private Integer rate;
    @JsonProperty("wikidata")
    private String wikidata;
    @JsonProperty("kinds")
    private String kinds;
    @JsonProperty("point")
    private Point point;
    @JsonProperty("osm")
    private String osm;

    /**
     * No args constructor for use in serialization
     * 
     */
    public CityJson() {
    }

    /**
     * 
     * @param xid
     * @param rate
     * @param name
     * @param osm
     * @param dist
     * @param kinds
     * @param wikidata
     * @param point
     */
    public CityJson(String xid, String name, Double dist, Integer rate, String wikidata, String kinds, Point point, String osm) {
        super();
        this.xid = xid;
        this.name = name;
        this.dist = dist;
        this.rate = rate;
        this.wikidata = wikidata;
        this.kinds = kinds;
        this.point = point;
        this.osm = osm;
    }

    @JsonProperty("xid")
    public String getXid() {
        return xid;
    }

    @JsonProperty("xid")
    public void setXid(String xid) {
        this.xid = xid;
    }

    @JsonProperty("name")
    public String getName() {
        return name;
    }

    @JsonProperty("name")
    public void setName(String name) {
        this.name = name;
    }

    @JsonProperty("dist")
    public Double getDist() {
        return dist;
    }

    @JsonProperty("dist")
    public void setDist(Double dist) {
        this.dist = dist;
    }

    @JsonProperty("rate")
    public Integer getRate() {
        return rate;
    }

    @JsonProperty("rate")
    public void setRate(Integer rate) {
        this.rate = rate;
    }

    @JsonProperty("wikidata")
    public String getWikidata() {
        return wikidata;
    }

    @JsonProperty("wikidata")
    public void setWikidata(String wikidata) {
        this.wikidata = wikidata;
    }

    @JsonProperty("kinds")
    public String getKinds() {
        return kinds;
    }

    @JsonProperty("kinds")
    public void setKinds(String kinds) {
        this.kinds = kinds;
    }

    @JsonProperty("point")
    public Point getPoint() {
        return point;
    }

    @JsonProperty("point")
    public void setPoint(Point point) {
        this.point = point;
    }

    @JsonProperty("osm")
    public String getOsm() {
        return osm;
    }

    @JsonProperty("osm")
    public void setOsm(String osm) {
        this.osm = osm;
    }

	@Override
	public String toString() {
		return "Example [xid=" + xid + ", name=" + name + ", dist=" + dist + ", rate=" + rate + ", wikidata=" + wikidata
				+ ", kinds=" + kinds + ", point=" + point + ", osm=" + osm + "]";
	}

}
