package com.security.br.org.sidi.sgstrial;

/********************************************
 *
 * @author l.pombal
 *
 */

public class AssetOut {

    private String name;
    private String asset;
    private String destiny;
    private String type;
    private String status;

    public AssetOut() {
    }

    public AssetOut(String name, String asset, String destiny, String type, String status) {
        this.name = name;
        this.asset = asset;
        this.destiny = destiny;
        this.type = type;
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAsset() {
        return asset;
    }

    public void setAsset(String asset) {
        this.asset = asset;
    }

    public String getDestiny() {
        return destiny;
    }

    public void setDestiny(String destiny) {
        this.destiny = destiny;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
