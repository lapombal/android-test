package com.security.br.org.sidi.sgstrial;

public class Transferencia {

    private String nome;
    private String asset;
    private String destino;
    private String tipo;
    private String status;
    private String data;

    public Transferencia() {
    }

    public Transferencia(String nome, String asset, String destino, String tipo, String status, String data) {
        setNome(nome);
        setAsset(asset);
        setDestino(destino);
        setTipo(tipo);
        setStatus(status);
        setData(data);
    }

    private String getNome() {
        return nome;
    }

    private void setNome(String nome) { this.nome=nome; }

    private String getAsset() {
        return asset;
    }

    private void setAsset(String asset) {
        this.asset = asset;
    }

    private String getDestino() {
        return destino;
    }

    private void setDestino(String destino) {
        this.destino = destino;
    }

    private String getStatus() {
        return status;
    }

    private void setStatus(String status) {
        this.status = status;
    }

    private String getData() {
        return data;
    }

    private void setData(String data) {
        this.data = data;
    }

    private String getTipo() {
        return tipo;
    }

    private void setTipo(String tipo) {
        this.tipo = tipo;
    }
}
