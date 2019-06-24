package mardil.it.uniparthenope.easyeat;

public class model {

    private boolean isSelected;
    private String nomePiatto;

    public String getPiatto() {
        return nomePiatto;
    }

    public void setPiatto(String p) {
        this.nomePiatto = p;
    }

    public boolean getSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

}
