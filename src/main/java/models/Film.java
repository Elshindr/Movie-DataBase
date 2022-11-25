package models;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import com.fasterxml.jackson.annotation.*;
import models.jsonschema.beans.Acteur;
import models.jsonschema.beans.CastingPrincipal;
import models.jsonschema.beans.RoleDto;
import javax.persistence.*;

/**
 * Classe Acteur.
 * Gére les instances de Film pour JPA et JSON
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@Entity
@Table(name="film")
public class Film {

    @JsonProperty("id")
    @Id
    private String id;

    @ManyToOne
    @JsonProperty("pays")
    @JoinColumn(name="id_pays")
    private Pays pays;

    @JsonProperty("nom")
    private String nom;

    @JsonProperty("url")
    private String url;

    @JsonProperty("plot")
    private String plot;

    @JsonProperty("langue")
    private String langue;

    @JsonProperty("lieuTournage")
    @ManyToOne
    @JoinColumn(name="id_lieu")
    private Lieu lieu;

    @JsonProperty("roles")
    @Transient
    private List<RoleDto> roles = new ArrayList<>();

    @ManyToMany
    @JoinTable(name="ROLE_FILM", joinColumns = {@JoinColumn(name = "id_film")},
            inverseJoinColumns = {@JoinColumn(name = "id_role")})
    private List<Role> lstRole = new ArrayList<>();


    @ManyToMany
    @JoinTable(name="ACTEUR_FILM", joinColumns = {@JoinColumn(name = "id_film")},
            inverseJoinColumns = {@JoinColumn(name = "id_acteur")})
    private List<Acteur> lstActeur = new ArrayList<>();


    @JsonProperty("realisateurs")
    @ManyToMany
    @JoinTable(name="REAL_FILM", joinColumns = {@JoinColumn(name = "id_film")},
            inverseJoinColumns = {@JoinColumn(name = "id_realisateur")})
    private List<Realisateur> lstRealisateur = new ArrayList<>();

    @JsonProperty("castingPrincipal")
    @Transient
    private List<CastingPrincipal> castingPrincipal = new ArrayList<>();

    @ManyToMany
    @JoinTable(name="CASTINGPRINCIPAL_FILM", joinColumns = {@JoinColumn(name = "id_film")},
            inverseJoinColumns = {@JoinColumn(name = "id_acteur")})
    private List<Acteur> lstCastingP = new ArrayList<>();

    @JsonProperty("anneeSortie")
    private String anneeSortie;

    @JsonProperty("genres")
    @Transient
    private List<String> genres;

    @ManyToMany
    @JoinTable(name="GENRE_FILM", joinColumns = {@JoinColumn(name = "id_film")},
            inverseJoinColumns = {@JoinColumn(name = "id_genre")})
    private List<Genre> lstGenres = new ArrayList<>();


    /**
     * Constructor JPA
     */
    public Film() {
       // JPA
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
     * Gets pays.
     *
     * @return the pays
     */
    @JsonProperty("pays")
    public Pays getPays() {
        return pays;
    }

    /**
     * Sets pays.
     *
     * @param pays the pays
     */
    @JsonProperty("pays")
    public void setPays(Pays pays) {
        this.pays = pays;
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
     * Gets plot.
     *
     * @return the plot
     */
    @JsonProperty("plot")
    public String getPlot() {
        return plot;
    }

    /**
     * Sets plot.
     *
     * @param plot the plot
     */
    @JsonProperty("plot")
    public void setPlot(String plot) {
        this.plot = plot;
    }

    /**
     * Gets langue.
     *
     * @return the langue
     */
    @JsonProperty("langue")
    public String getLangue() {
        return langue;
    }

    /**
     * Sets langue.
     *
     * @param langue the langue
     */
    @JsonProperty("langue")
    public void setLangue(String langue) {
        this.langue = langue;
    }

    /**
     * Gets lieu tournage.
     *
     * @return the lieu tournage
     */
    @JsonProperty("lieuTournage")
    public Lieu getLieuTournage() {
        return lieu;
    }

    /**
     * Sets lieu tournage.
     *
     * @param lieu the lieu
     */
    @JsonProperty("lieuTournage")
    public void setLieuTournage(Lieu lieu) {
        this.lieu = lieu;
    }

    /**
     * Gets lst realisateur.
     *
     * @return the lst realisateur
     */
    @JsonProperty("realisateurs")
    public List<Realisateur> getLstRealisateur() {
        return lstRealisateur;
    }

    /**
     * Sets lst realisateur.
     *
     * @param lstRealisateur the lst realisateur
     */
    @JsonProperty("realisateurs")
    public void setLstRealisateur(List<Realisateur> lstRealisateur) {
        this.lstRealisateur = lstRealisateur;
    }

    /**
     * Gets lst acteur.
     *
     * @return the lst acteur
     */
    public List<Acteur> getLstActeur() {
        return lstActeur;
    }

    /**
     * Sets lst acteur.
     *
     * @param lstActeur the lst acteur
     */
    public void setLstActeur(List<Acteur> lstActeur) {
        this.lstActeur = lstActeur;
    }

    /**
     * Gets roles.
     *
     * @return the roles
     */
    @JsonProperty("roles")
    public List<RoleDto> getRoles() {
        return roles;
    }

    /**
     * Sets roles.
     *
     * @param roles the roles
     */
    @JsonProperty("roles")
    public void setRoles(List<RoleDto> roles) {
        this.roles = roles;
    }

    /**
     * Gets casting principal.
     *
     * @return the casting principal
     */
    @JsonProperty("castingPrincipal")
    public List<CastingPrincipal> getCastingPrincipal() {
        return castingPrincipal;
    }

    /**
     * Sets casting principal.
     *
     * @param castingPrincipal the casting principal
     */
    @JsonProperty("castingPrincipal")
    public void setCastingPrincipal(List<CastingPrincipal> castingPrincipal) {
        this.castingPrincipal = castingPrincipal;
    }

    /**
     * Gets lst casting p.
     *
     * @return the lst casting p
     */
    public List<Acteur> getLstCastingP() {
        return lstCastingP;
    }

    /**
     * Sets lst casting p.
     *
     * @param lstCastingP the lst casting p
     */
    public void setLstCastingP(List<Acteur> lstCastingP) {
        this.lstCastingP = lstCastingP;
    }

    /**
     * Gets annee sortie.
     *
     * @return the annee sortie
     */
    @JsonProperty("anneeSortie")
    public String getAnneeSortie() {
        return anneeSortie;
    }

    /**
     * Sets annee sortie.
     *
     * @param anneeSortie the annee sortie
     */
    @JsonProperty("anneeSortie")
    public void setAnneeSortie(String anneeSortie) {
        this.anneeSortie = anneeSortie;
    }


    /**
     * Gets lst role.
     *
     * @return the lst role
     */
    public List<Role> getLstRole() {
        return lstRole;
    }


    /**
     * Gets lst genres.
     *
     * @return the lst genres
     */
    public List<Genre> getLstGenres() {
        return lstGenres;
    }


    /**
     * Gets genres.
     *
     * @return the genres
     */
    @JsonProperty("genres")
    public List<String> getGenres() {
        return genres;
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        String cst = "<null>";
        sb.append(Film.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("id");
        sb.append('=');
        sb.append(((this.id == null)?cst:this.id));
        sb.append(',');
        sb.append("pays");
        sb.append('=');
        sb.append(((this.pays == null)?cst:this.pays));
        sb.append(',');
        sb.append("nom");
        sb.append('=');
        sb.append(((this.nom == null)?cst:this.nom));
        sb.append(',');
        sb.append("url");
        sb.append('=');
        sb.append(((this.url == null)?cst:this.url));
        sb.append(',');
        sb.append("plot");
        sb.append('=');
        sb.append(((this.plot == null)?cst:this.plot));
        sb.append(',');
        sb.append("langue");
        sb.append('=');
        sb.append(((this.langue == null)?cst:this.langue));
        sb.append(',');
        sb.append("lieu");
        sb.append('=');
        sb.append(((this.lieu == null)?cst:this.lieu));
        sb.append(',');
        sb.append("realisateurs");
        sb.append('=');
        sb.append(((this.lstRealisateur == null)?cst:this.lstRealisateur));
        sb.append(',');
        sb.append("castingPrincipal");
        sb.append('=');
        sb.append(((this.castingPrincipal == null)?cst:this.castingPrincipal));
        sb.append(',');
        sb.append("anneeSortie");
        sb.append('=');
        sb.append(((this.anneeSortie == null)?cst:this.anneeSortie));
        sb.append(',');
        sb.append("genres");
        sb.append('=');
        sb.append(((this.lstGenres == null)?cst:this.lstGenres));
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
        result = ((result* 31)+((this.lieu == null)? 0 :this.lieu.hashCode()));
        result = ((result* 31)+((this.castingPrincipal == null)? 0 :this.castingPrincipal.hashCode()));
        result = ((result* 31)+((this.lstRealisateur == null)? 0 :this.lstRealisateur.hashCode()));
        result = ((result* 31)+((this.anneeSortie == null)? 0 :this.anneeSortie.hashCode()));
        result = ((result* 31)+((this.langue == null)? 0 :this.langue.hashCode()));
        result = ((result* 31)+((this.nom == null)? 0 :this.nom.hashCode()));
        result = ((result* 31)+((this.url == null)? 0 :this.url.hashCode()));
        result = ((result* 31)+((this.plot == null)? 0 :this.plot.hashCode()));
        result = ((result* 31)+((this.lstGenres == null)? 0 :this.lstGenres.hashCode()));
        result = ((result* 31)+((this.id == null)? 0 :this.id.hashCode()));
        result = ((result* 31)+((this.pays == null)? 0 :this.pays.hashCode()));
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if (!(other instanceof Film)) {
            return false;
        }
        Film rhs = ((Film) other);

        return Objects.equals(this.lieu, rhs.lieu) && Objects.equals(this.castingPrincipal, rhs.castingPrincipal) && Objects.equals(this.lstRealisateur, rhs.lstRealisateur) && Objects.equals(this.anneeSortie, rhs.anneeSortie) && Objects.equals(this.langue, rhs.langue) && ( this.nom != null && this.nom.equals(rhs.nom)) && this.url.equals(rhs.url) && this.plot.equals(rhs.plot) && Objects.equals(this.lstGenres, rhs.lstGenres) && (this.id.equals(rhs.id)) && Objects.equals(this.pays, rhs.pays);
    }

}
