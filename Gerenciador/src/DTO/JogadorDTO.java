package DTO;

public class JogadorDTO extends PessoaDTO{
    private String  posicao_jogador, situacao_jogador;
    private int numero_jogador, id_jogador;
    private double salario_jogador;
    
    public int getId_jogador() {
        return id_jogador;
    }

    public void setId_jogador(int id_jogador) {
        this.id_jogador = id_jogador;
    }
    
    public String getSituacao_jogador() {
        return situacao_jogador;
    }

    public void setSituacao_jogador(String situacao_jogador) {
        this.situacao_jogador = situacao_jogador;
    }

    public String getPosicao_jogador() {
        return posicao_jogador;
    }

    public void setPosicao_jogador(String posicao_jogador) {
        this.posicao_jogador = posicao_jogador;
    }

    public int getNumero_jogador() {
        return numero_jogador;
    }

    public void setNumero_jogador(int numero_jogador) {
        this.numero_jogador = numero_jogador;
    }

    public double getSalario_jogador() {
        return salario_jogador;
    }

    public void setSalario_jogador(double salario_jogador) {
        this.salario_jogador = salario_jogador;
    }

    
}
