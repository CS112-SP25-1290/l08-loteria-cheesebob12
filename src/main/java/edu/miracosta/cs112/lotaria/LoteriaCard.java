package edu.miracosta.cs112.lotaria;

import javafx.scene.image.Image;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class LoteriaCard {
    private String cardName = "";
    private String imageName = "";
    private int cardNum = 0;

    public LoteriaCard(String cardName, String imageName, int cardNum) {
        this.setAll(cardName, imageName, cardNum);
    }

    public LoteriaCard(LoteriaCard original) {
        if (original != null) {
            this.setAll(original.cardName, original.imageName, original.cardNum);
        } else {
            System.err.println("ERROR: trying to copy NULL object. Exiting...");
            System.exit(1);
        }
    }

    public LoteriaCard() {
        this("EChALE STEM Logo", "0.png", 0);
    }

    public void setAll(String cardName, String imageName, int cardNum) {
        this.setCardName(cardName);
        this.setImageName(imageName);
        this.setCardNum(cardNum);
    }

    public String getCardName() {
        return this.cardName;
    }

    public void setCardName(String cardName) {
        this.cardName = cardName;
    }

    public String getImageName() {
        return this.imageName;
    }

    public Image getImage() {
        try {
            return new Image(new FileInputStream("resources/" + this.imageName));
        } catch (FileNotFoundException e) {
            System.err.println("ERROR: Could not load image " + this.imageName);
            return null;
        }
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public int getCardNum() {
        return cardNum;
    }

    public void setCardNum(int cardNum) {
        this.cardNum = cardNum;
    }

    @Override
    public String toString() {
        return "Loteria Card #" + this.cardNum + ": " + this.cardName + " (" + this.imageName + ")";
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof LoteriaCard other) {
            return this.cardName.equals(other.cardName)
                    && this.cardNum == other.cardNum
                    && this.imageName.equals(other.imageName);
        }
        return false;
    }
}
