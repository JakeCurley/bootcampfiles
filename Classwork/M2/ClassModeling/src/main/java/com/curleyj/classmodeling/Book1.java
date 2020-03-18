package com.curleyj.classmodeling;

/**
 *
 * @author Jake
 */
public class Book1 {
   private String name;
   private String authorName;
   private int numberOfPages;
   private int sizeOfFont;
   private String materialOfCover;
   
    public Book1() {
       
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public int getNumberOfPages() {
        return numberOfPages;
    }

    public void setNumberOfPages(int numberOfPages) {
        this.numberOfPages = numberOfPages;
    }

    public int getSizeOfFont() {
        return sizeOfFont;
    }

    public void setSizeOfFont(int sizeOfFont) {
        this.sizeOfFont = sizeOfFont;
    }

    public String getMaterialOfCover() {
        return materialOfCover;
    }

    public void setMaterialOfCover(String materialOfCover) {
        this.materialOfCover = materialOfCover;
    }
   
   
   
}
