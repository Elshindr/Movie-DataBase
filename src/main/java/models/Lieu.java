package models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.fasterxml.jackson.annotation.*;
import models.jsonschema.beans.Acteur;
import javax.persistence.*;


/**
 * Classe Lieu.
 * Gére les instances de Lieu pour JPA et JSON
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@Entity
@Table(name="lieu")
public class Lieu {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @JsonProperty("ville")
    private String ville;

    @JsonProperty("etatDept")
    private String etatDept;

    @JsonProperty("pays")
    @Transient
    private String paysJSON;

    @ManyToOne
    @JoinColumn(name="id_pays")
    private Pays pays;

    @JsonIgnore
    @Transient
    private Map<String, Object> additionalProperties = new HashMap<>();

    @OneToMany(mappedBy = "lieu")
    private List<Film> lstFilm = new ArrayList<>();

    @OneToMany(mappedBy = "lieuNaissance")
    private List<Acteur> lstActeur = new ArrayList<>();

    public Lieu() {
        // Constructor for JPA
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @JsonProperty("pays")
    public String getPaysJSON() {
        return paysJSON;
    }
    @JsonProperty("pays")
    public void setPaysJSON(String paysJSON) {
        this.paysJSON = paysJSON;
    }

    @JsonProperty("ville")
    public String getVille() {
        return ville;
    }

    @JsonProperty("ville")
    public void setVille(String ville) {
        this.ville = ville;
    }

    @JsonProperty("etatDept")
    public String getEtatDept() {
        return etatDept;
    }

    @JsonProperty("etatDept")
    public void setEtatDept(String etatDept) {
        this.etatDept = etatDept;
    }


    public Pays getPays() {
        return pays;
    }

    public void setPays(Pays pays) {
        this.pays = pays;
    }


    public List<Acteur> getLstActeur() {
        return lstActeur;
    }

    public void setLstActeur(List<Acteur> lstActeur) {
        this.lstActeur = lstActeur;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

    public List<Film> getLstFilm() {
        return lstFilm;
    }

    public void setLstFilm(List<Film> lstFilm) {
        this.lstFilm = lstFilm;
    }

    @Override
    public String toString() {
        String cst = "<null>";
        StringBuilder sb = new StringBuilder();
        sb.append(Lieu.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("ville");
        sb.append('=');
        sb.append(((this.ville == null)?cst:this.ville));
        sb.append(',');
        sb.append("etatDept");
        sb.append('=');
        sb.append(((this.etatDept == null)?cst:this.etatDept));
        sb.append(',');
        sb.append("pays");
        sb.append('=');
        sb.append(((this.pays == null)?cst:this.pays));
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
        result = ((result* 31)+((this.ville == null)? 0 :this.ville.hashCode()));
        result = ((result* 31)+((this.additionalProperties == null)? 0 :this.additionalProperties.hashCode()));
        result = ((result* 31)+((this.etatDept == null)? 0 :this.etatDept.hashCode()));
        result = ((result* 31)+((this.pays == null)? 0 :this.pays.hashCode()));
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if (!(other instanceof Lieu)) {
            return false;
        }
        Lieu rhs = ((Lieu) other);
        return (((((this.ville == rhs.ville)||((this.ville!= null)&&this.ville.equals(rhs.ville)))&&((this.additionalProperties == rhs.additionalProperties)||((this.additionalProperties!= null)&&this.additionalProperties.equals(rhs.additionalProperties))))&&((this.etatDept == rhs.etatDept)||((this.etatDept!= null)&&this.etatDept.equals(rhs.etatDept))))&&((this.pays == rhs.pays)||((this.pays!= null)&&this.pays.equals(rhs.pays))));
    }

}
