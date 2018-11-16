/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

/**
 *
 * @author Leandro
 */
@Entity
@Table(name="pessoa")
public class Pessoa implements Serializable{
    
    @Id
    @NotNull(message="O nome de pessoa não pode ser nulo")
    @NotBlank(message="O nome de pessoa não pode ser em branco")
    @Length(max=20, message="O nome de pessoa não pode ter mais que {max} caracteres")
    @Column(name="nome_pessoa",length = 20, nullable = false)
    private String nomePessoa;
    
    @NotNull(message="O nome não pode ser nulo")
    @NotBlank(message="O nome não pode ser em branco")
    @Length(max=20, message="O nome não pode ter mais que {max} caracteres")
    @Column(name="nome",length = 20, nullable = false)
    private String nome;
    
    @Email(message="o email deve ser válido")
    @NotNull(message="O email não pode ser nulo")
    @NotBlank(message="O email não pode ser em branco")
    @Length(max=20, message="O email não pode ter mais que {max} caracteres")
    @Column(name="email",length = 20, nullable = false)
    private String email;
    
    @NotNull(message="A senha não pode ser nulo")
    @NotBlank(message="A senha não pode ser em branco")
    @Length(max=20, message="A senha não pode ter mais que {max} caracteres")
    @Column(name="senha",length = 20, nullable = false)
    private String senha;
    
    
    
    @ManyToMany
    @JoinTable(name="perfis",joinColumns = @JoinColumn(name="pessoa",
            referencedColumnName = "nome_pessoa",nullable = false),
            inverseJoinColumns = @JoinColumn(name="perfil",
                    referencedColumnName = "cargo",nullable = false))
    private Set<Perfil> perfis=new HashSet<>();

    public Pessoa() {
    }

    public String getNomePessoa() {
        return nomePessoa;
    }

    public void setNomePessoa(String nomePessoa) {
        this.nomePessoa = nomePessoa;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Set<Perfil> getPerfis() {
        return perfis;
    }

    public void setPerfis(Set<Perfil> perfis) {
        this.perfis = perfis;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 23 * hash + Objects.hashCode(this.nomePessoa);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Pessoa other = (Pessoa) obj;
        if (!Objects.equals(this.nomePessoa, other.nomePessoa)) {
            return false;
        }
        return true;
    }
    
    
    
}
