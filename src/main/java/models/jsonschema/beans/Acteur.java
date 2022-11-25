package models.jsonschema.beans;

import java.time.LocalDate;
import java.util.*;

import com.fasterxml.jackson.annotation.*;
import models.Film;
import models.Lieu;
import javax.persistence.*;

/**
 * Classe Acteur.
 * Gére les instances d'Acteur pour JPA et JSON
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@Entity
@Table(name = "acteur")
public class Acteur {

    @JsonProperty("id")
    @Id
    private String id;

    @JsonProperty("identite")
    private String nom;

    @JsonProperty("naissance")
    @Transient
    private Naissance naissance;

    @ManyToOne
    @JoinColumn(name="id_lieu")
    private Lieu lieuNaissance;

    @Column(columnDefinition = "DATE")
    private LocalDate dateNaissance;

    @JsonProperty("url")
    private String url;

    @JsonProperty("roles")
    @Transient
    private List<Object> roles = new ArrayList<>();

    @ManyToMany(mappedBy="lstActeur")
    private List<Film> lstFilm = new ArrayList<>();

    @JsonIgnore
    @Transient
    private final Map<String, Object> additionalProperties = new HashMap<>();

    /**
     * Instantiates a new Acteur.
     */
    public Acteur() {
        //JPA CONSTRUCTOR
    }

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
     * Sets id.
     *
     * @param id the id
     */
    @JsonProperty("id")
    public void setId(String id) {
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
     * Gets naissance.
     *
     * @return the naissance
     */
    @JsonProperty("naissance")
    public Naissance getNaissance() {
        return naissance;
    }

    /**
     * Sets naissance.
     *
     * @param naissance the naissance
     */
    @JsonProperty("naissance")
    public void setNaissance(Naissance naissance) {
        this.naissance = naissance;
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
     * Gets roles.
     *
     * @return the roles
     */
    @JsonProperty("roles")
    public List<Object> getRoles() {
        return roles;
    }

    /**
     * Sets roles.
     *
     * @param roles the roles
     */
    @JsonProperty("roles")
    public void setRoles(List<Object> roles) {
        this.roles = roles;
    }

    /**
     * Gets lieu naissance.
     *
     * @return the lieu naissance
     */
    public Lieu getLieuNaissance() {
        return lieuNaissance;
    }

    /**
     * Sets lieu naissance.
     *
     * @param lieuNaissance the lieu naissance
     */
    public void setLieuNaissance(Lieu lieuNaissance) {
        this.lieuNaissance = lieuNaissance;
    }

    /**
     * Gets date naissance.
     *
     * @return the date naissance
     */
    public LocalDate getDateNaissance() {
        return dateNaissance;
    }

    /**
     * Sets date naissance.
     *
     * @param dateNaissance the date naissance
     */
    public void setDateNaissance(LocalDate dateNaissance) {
        this.dateNaissance = dateNaissance;
    }

    /**
     * Gets lst film.
     *
     * @return the lst film
     */
    public List<Film> getLstFilm() {
        return lstFilm;
    }

    /**
     * Sets lst film.
     *
     * @param lstFilm the lst film
     */
    public void setLstFilm(List<Film> lstFilm) {
        this.lstFilm = lstFilm;
    }

    @Override
    public String toString() {
        String cst = "<null>";
        StringBuilder sb = new StringBuilder();
        sb.append(Acteur.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("id");
        sb.append('=');
        sb.append(((this.id == null)?cst:this.id));
        sb.append(',');
        sb.append("nom");
        sb.append('=');
        sb.append(((this.nom == null)?cst:this.nom));
        sb.append(',');
        sb.append("naissance");
        sb.append('=');
        sb.append(((this.naissance == null)?cst:this.naissance));
        sb.append(',');
        sb.append("url");
        sb.append('=');
        sb.append(((this.url == null)?cst:this.url));
        sb.append(',');
        sb.append("roles");
        sb.append('=');
        sb.append(((this.roles == null)?cst:this.roles));
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
        result = ((result* 31)+((this.naissance == null)? 0 :this.naissance.hashCode()));
        result = ((result* 31)+((this.roles == null)? 0 :this.roles.hashCode()));
        result = ((result* 31)+((this.id == null)? 0 :this.id.hashCode()));
        result = ((result* 31)+((this.url == null)? 0 :this.url.hashCode()));
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if (!(other instanceof Acteur)) {
            return false;
        }
        Acteur rhs = ((Acteur) other);
        return (((((Objects.equals(this.nom, rhs.nom))&&(Objects.equals(this.naissance, rhs.naissance)))&&(Objects.equals(this.roles, rhs.roles)))&&(Objects.equals(this.id, rhs.id)))&&((this.additionalProperties == rhs.additionalProperties)||(Objects.equals(this.url, rhs.url))));
    }

}
