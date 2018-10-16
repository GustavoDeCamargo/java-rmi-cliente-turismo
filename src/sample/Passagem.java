package sample;

import java.io.Serializable;
import java.util.Date;

public class Passagem implements Serializable {
    private static final long serialVersionUID = 8367908553994431734L;
    private Integer id;
    private Voo voo;
    private Date data;
    private Double preco;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Voo getVoo() {
        return voo;
    }

    public void setVoo(Voo voo) {
        this.voo = voo;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }
}
