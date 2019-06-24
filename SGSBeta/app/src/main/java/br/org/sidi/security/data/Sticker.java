package br.org.sidi.security.data;

public class Sticker {
    private String number;
    private String name;
    private String pictureUrl;

    public Sticker() {
    }

    public Sticker(String number, String name, String pictureUrl) {
        this.number = number;
        this.name = name;
        this.pictureUrl = pictureUrl;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPictureUrl() {
        return pictureUrl;
    }

    public void setPictureUrl(String pictureUrl) {
        this.pictureUrl = pictureUrl;
    }
}
