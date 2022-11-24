
package models.jsonschema.beans;

import java.util.*;
import com.fasterxml.jackson.annotation.*;
import javax.persistence.Transient;

/**
 * Classe CastingPrincipal
 * Gére les instances de CastingPrincipal pour JSON
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class CastingPrincipal {

    @JsonProperty("id")
    private String id;

    @JsonProperty("identite")
    private String identite;

    @JsonProperty("naissance")
    @Transient
    private Naissance naissance;

    @JsonProperty("url")
    private String url;

    @JsonProperty("height")
    private Integer height;

    @Transient
    private List<Object> roles = new ArrayList<>();

    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<>();

    /**
     * Gets id.
     *
     * @return the id
     */
    @JsonProperty("id")
    public String getId() {
        return id;
    }


    /**
     * Gets identite.
     *
     * @return the identite
     */
    @JsonProperty("identite")
    public String getIdentite() {
        return identite;
    }


    /**
     * Gets naissance.
     *
     * @return the naissance
     */
    @JsonProperty("naissance")
    public Naissance getNaissance() {
        return naissance;
    }


    /**
     * Gets url.
     *
     * @return the url
     */
    @JsonProperty("url")
    public String getUrl() {
        return url;
    }


    /**
     * Gets roles.
     *
     * @return the roles
     */
    @JsonProperty("roles")
    public List<Object> getRoles() {
        return roles;
    }


    @Override
    public String toString() {
        String cst = "<null>";
        StringBuilder sb = new StringBuilder();
        sb.append(CastingPrincipal.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("id");
        sb.append('=');
        sb.append(((this.id == null)?cst:this.id));
        sb.append(',');
        sb.append("identite");
        sb.append('=');
        sb.append(((this.identite == null)?cst:this.identite));
        sb.append(',');
        sb.append("naissance");
        sb.append('=');
        sb.append(((this.naissance == null)?cst:this.naissance));
        sb.append(',');
        sb.append("url");
        sb.append('=');
        sb.append(((this.url == null)?cst:this.url));
        sb.append(',');
        sb.append("height");
        sb.append('=');
        sb.append(((this.height == null)?cst:this.height));
        sb.append(',');
        sb.append("roles");
        sb.append('=');
        sb.append(((this.roles == null)?cst:this.roles));
        sb.append(',');
        sb.append("additionalProperties");
        sb.append('=');
        sb.append(((this.additionalProperties == null)?cst:this.additionalProperties));
        sb.append(',');
        if (sb.charAt((sb.length()- 1)) == ',') {
            sb.setCharAt((sb.length()- 1), ']');
        } else {
            sb.append(']');
        }
        return sb.toString();
    }

    @Override
    public int hashCode() {
        int result = 1;
        result = ((result* 31)+((this.identite == null)? 0 :this.identite.hashCode()));
        result = ((result* 31)+((this.naissance == null)? 0 :this.naissance.hashCode()));
        result = ((result* 31)+((this.roles == null)? 0 :this.roles.hashCode()));
        result = ((result* 31)+((this.id == null)? 0 :this.id.hashCode()));
        result = ((result* 31)+((this.additionalProperties == null)? 0 :this.additionalProperties.hashCode()));
        result = ((result* 31)+((this.url == null)? 0 :this.url.hashCode()));
        result = ((result* 31)+((this.height == null)? 0 :this.height.hashCode()));
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if (!(other instanceof CastingPrincipal)) {
            return false;
        }
        CastingPrincipal rhs = ((CastingPrincipal) other);
        return ((((Objects.equals(this.identite, rhs.identite))&&(Objects.equals(this.naissance, rhs.naissance)))&&(Objects.equals(this.roles, rhs.roles)))&&(Objects.equals(this.id, rhs.id)))&&((this.additionalProperties == rhs.additionalProperties)||((this.additionalProperties!= null)&&(Objects.equals(this.url, rhs.url)))&&(Objects.equals(this.height, rhs.height)));
    }

}
