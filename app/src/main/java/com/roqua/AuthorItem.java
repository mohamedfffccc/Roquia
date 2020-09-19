package com.roqua;

class AuthorItem {
    String name;
    int image;
    String roqia_aya;
    String harq_aya;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getRoqia_aya() {
        return roqia_aya;
    }

    public void setRoqia_aya(String roqia_aya) {
        this.roqia_aya = roqia_aya;
    }

    public String getHarq_aya() {
        return harq_aya;
    }

    public void setHarq_aya(String harq_aya) {
        this.harq_aya = harq_aya;
    }

    public AuthorItem(String name, int image, String roqia_aya, String harq_aya) {
        this.name = name;
        this.image = image;
        this.roqia_aya = roqia_aya;
        this.harq_aya = harq_aya;
    }
}
