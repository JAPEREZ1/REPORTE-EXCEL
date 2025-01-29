package com.refla.reportes.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Collection;

@Entity
@Table(name = "personas")
public class Persona {

    @Id
    private Long codigo;

    @Column(name = "codigo_institucion")
    private String codigoInstitucion;

    @Column(name = "codigo_ubicacion")
    private String codigoUbicacion;

    @Column(name = "per_nombres")
    private String perNombres;

    @Column(name="per_tipo_identificacion")
    private String perTipoIdentificacion;

    @Column(name="per_identificacion")
    private String perIdentificacion;

    @Column(name="per_listas_control")
    private String perListasControl;

    @Column(name="per_peps")
    private String perPeps;

    @Column(name="per_fecha_nacimiento")
    private String perFechaNacimiento;

    @Column(name="per_sexo")
    private String perSexo;

    @Column(name="per_ingresos")
    private String perIngresos;

    @Column(name="per_patrimonio")
    private String perPatrimonio;

    @Column(name="per_estado_informacion")
    private String perEstadoInformacion;

    @Column(name="per_estado")
    private String perEstado;

    @Column(name="per_tipo_persona")
    private String perTipoPersona;

    @Column(name="aud_crea")
    private String AudCrea;

    @Column(name="aud_modifica")
    private String AudModifica;

    @Column(name ="aud_eliminado")
    private String AudEliminado;

    @Column(name="created_at")
    private String CreatedAt;

    @Column(name="updated_at")
    private String UpdatedAt;

    @Column(name="codigo_tipo_estado_civil")
    private String CodigoTipoEstadoCivil;

    public Long getCodigo() { return codigo; }
    public void setCodigo(Long codigo) { this.codigo = codigo; }
    public String getCodigoInstitucion() { return codigoInstitucion; }
    public void setCodigoInstitucion(String codigoInstitucion) { this.codigoInstitucion = codigoInstitucion; }
    public String getCodigoUbicacion() { return codigoUbicacion; }
    public void setCodigoUbicacion(String codigoUbicacion) { this.codigoUbicacion = codigoUbicacion; }
    public String getPerNombres() { return perNombres; }
    public void setPerNombres(String perNombres) { this.perNombres = perNombres; }
    public String getPerTipoIdentificacion(){return perTipoIdentificacion;}
    public void setPerTipoIdentificacion(String perTipoIdentificacion){this.perTipoIdentificacion = perTipoIdentificacion; }
    public String getPerIdentificacion(){return perIdentificacion;}
    public void setPerIdentificacion(String perIdentificacion){this.perIdentificacion = perIdentificacion; }
    public String getPerListasControl(){return perListasControl;}
    public void setPerListasControl(String perListasControl){this.perListasControl = perListasControl; }
    public String getPerPeps(){return perPeps;}
    public void setPerPeps(String perPeps){this.perPeps = perPeps; }
    public String getPerFechaNacimiento(){return perFechaNacimiento;}
    public void setPerFechaNacimiento(String perFechaNacimiento){this.perFechaNacimiento = perFechaNacimiento; }
    public String getPerSexo(){return perSexo;}
    public void setPerSexo(String perSexo){this.perSexo = perSexo; }
    public String getPerIngresos(){return perIngresos;}
    public void setPerIngresos(String perIngresos){this.perIngresos = perIngresos; }
    public String getPerPatrimonio(){return perPatrimonio;}
    public void setPerPatrimonio(String perPatrimonio){this.perPatrimonio = perPatrimonio; }
    public String getPerEstadoInformacion(){return perEstadoInformacion;}
    public void setPerEstadoInformacion(String perEstadoInformacion){this.perEstadoInformacion = perEstadoInformacion; }
    public String getPerEstado(){return perEstado;}
    public void setPerEstado(String perEstado){this.perEstado = perEstado; }
    public String getPerTipoPersona(){return perTipoPersona;}
    public void setPerTipoPersona(String perTipoPersona){this.perTipoPersona = perTipoPersona; }
    public String getAudCrea(){return AudCrea;}
    public void setAudCrea(String audCrea){this.AudCrea = audCrea; }
    public String getAudModifica(){return AudModifica;}
    public void setAudModifica(String audModifica){this.AudModifica = audModifica; }
    public String getAudEliminado() {return AudEliminado;}
    public void setAudEliminado(String audEliminado){this.AudEliminado = audEliminado; }
    public String getCreatedAt(){return CreatedAt;}
    public void setCreatedAt(String createdAt){this.CreatedAt = createdAt; }
    public String getUpdatedAt(){return UpdatedAt;}
    public void setUpdatedAt(String updatedAt){this.UpdatedAt = updatedAt; }
    public String getCodigoTipoEstadoCivil(){return CodigoTipoEstadoCivil;}
    public void setCodigoTipoEstadoCivil(String codigoTipoEstadoCivil){this.CodigoTipoEstadoCivil = codigoTipoEstadoCivil; }

    public Collection<Object> getIdentificacion() {
        return null;
    }

    public String getNombres() {
        return null;
    }
}


