package com.logistica.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * Registro
 */
public interface Registro {

    public String getCriadoPor();

    public void setCriadoPor(String criadoPor);

    public String getAlteradoPor();

    public void setAlteradoPor(String alteradoPor);

    public LocalDateTime getCriadoEm();

    public void setCriadoEm(LocalDateTime date);

    public void setAlteradoEm(LocalDateTime date);

    public LocalDateTime getAlteradoEm();

}
