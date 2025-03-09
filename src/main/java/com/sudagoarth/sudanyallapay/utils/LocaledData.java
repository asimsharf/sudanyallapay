package  com.sudagoarth.sudanyallapay.utils;
public class LocaledData {

    private String arabic;
    private String english;

    public LocaledData() {
    }

    public LocaledData(String arabic, String english) {

        this.arabic = arabic;
        this.english = english;
    }

    public String getArabic() {
        return arabic;
    }

    public void setArabic(String arabic) {
        this.arabic = arabic;
    }

    public String getEnglish() {
        return english;
    }

    public void setEnglish(String english) {
        this.english = english;
    }

    // toString method for easy debugging
    @Override
    public String toString() {
        return "LocaledData{" +
                ", arabic='" + arabic + '\'' +
                ", english='" + english + '\'' +
                '}';
    }
}
