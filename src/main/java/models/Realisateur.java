
package models;

import com.fasterxml.jackson.annotation.*;
import javax.persistence.*;
import java.util.Objects;

/**
 * Classe Realisateur
 * Gére les instances de Realisateur pour JPA et JSON
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@Entity
@Table(name = "realisateur")
public class Realisateur {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @JsonProperty("identite")
    private String nom;

    @JsonProperty("url")
    private String url;

    /**
     * Instantiates a new Realisateur.
     */
    public Realisateur() {
        // JPA Constructor
    }

    /**
     * Gets id.
     *
     * @return the id
     */
    public Integer getId() {
        return id;
    }

    /**
     * Sets id.
     *
     * @param id the id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * Gets nom.
     *
     * @return the nom
     */
    @JsonProperty("identite")
    public String getNom() {
        return nom;
    }

    /**
     * Sets nom.
     *
     * @param nom the nom
     */
    @JsonProperty("identite")
    public void setNom(String nom) {
        this.nom = nom;
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
     * Sets url.
     *
     * @param url the url
     */
    @JsonProperty("url")
    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(Realisateur.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("nom");
        sb.append('=');
        sb.append(((this.nom == null)?"<null>":this.nom));
        sb.append(',');
        sb.append("url");
        sb.append('=');
        sb.append(((this.url == null)?"<null>":this.url));
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
        result = ((result* 31)+((this.nom == null)? 0 :this.nom.hashCode()));
        result = ((result* 31)+((this.url == null)? 0 :this.url.hashCode()));
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if (!(other instanceof Realisateur)) {
            return false;
        }
        Realisateur rhs = ((Realisateur) other);
        return ((Objects.equals(this.nom, rhs.nom))&&(Objects.equals(this.id, rhs.id))&&(Objects.equals(this.url, rhs.url)));
    }
}
