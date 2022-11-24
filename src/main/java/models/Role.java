package models;

import java.util.ArrayList;
import java.util.List;
import com.fasterxml.jackson.annotation.*;
import models.jsonschema.beans.Acteur;
import javax.persistence.*;

/**
 * Classe Role
 * Gére les instances de Role pour JPA
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@Entity
@Table(name = "role")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String characterName;

    @ManyToMany
    @JoinTable(name="ROLE_ACTEUR", joinColumns = {@JoinColumn(name = "id_role")},
            inverseJoinColumns = {@JoinColumn(name = "id_acteur")})
    private List<Acteur> lstActeur = new ArrayList<>();


    /**
     * Instantiates a new Role.
     */
    public Role() {
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
     * Gets character name.
     *
     * @return the character name
     */
    public String getCharacterName() {
        return characterName;
    }

    /**
     * Sets character name.
     *
     * @param characterName the character name
     */
    public void setCharacterName(String characterName) {
        this.characterName = characterName;
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

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(models.jsonschema.beans.RoleDto.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("characterName");
        sb.append('=');
        sb.append(((this.characterName == null)?"<null>":this.characterName));
        sb.append(',');
        sb.append("acteur");
        sb.append('=');
        sb.append(((this.lstActeur == null)?"<null>":this.lstActeur));
        sb.append(',');

        if (sb.charAt((sb.length()- 1)) == ',') {
            sb.setCharAt((sb.length()- 1), ']');
        } else {
            sb.append(']');
        }
        return sb.toString();
    }

}
