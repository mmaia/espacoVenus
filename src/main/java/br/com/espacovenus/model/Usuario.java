package br.com.espacovenus.model;

import javax.persistence.Entity;
import java.io.Serializable;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Column;
import javax.persistence.Version;
import java.lang.Override;
import java.util.Date;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Usuario implements Serializable
{

   @Id
   @GeneratedValue(strategy = GenerationType.AUTO)
   @Column(name = "id", updatable = false, nullable = false)
   private Long id = null;
   @Version
   @Column(name = "version")
   private int version = 0;

   @Column
   private String sobrenome;

   @Column
   private String nome;

   @Temporal(TemporalType.DATE)
   private Date dataNascimento;

   @Column
   private String email;

   @Column
   private String telefoneCelular;

   @Column
   private String telefoneCasa;

   @Column
   private String telefoneTrabalho;

   public Long getId()
   {
      return this.id;
   }

   public void setId(final Long id)
   {
      this.id = id;
   }

   public int getVersion()
   {
      return this.version;
   }

   public void setVersion(final int version)
   {
      this.version = version;
   }

   @Override
   public boolean equals(Object that)
   {
      if (this == that)
      {
         return true;
      }
      if (that == null)
      {
         return false;
      }
      if (getClass() != that.getClass())
      {
         return false;
      }
      if (id != null)
      {
         return id.equals(((Usuario) that).id);
      }
      return super.equals(that);
   }

   @Override
   public int hashCode()
   {
      if (id != null)
      {
         return id.hashCode();
      }
      return super.hashCode();
   }

   public String getSobrenome()
   {
      return this.sobrenome;
   }

   public void setSobrenome(final String sobrenome)
   {
      this.sobrenome = sobrenome;
   }

   public String getNome()
   {
      return this.nome;
   }

   public void setNome(final String nome)
   {
      this.nome = nome;
   }

   public Date getDataNascimento()
   {
      return this.dataNascimento;
   }

   public void setDataNascimento(final Date dataNascimento)
   {
      this.dataNascimento = dataNascimento;
   }

   public String getEmail()
   {
      return this.email;
   }

   public void setEmail(final String email)
   {
      this.email = email;
   }

   public String getTelefoneCelular()
   {
      return this.telefoneCelular;
   }

   public void setTelefoneCelular(final String telefoneCelular)
   {
      this.telefoneCelular = telefoneCelular;
   }

   public String getTelefoneCasa()
   {
      return this.telefoneCasa;
   }

   public void setTelefoneCasa(final String telefoneCasa)
   {
      this.telefoneCasa = telefoneCasa;
   }

   public String getTelefoneTrabalho()
   {
      return this.telefoneTrabalho;
   }

   public void setTelefoneTrabalho(final String telefoneTrabalho)
   {
      this.telefoneTrabalho = telefoneTrabalho;
   }

   @Override
   public String toString()
   {
      String result = getClass().getSimpleName() + " ";
      if (sobrenome != null && !sobrenome.trim().isEmpty())
         result += "sobrenome: " + sobrenome;
      if (nome != null && !nome.trim().isEmpty())
         result += ", nome: " + nome;
      if (email != null && !email.trim().isEmpty())
         result += ", email: " + email;
      if (telefoneCelular != null && !telefoneCelular.trim().isEmpty())
         result += ", telefoneCelular: " + telefoneCelular;
      if (telefoneCasa != null && !telefoneCasa.trim().isEmpty())
         result += ", telefoneCasa: " + telefoneCasa;
      if (telefoneTrabalho != null && !telefoneTrabalho.trim().isEmpty())
         result += ", telefoneTrabalho: " + telefoneTrabalho;
      return result;
   }
}