
package models.jsonschema.beans;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Classe Naissance
 * Gére les instances de Naissance pour JSON
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Naissance {

    @JsonProperty("dateNaissance")
    private String dateNaissance;

    @JsonProperty("lieuNaissance")
    private String lieuNaissance;

    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<>();

    /**
     * Gets date naissance.
     *
     * @return the date naissance
     */
    @JsonProperty("dateNaissance")
    public String getDateNaissance() {
        return dateNaissance;
    }


    /**
     * Gets lieu naissance.
     *
     * @return the lieu naissance
     */
    @JsonProperty("lieuNaissance")
    public String getLieuNaissance() {
        return lieuNaissance;
    }

    @Override
    public String toString() {
        String cst = "<null>";
        StringBuilder sb = new StringBuilder();
        sb.append(Naissance.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("dateNaissance");
        sb.append('=');
        sb.append(((this.dateNaissance == null)?cst:this.dateNaissance));
        sb.append(',');
        sb.append("lieuNaissance");
        sb.append('=');
        sb.append(((this.lieuNaissance == null)?cst:this.lieuNaissance));
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
        result = ((result* 31)+((this.lieuNaissance == null)? 0 :this.lieuNaissance.hashCode()));
        result = ((result* 31)+((this.additionalProperties == null)? 0 :this.additionalProperties.hashCode()));
        result = ((result* 31)+((this.dateNaissance == null)? 0 :this.dateNaissance.hashCode()));
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if (!(other instanceof Naissance)) {
            return false;
        }
        Naissance rhs = ((Naissance) other);
        return this.lieuNaissance.equals(rhs.lieuNaissance) && (Objects.equals(this.additionalProperties, rhs.additionalProperties)) && this.dateNaissance.equals(rhs.dateNaissance);
    }

}
