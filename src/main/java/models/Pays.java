
package models;

import java.util.*;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import javax.persistence.*;

/**
 * Classe Pays
 * Gére les instances de Pays pour JPA et JSON
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Entity(name = "pays")
public class Pays {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @JsonProperty("nom")
    private String nom;

    @JsonProperty("url")
    private String url;

    @JsonIgnore
    @Transient
    private Map<String, Object> additionalProperties = new HashMap<>();

    @OneToMany(mappedBy = "pays")
    private Set<Lieu> setLieu = new HashSet<>();

    @OneToMany(mappedBy = "pays")
    private Set<Film> setFilm = new HashSet<>();

    /**
     * Instantiates a new Pays.
     */
    public Pays() {
        // Constructor for JPA
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
    @JsonProperty("nom")
    public String getNom() {
        return nom;
    }

    /**
     * Sets nom.
     *
     * @param nom the nom
     */
    @JsonProperty("nom")
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

    /**
     * Gets additional properties.
     *
     * @return the additional properties
     */
    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    /**
     * Sets additional property.
     *
     * @param name  the name
     * @param value the value
     */
    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

    /**
     * Gets set lieu.
     *
     * @return the set lieu
     */
    public Set<Lieu> getSetLieu() {
        return setLieu;
    }

    /**
     * Sets set lieu.
     *
     * @param setLieu the set lieu
     */
    public void setSetLieu(Set<Lieu> setLieu) {
        this.setLieu = setLieu;
    }

    /**
     * Gets set film.
     *
     * @return the set film
     */
    public Set<Film> getSetFilm() {
        return setFilm;
    }

    /**
     * Sets set film.
     *
     * @param setFilm the set film
     */
    public void setSetFilm(Set<Film> setFilm) {
        this.setFilm = setFilm;
    }

    @Override
    public String toString() {
        String cst = "<null>";
        StringBuilder sb = new StringBuilder();
        sb.append(Pays.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("nom");
        sb.append('=');
        sb.append(((this.nom == null)?cst:this.nom));
        sb.append(',');
        sb.append("url");
        sb.append('=');
        sb.append(((this.url == null)?cst:this.url));
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
        result = ((result* 31)+((this.additionalProperties == null)? 0 :this.additionalProperties.hashCode()));
        result = ((result* 31)+((this.nom == null)? 0 :this.nom.hashCode()));
        result = ((result* 31)+((this.url == null)? 0 :this.url.hashCode()));
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if (!(other instanceof Pays)) {
            return false;
        }
        Pays rhs = ((Pays) other);
        return (((Objects.equals(this.additionalProperties, rhs.additionalProperties))&&(Objects.equals(this.nom, rhs.nom)))&&(Objects.equals(this.url, rhs.url)));
    }

}
