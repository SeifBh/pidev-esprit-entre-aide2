/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.pidev.entities;

import java.sql.Date;

/**
 *
 * @author Seif BelHadjAli
 */
public class Utilisateur {
    public int id;
    public String username;
    public String username_canonical;
    public String email;
    public String email_canonical;
    public Byte enabled;
    public String salt;
    public String password;
    public Date last_login;
    public String Confirmation_token;
    public Date password_requested_at;
    public String roles;
    public String nom;
    public String prenom;
    public String sexe;

    public Utilisateur() {
    }

    public Utilisateur(String sexe) {
        this.sexe = sexe;
    }

    public Utilisateur(int id, String sexe) {
        this.id = id;
        this.sexe = sexe;
    }

    public Utilisateur( String username, String username_canonical, String email, String email_canonical, Byte enabled, String salt, String password, Date last_login, String Confirmation_token, Date password_requested_at,String roles,  String nom, String prenom, String sexe) {

        this.username = username;
        this.username_canonical = username_canonical;
        this.email = email;
        this.email_canonical = email_canonical;
        this.enabled = enabled;
        this.salt = salt;
        this.password = password;
        this.last_login = last_login;
        this.Confirmation_token = Confirmation_token;
        this.password_requested_at = password_requested_at;
        this.roles = roles;
        this.nom = nom;
        this.prenom = prenom;
        this.sexe = sexe;
    }

        public Utilisateur(String username, String nom, String prenom, String email) {

        this.username = username;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername_canonical() {
        return username_canonical;
    }

    public void setUsername_canonical(String username_canonical) {
        this.username_canonical = username_canonical;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail_canonical() {
        return email_canonical;
    }

    public void setEmail_canonical(String email_canonical) {
        this.email_canonical = email_canonical;
    }

    public Byte getEnabled() {
        return enabled;
    }

    public void setEnabled(Byte enabled) {
        this.enabled = enabled;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getLast_login() {
        return last_login;
    }

    public void setLast_login(Date last_login) {
        this.last_login = last_login;
    }

    public String getConfirmation_token() {
        return Confirmation_token;
    }

    public void setConfirmation_token(String Confirmation_token) {
        this.Confirmation_token = Confirmation_token;
    }

    public Date getPassword_requested_at() {
        return password_requested_at;
    }

    public void setPassword_requested_at(Date password_requested_at) {
        this.password_requested_at = password_requested_at;
    }

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getSexe() {
        return sexe;
    }

    public void setSexe(String sexe) {
        this.sexe = sexe;
    }
    
    
    
    
}
