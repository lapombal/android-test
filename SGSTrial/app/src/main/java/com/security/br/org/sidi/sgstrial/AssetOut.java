package com.security.br.org.sidi.sgstrial;

/********************************************
 *
 * @author l.pombal
 *
 */

public class AssetOut {

    private String nome;
    private String asset;
    private String destino;
    private String tipo;
    private String status;

    public AssetOut() {
    }

    public AssetOut(String nome, String asset, String destino, String tipo, String status) {
        this.nome = nome;
        this.asset = asset;
        this.destino = destino;
        this.tipo = tipo;
        this.status = status;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getAsset() {
        return asset;
    }

    public void setAsset(String asset) {
        this.asset = asset;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
