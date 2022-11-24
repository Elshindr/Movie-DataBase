
package models.jsonschema.beans;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import com.fasterxml.jackson.annotation.*;


/**
 * Classe RoleDto
 * Gére les instances de RoleDto pour JSON
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class RoleDto {

    @JsonProperty("characterName")
    private String characterName;

    @JsonProperty("acteur")
    private Acteur acteur;

    @JsonProperty("film")
    private String film;

    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<>();

    /**
     * Gets character name.
     *
     * @return the character name
     */
    @JsonProperty("characterName")
    public String getCharacterName() {
        return characterName;
    }



    /**
     * Gets acteur.
     *
     * @return the acteur
     */
    @JsonProperty("acteur")
    public Acteur getActeur() {
        return acteur;
    }


    /**
     * Gets film.
     *
     * @return the film
     */
    @JsonProperty("film")
    public String getFilm() {
        return film;
    }



    @Override
    public String toString() {
        String cst = "<null>";
        StringBuilder sb = new StringBuilder();
        sb.append(RoleDto.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("characterName");
        sb.append('=');
        sb.append(((this.characterName == null)?cst:this.characterName));
        sb.append(',');
        sb.append("acteur");
        sb.append('=');
        sb.append(((this.acteur == null)?cst:this.acteur));
        sb.append(',');
        sb.append("film");
        sb.append('=');
        sb.append(((this.film == null)?cst:this.film));
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
        result = ((result* 31)+((this.characterName == null)? 0 :this.characterName.hashCode()));
        result = ((result* 31)+((this.film == null)? 0 :this.film.hashCode()));
        result = ((result* 31)+((this.additionalProperties == null)? 0 :this.additionalProperties.hashCode()));
        result = ((result* 31)+((this.acteur == null)? 0 :this.acteur.hashCode()));
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if (!(other instanceof RoleDto)) {
            return false;
        }
        RoleDto rhs = ((RoleDto) other);
        return ((((Objects.equals(this.characterName, rhs.characterName))&&(Objects.equals(this.film, rhs.film)))&&(Objects.equals(this.additionalProperties, rhs.additionalProperties)))&&(Objects.equals(this.acteur, rhs.acteur)));
    }

}
