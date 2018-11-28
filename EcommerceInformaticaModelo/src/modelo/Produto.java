/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

/**
 *
 * @author Leandro
 */
@Entity
@Table(name="produto")
public class Produto implements Serializable {
    
    @Id
    @SequenceGenerator(name="seq_produto", sequenceName = "seq_produto_id", allocationSize = 1)
    @GeneratedValue(generator = "seq_produto", strategy = GenerationType.SEQUENCE)
    private Integer id;
    
    
    @NotNull(message="A marca não pode ser nulo")
    @NotBlank(message="A marca não pode ser em branco")
    @Length(max=50, message="A marca não pode ter mais que {max} caracteres")
    @Column(name="marca",length = 50, nullable = false)
    private String marca;
    
    
    @NotNull(message="A descricao não pode ser nulo")
    @NotBlank(message="A descricao não pode ser em branco")
    @Length(max=50, message="A descricao não pode ter mais que {max} caracteres")
    @Column(name="descricao",length = 50, nullable = false)
    private String descricao;
    
    
    @NotNull(message="O valor não pode ser nulo")
    @Min(value = 0,message="O valor não poder ser abaixo de zero")
    @Column(name="valor",columnDefinition ="numeric(10,2)",nullable=false)
    private Double valor;
    
    
    @NotNull(message="O estoque não pode ser nulo")
    @Min(value = 0,message="O estoque não poder ser abaixo de zero")
    @Column(name="estoque",nullable=false)
    private Integer estoque;
    
   
    @Min(value = 0,message="A promocao não poder ser abaixo de zero")
    @Column(name="promocao",columnDefinition ="numeric(10,2)")
    private Double promocao;
    
    
    
    @NotNull(message="O peso não pode ser nulo")
    @Min(value = 0,message="O peso não poder ser abaixo de zero")
    @Column(name="peso",columnDefinition ="numeric(10,2)",nullable=false)
    private Double peso;

    public Produto() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public Integer getEstoque() {
        return estoque;
    }

    public void setEstoque(Integer estoque) {
        this.estoque = estoque;
    }

    public Double getPromocao() {
        return promocao;
    }

    public void setPromocao(Double promocao) {
        this.promocao = promocao;
    }

    public Double getPeso() {
        return peso;
    }

    public void setPeso(Double peso) {
        this.peso = peso;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 47 * hash + Objects.hashCode(this.id);
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
        final Produto other = (Produto) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }
    
    
    
    
    
}
