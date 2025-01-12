package org.lengueCode.entites;

import org.lengueCode.enums.StatusEmprunt;

import java.time.LocalDate;

public class Emprunt {
    private Long idEmprunt;
    private LocalDate dateEmprunt;
    private  LocalDate dateRetourPrev;
    private LocalDate dateRetourEff;
    private Long membreId;
    private Long livreId;
    private StatusEmprunt status;

    public Emprunt() {
    }

    public Emprunt(Long idEmprunt, LocalDate dateEmprunt, LocalDate dateRetourPrev, LocalDate dateRetourEff, Long membreId, Long livreId, StatusEmprunt status) {
        this.idEmprunt = idEmprunt;
        this.dateEmprunt = dateEmprunt;
        this.dateRetourPrev = dateRetourPrev;
        this.dateRetourEff = dateRetourEff;
        this.membreId = membreId;
        this.livreId = livreId;
        this.status=status;
    }

    public Long getIdEmprunt() {
        return idEmprunt;
    }

    public void setIdEmprunt(Long idEmprunt) {
        this.idEmprunt = idEmprunt;
    }

    public LocalDate getDateEmprunt() {
        return dateEmprunt;
    }

    public void setDateEmprunt(LocalDate dateEmprunt) {
        this.dateEmprunt = dateEmprunt;
    }

    public LocalDate getDateRetourPrev() {
        return dateRetourPrev;
    }

    public void setDateRetourPrev(LocalDate dateRetourPrev) {
        this.dateRetourPrev = dateRetourPrev;
    }

    public LocalDate getDateRetourEff() {
        return dateRetourEff;
    }

    public void setDateRetourEff(LocalDate dateRetourEff) {
        this.dateRetourEff = dateRetourEff;
    }

    public Long getMembreId() {
        return membreId;
    }

    public void setMembreId(Long membreId) {
        this.membreId = membreId;
    }

    public Long getLivreId() {
        return livreId;
    }

    public void setLivreId(Long livreId) {
        this.livreId = livreId;
    }

    public StatusEmprunt getStatus() {
        return status;
    }

    public void setStatus(StatusEmprunt status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Emprunt{" +
                "idEmprunt=" + idEmprunt +
                ", dateEmprunt=" + dateEmprunt +
                ", dateRetourPrev=" + dateRetourPrev +
                ", dateRetourEff=" + dateRetourEff +
                ", membreId=" + membreId +
                ", livreId=" + livreId +
                ", status=" + status +
                '}';
    }
}


